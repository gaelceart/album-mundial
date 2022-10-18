package simulacion;

import java.util.HashMap;
import java.util.Iterator;

import albumMundial.Album;
import albumMundial.Paquete;


public class Simulacion implements Runnable {
	private Usuario[] _users;
	private int _cantidadFigusPorPaquete;
	private int _paquetesTotalesComprados;
	private int _figusTotalesRepetidas;
	private int _cantidadFigusAlbum;
	private int _cantidadFigusDonadas;
	private int _cantIntercambiosRealizados;

	private enum tipoEscenario {
		individual, donacion, intercambio
	};

	private tipoEscenario _escenario;
	private int _figusRepetidasSobrantes;

	public Simulacion(int cantUsuarios, int cantFigusAlbum, int cantFigusPorPaquete, tipoEscenario e) {
		_cantidadFigusAlbum = cantFigusAlbum;
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
			ret[i] = new Usuario(_cantidadFigusAlbum);
		return ret;
	}

	// public double simular(int cantSimulaciones, int cantUsuarios, int
	// cantFigusAlbum, int cantFigusPorPaquete, double costoPaquete, tipoEscenario
	// e) {
	public static void main(String[] args) throws InterruptedException {
		Album.crearGeneradorAleatorio();
		Paquete.crearGeneradorAleatorio();
		
		int cantSimulaciones = 2;
		int cantUsuarios = 2;
		int cantFigusAlbum = 638;
		int cantFigusPorPaquete = 5;
		tipoEscenario e = tipoEscenario.donacion;

		double costoPaquete = 150.0;
		double costoTotal = 0.0;
		double costoPromedio = 0.0;

		Simulacion[] s = new Simulacion[cantSimulaciones];
		Thread[] t = new Thread[cantSimulaciones];

		for (int i = 0; i < cantSimulaciones; i++) {
			s[i] = new Simulacion(cantUsuarios, cantFigusAlbum, cantFigusPorPaquete, e);
			t[i] = new Thread(s[i]);
			t[i].start();
		}

		System.out.println("CARGANDO...\n");

		for (int i = 0; i < cantSimulaciones; i++) {
			t[i].join();
			costoTotal += s[i]._paquetesTotalesComprados * costoPaquete / cantUsuarios;
		}
		costoPromedio = costoTotal / cantSimulaciones;
		System.out.println("COSTO TOTAL: " + costoTotal);
		System.out.println("COSTO PROMEDIO: " + costoPromedio);
	}

	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		while (!albumesCompletos()) {
			// Fase 1 Comprar paquetes
			HashMap<Integer, Integer[]> paquetes = comprarPaquetes(_cantidadFigusPorPaquete);

			// Fase 2 Pegar figuritas
			for (int i = 0; i < _users.length; i++)
				if (paquetes.containsKey(i))
					_users[i].pegarFiguritas(paquetes.get(i));

			// Fase 3 Donar y pegar donadas
			if (_escenario == tipoEscenario.donacion) {
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

			// Fase 3 Intercambiar
			if (_escenario == tipoEscenario.intercambio) {
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

		}

		_calcularPaquetesTotales();
		_calcularFigusRepetidasTotales();
		_calcularFigusRepetidasSobrantes();
		System.out.println("CANT USERS:" + _users.length);
		System.out.println("CANTIDAD FIGUS ALBUM: " + _cantidadFigusAlbum);
		System.out.println("CANTIDAD FIGUS PAQUETE: " + _cantidadFigusPorPaquete);
		System.out.println("Paquetes totales: " + _paquetesTotalesComprados);
		System.out.println("Paquetes comprados por el usuario 0: " + _users[0].getCantidadPaquetesComprados());
		System.out.println(
				"Paquetes comprados por el usuario final: " + _users[_users.length - 1].getCantidadPaquetesComprados());
		System.out.println("Figuritas repetidas totales: " + _figusTotalesRepetidas);
		System.out.println("Figuritas repetidas sobrantes: " + _figusRepetidasSobrantes);
		System.out.println("Figuritas repetidas del usuario 0: " + _users[0].getCantidadFigusRepetidasTotal());
		System.out.println(
				"Figuritas repetidas del usuario final: " + _users[_users.length - 1].getCantidadFigusRepetidasTotal());
		System.out.println("Figuritas donadas totales: " + _cantidadFigusDonadas);
		System.out.println("Figuritas intercambiadas: " + _cantIntercambiosRealizados);
		long endTime = System.currentTimeMillis();
		System.out.println("TIEMPO TRANSCURRIDO: " + (endTime - startTime) + "ms\n");

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

	private boolean albumesCompletos() {
		for (Usuario u : _users) {
			if (!u.tieneAlbumCompleto())
				return false;
		}
		return true;
	}

}
