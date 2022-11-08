package simulacion;

import java.util.HashMap;
import java.util.Iterator;

import albumMundial.Album;

public class Simulacion implements Runnable {
	private Usuario[] _users;
	private int _cantidadFigusPorPaquete;
	private int _cantRarasAlbum;
	private int _cantidadPaquetesComprados;
	private int _cantidadFigusRepetidas;
	private int _cantidadFigusAlbum;
	private int _cantidadFigusDonadas;
	private int _cantidadFigusIntercambiadas;
	private int _cantidadIntercambiosRealizados;
	private int _cantidadFigusSobrantes;
	private int _costoPaquete;
	private int _costoTotal;

	private tipoEscenario _escenario;

	public Simulacion(int cantUsuarios, Album album, int cantFigusPorPaquete, int costoPaquete, tipoEscenario e) {
		if (album == null) {
			throw new NullPointerException("Un album no puede ser null");
		}
		if (costoPaquete < 0) {
			throw new IllegalArgumentException(
					"El costo de los paquetes no puede ser menor 0. costoPaquete:" + costoPaquete);
		}
		if (cantUsuarios <= 0) {
			throw new IllegalArgumentException(
					"No puede existir una simulacion con 0 o menos usuarios. cantUsuarios: " + cantUsuarios);
		}
		_cantidadFigusAlbum = album.getCantidadFiguritas();
		_cantRarasAlbum = album.getCantidadFiguritasRaras();
		_cantidadFigusPorPaquete = cantFigusPorPaquete;
		_users = inicializarUsers(cantUsuarios);
		_escenario = e;
		_cantidadPaquetesComprados = 0;
		_cantidadFigusRepetidas = 0;
		_cantidadFigusDonadas = 0;
		_cantidadFigusSobrantes = 0;
		_cantidadIntercambiosRealizados = 0;
		_cantidadFigusIntercambiadas = 0;
		_costoPaquete = costoPaquete;
		_costoTotal = 0;
	}

	@Override
	public void run() {

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

		calcularEnUsuarios();
	}

	public boolean albumesCompletos() {
		for (Usuario u : _users) {
			if (!u.tieneAlbumCompleto())
				return false;
		}
		return true;
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

	private void pegarFiguritas(HashMap<Integer, Integer[]> paquetes) {
		for (int i = 0; i < _users.length; i++)
			if (paquetes.containsKey(i)) {
				_users[i].pegarFiguritas(paquetes.get(i));
			}
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
						_users[donante].contarFiguritaDonada();
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
								_cantidadFigusIntercambiadas += 2;
								_cantidadIntercambiosRealizados++;
								_users[trader].contarFiguritaIntercambiada();
								_users[destino].contarFiguritaIntercambiada();
								break;
							}
						}
						if (seIntercambio) {
							itB.remove();
							itA.remove();
							break;
						}
					}
				}
			}
		}
	}

	private void calcularEnUsuarios() {
		for (Usuario u : _users) {
			calcularFigusRepetidasTotales(u);
			calcularPaquetesTotales(u);
			calcularFigusRepetidasSobrantes(u);
			calcularCostoTotal(u);
		}
	}

	private void calcularFigusRepetidasTotales(Usuario u) {
		_cantidadFigusRepetidas += u.getCantidadFigusRepetidas();
	}

	private void calcularPaquetesTotales(Usuario u) {
		_cantidadPaquetesComprados += u.getCantidadPaquetesComprados();
	}

	private void calcularFigusRepetidasSobrantes(Usuario u) {
		_cantidadFigusSobrantes += u.getFiguritasRepetidas().size();
	}

	private void calcularCostoTotal(Usuario u) {
		_costoTotal += u.getCantidadPaquetesComprados() * _costoPaquete;
	}

	public int cantidadFigusIntercambiadasUsuario(int i) {
		if (i > _users.length || i < 0) {
			throw new IndexOutOfBoundsException("Indice fuera de rango: " + i);
		}
		return _users[i].getCantidadFigusIntercambiadas();
	}

	public int cantidadFigusDonadasUsuario(int i) {
		if (i > _users.length || i < 0) {
			throw new IndexOutOfBoundsException("Indice fuera de rango: " + i);
		}
		return _users[i].getCantidadFigusDonadas();
	}

	public String datosDelUsuario0() {
		if (_escenario.equals(tipoEscenario.donacion)) {
			return datosUsuarioDeDonacion();
		} else if (_escenario.equals(tipoEscenario.intercambio)) {
			return datosUsuarioDeIntercambio();
		}
		return datosUsuario();
	}

	private String datosUsuario() {
		StringBuilder s = new StringBuilder();
		s.append("Paquetes Comprados\n");
		s.append(_users[0].getCantidadPaquetesComprados());
		s.append("\nRepetidas\n");
		s.append(_users[0].getCantidadFigusRepetidas());
		s.append("\nRaras\n");
		s.append(_users[0].getAlbum().getCantidadFiguritasRaras());
		return s + "\n";
	}

	private String datosUsuarioDeIntercambio() {
		StringBuilder s = new StringBuilder(datosUsuario());
		s.append("Intercambiadas\n");
		s.append(_users[0].getCantidadFigusIntercambiadas());
		return s + "\n";
	}

	private String datosUsuarioDeDonacion() {
		StringBuilder s = new StringBuilder(datosUsuario());
		s.append("Donadas\n");
		s.append(_users[0].getCantidadFigusDonadas());
		return s + "\n";
	}

	public int getcantidadIntercambiosRealizados() {
		return _cantidadIntercambiosRealizados;
	}

	public int getCantidadFigusRepetidas() {
		return _cantidadFigusRepetidas;
	}

	public int getCantidadPaquetesComprados() {
		return _cantidadPaquetesComprados;
	}

	public int getCantidadFigusDonadas() {
		return _cantidadFigusDonadas;
	}

	public int getCantidadFigusSobrantes() {
		return _cantidadFigusSobrantes;
	}

	public int getCantidadFigusIntercambiadas() {
		return _cantidadFigusIntercambiadas;
	}

	public int getCostoTotal() {
		return _costoTotal;
	}

}