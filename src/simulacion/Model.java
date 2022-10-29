package simulacion;

import albumMundial.Album;

public class Model {

	private int _cantSimulaciones;
	private int _cantUsuarios;
	private int _cantFigusAlbum;
	private int _cantFigusRaras;
	private int _cantFigusPaquete;
	private int _cantFigusRepetidas;
	private int _cantPaquetesComprados;
	private int _precioPaquete;
	private double _costoTotal;
	private double _costoPromedio;
	tipoEscenario _escenario;

	private Simulacion[] _s;
	private Thread[] _t;

	public Model() {
		_cantSimulaciones = 0;
		_cantUsuarios = 0;
		_cantFigusAlbum = 0;
		_cantFigusRaras = 0;
		_cantFigusPaquete = 0;
		_cantFigusRepetidas = 0;
		_cantPaquetesComprados = 0;
		_precioPaquete = 0;
		_costoTotal = 0;
		_costoPromedio = 0;
		_escenario = tipoEscenario.individual;
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

	public void setCantFigusRaras(int n) {
		_cantFigusRaras = n;
	}

	public void setCantFigusPaquete(int n) {
		_cantFigusPaquete = n;
	}

	public void setPrecioPaquete(int c) {
		_precioPaquete = c;
	}

	public void setTipoEscenario(tipoEscenario e) {
		_escenario = e;
	}

	double simular() {
		initSimulaciones();
		initThreads();
		startThreads();
		stopThreads();
		calcularEstadisticas();
		calcCostoPromedio();
		System.out.println("COSTO TOTAL: " + _costoTotal);
		System.out.println("COSTO PROMEDIO: " + _costoPromedio);

		return _costoPromedio;
	}

	private void calcCostoPromedio() {
		_costoPromedio = _costoTotal / _cantSimulaciones;
	}

	private void initSimulaciones() {
		_s = new Simulacion[_cantSimulaciones];

		for (int i = 0; i < _cantSimulaciones; i++) {
			Album album = new Album(_cantFigusAlbum, _cantFigusRaras);
			_s[i] = new Simulacion(_cantUsuarios, album, _cantFigusPaquete, _precioPaquete, _escenario);
		}
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

	private void calcularEstadisticas() {
		for (int i = 0; i < _cantSimulaciones; i++) {
			sumarCostoDeSimulacion(i);
			calcPaquetesComprados(i);
			calcFigusRepetidas(i);
		}
	}

	private void calcFigusRepetidas(int i) {
		_cantFigusRepetidas = _s[i].getCantidadFigusSobrantes();
	}

	private void sumarCostoDeSimulacion(int i) {
		_costoTotal += _s[i].getCantidadPaquetesComprados() * _precioPaquete / _cantUsuarios;
	}

	private void calcPaquetesComprados(int i) {
		_cantPaquetesComprados = _s[i].getCantidadPaquetesComprados();
	}

	public void iniciarSimulacion() {
		simular();
	}

	public String setEscenarioActual() {
		return _escenario + "";
	}

	public String setCostoPromedio() {
		_costoPromedio = Math.round(_costoPromedio * 100.0) / 100.0;
		return _costoPromedio + "";
	}

	public String setPaquetesComprados() {
		return _cantPaquetesComprados + "";
	}

	public String setFigusRepetidas() {
		return _cantFigusRepetidas + "";
	}

	public String setUsuario0() {
		return _s[0].toStringUsuario0();
	}

}