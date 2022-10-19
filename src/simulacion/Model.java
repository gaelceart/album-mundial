package simulacion;

import albumMundial.Album;
import albumMundial.Paquete;

public class Model {
	
	// public double simular(int cantSimulaciones, int cantUsuarios, int
	// cantFigusAlbum, int cantFigusPorPaquete, double costoPaquete, tipoEscenario
	// e) {
	private int _cantSimulaciones;
	private int _cantUsuarios;
	private int _cantFigusAlbum;
	private int _cantFigusPaquete;
	private double _costoPaquete;
	private double _costoTotal;
	tipoEscenario _escenario;

	private Simulacion[] _s;
	private Thread[] _t;
	
	public Model() {
		_cantSimulaciones = 0;
		_cantUsuarios = 0;
		_cantFigusAlbum = 0;
		_cantFigusPaquete = 0;
		_costoPaquete = 0;
		_costoTotal = 0;
		_escenario = tipoEscenario.donacion;
	}

	public void setCantSimulaciones(int n) {
		_cantSimulaciones = n;
	}

	public void setCantUsuarios(int n) {
		_cantUsuarios = n;
	}

	public void setCantFigusAlbum(int n) {
		_cantFigusAlbum = n;
	}

	public void setCantFigusPaquete(int n) {
		_cantFigusPaquete = n;
	}

	public void setCostoPaquete(double c) {
		_costoPaquete = c;
	}
	
	public void setTipoEscenario(tipoEscenario e) {
		_escenario = e;
	}
	
	double simular() {
		
		initSimulaciones();
		initThreads();
		startThreads();
		stopThreads();
		calcCostoTotal();

		System.out.println("CARGANDO...\n");

		double costoPromedio = _costoTotal / _cantSimulaciones;
		System.out.println("COSTO TOTAL: " + _costoTotal);
		System.out.println("COSTO PROMEDIO: " + costoPromedio);
		
		return costoPromedio;
	}

	private void initSimulaciones() {
		_s = new Simulacion[_cantSimulaciones];
		for (int i = 0; i < _cantSimulaciones; i++) 
			_s[i] = new Simulacion(_cantUsuarios, _cantFigusAlbum, _cantFigusPaquete, _escenario);
	}

	private void initThreads() {
		_t = new Thread[_cantSimulaciones];
		for (int i = 0; i < _cantSimulaciones; i++)
			_t[i] = new Thread(_s[i]);
	}

	private void startThreads() {
		for (int i = 0; i < _cantSimulaciones; i++)
			_t[i].start();
	}
	
	private void stopThreads() {
		for (int i = 0; i < _cantSimulaciones; i++) {
			tryJoinThread(i);
		}
	}

	private void tryJoinThread(int i) {
		try {
			_t[i].join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void calcCostoTotal() {
		for (int i = 0; i < _cantSimulaciones; i++)
			sumarCostoDeSimulacion(i);
	}

	private void sumarCostoDeSimulacion(int i) {
		_costoTotal += _s[i].getPaquetesTotalesComprados() * _costoPaquete / _cantUsuarios;
	}

	public void iniciarSimulacion() {
		Album.crearGeneradorAleatorio();
		Paquete.crearGeneradorAleatorio();
		double costoPromedio = simular();
	}
}
