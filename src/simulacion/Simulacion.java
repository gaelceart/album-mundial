package simulacion;

import java.util.HashMap;
import java.util.Iterator;

public class Simulacion implements Runnable {
	private Usuario[] _users;
	private int _cantidadFigusPorPaquete;
	private int _cantRarasAlbum;
	private int _paquetesTotalesComprados;
	private int _figusTotalesRepetidas;
	private int _cantidadFigusAlbum;
	private int _cantidadFigusDonadas;
	private int _cantIntercambiosRealizados;
	private int _figusRepetidasSobrantes;

	private tipoEscenario _escenario;

	public Simulacion(int cantUsuarios, int cantFigusAlbum, int cantFigusRaras, int cantFigusPorPaquete,
			tipoEscenario e) {
		if (cantUsuarios <= 0) {
			throw new IllegalArgumentException(
					"No puede existir una simulacion con 0 o menos usuarios. cantUsuarios: " + cantUsuarios);
		}
		_cantidadFigusAlbum = cantFigusAlbum;
		_cantRarasAlbum = cantFigusRaras;
		_cantidadFigusPorPaquete = cantFigusPorPaquete;
		_users = inicializarUsers(cantUsuarios);
		_paquetesTotalesComprados = 0;
		_figusTotalesRepetidas = 0;
		_escenario = e;
		_cantidadFigusDonadas = 0;
		_figusRepetidasSobrantes = 0;
		_cantIntercambiosRealizados = 0;
	}

	private Usuario[] inicializarUsers(int cantUsuarios) {
		Usuario[] ret = new Usuario[cantUsuarios];
		for (int i = 0; i < cantUsuarios; i++)
			ret[i] = new Usuario();
		return ret;
	}

	private void comprarAlbumes(int cantFigusAlbum, int cantFigusRaras) {
		for (int i = 0; i < _users.length; i++)
			_users[i].comprarAlbum(cantFigusAlbum, cantFigusRaras);
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		comprarAlbumes(_cantidadFigusAlbum, _cantRarasAlbum);

		while (!albumesCompletos()) {
			// Fase 1 Comprar paquetes
			HashMap<Integer, Integer[]> paquetes = comprarPaquetes(_cantidadFigusPorPaquete);

			// Fase 2 Pegar figuritas
			pegarFiguritas(paquetes);

			// Fase 3 Donar y pegar donadas
			donarFiguritas();

			// Fase 3 Intercambiar
			intercambiarFiguritas();
		}

		_calcularPaquetesTotales();
		_calcularFigusRepetidasTotales();
		_calcularFigusRepetidasSobrantes();
	}

	private void pegarFiguritas(HashMap<Integer, Integer[]> paquetes) {
		for (int i = 0; i < _users.length; i++)
			if (paquetes.containsKey(i))
				_users[i].pegarFiguritas(paquetes.get(i));
	}

	private void donarFiguritas() {
		if (_escenario != tipoEscenario.donacion)
			return;
		// donar
		for (int donante = 0; donante < _users.length; donante++) {
			// recorro figus
			Iterator<Integer> it = _users[donante].getFiguritasRepetidas().iterator();
			while (it.hasNext()) {
				Integer figu = it.next();
				for (int destino = donante + 1; destino < _users.length; destino++) {
					if (!_users[destino].esFiguritaRepetida(figu)) {
						_users[destino].pegarFigurita(figu);
						it.remove();
						_cantidadFigusDonadas++;
						break;
					}
				}
			}
		}
	}

	private void intercambiarFiguritas() {
		if (_escenario != tipoEscenario.intercambio)
			return;
		// intercambiar
		for (int trader = 0; trader < _users.length; trader++) {
			if (_users[trader].tieneAlbumCompleto())
				continue;
			// recorro figus
			Iterator<Integer> itA = _users[trader].getFiguritasRepetidas().iterator();
			while (itA.hasNext()) {
				Integer figuTrader = itA.next();
				for (int destino = trader + 1; destino < _users.length; destino++) {
					if (!_users[destino].esFiguritaRepetida(figuTrader)) {
						boolean seIntercambio = false;
						Iterator<Integer> itB = _users[destino].getFiguritasRepetidas().iterator();
						while (itB.hasNext()) {
							Integer figuB = itB.next();
							if (!_users[trader].esFiguritaRepetida(figuB)) {
								_users[trader].pegarFigurita(figuB);
								_users[destino].pegarFigurita(figuTrader);
								seIntercambio = true;
								_cantIntercambiosRealizados++;
								break;
							}
							itB.remove();
							if (seIntercambio) {
								itA.remove();
								break;
							}
						}
					}
				}
			}
		}
	}

	private void _calcularFigusRepetidasTotales() {
		for (Usuario u : _users)
			_figusTotalesRepetidas += u.getCantidadFigusRepetidasTotal();
	}

	private void _calcularPaquetesTotales() {
		for (Usuario u : _users)
			_paquetesTotalesComprados += u.getCantidadPaquetesComprados();
	}

	private void _calcularFigusRepetidasSobrantes() {
		for (Usuario u : _users)
			_figusRepetidasSobrantes += u.getFiguritasRepetidas().size();
	}

	private HashMap<Integer, Integer[]> comprarPaquetes(int cantFigus) {
		HashMap<Integer, Integer[]> ret = new HashMap<>();
		int index = 0;
		for (Usuario u : _users) {
			if (!u.tieneAlbumCompleto())
				ret.put(index, u.comprarPaquete(cantFigus));
			index++;

		}
		return ret;
	}

	public boolean albumesCompletos() {
		for (Usuario u : _users) {
			if (!u.tieneAlbumCompleto())
				return false;
		}
		return true;
	}

	public int getPaquetesTotalesComprados() {
		return _paquetesTotalesComprados;
	}

	public int getFiguritasSobrantes() {
		return _figusTotalesRepetidas;
	}
	public int getFiguritasTotalesDonadas() {
		return _cantidadFigusDonadas;
	}

}
