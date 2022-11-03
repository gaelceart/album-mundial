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

	public void iniciarSimulacion() {
		simular();
	}

	public double simular() {
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
		for (int userIndex = 0; userIndex < _cantSimulaciones; userIndex++) {
			sumarCostos(userIndex);
			sumarPaquetesComprados(userIndex);
			sumarRepetidas(userIndex);
		}
	}

	private void sumarRepetidas(int userIndex) {
		_cantFigusRepetidas += _s[userIndex].getCantidadFigusSobrantes();
	}

	private void sumarCostos(int userIndex) {
		_costoTotal += _s[userIndex].getCostoTotal();
	}

	private void sumarPaquetesComprados(int userIndex) {
		_cantPaquetesComprados += _s[userIndex].getCantidadPaquetesComprados();
	}

	private void calcCostoPromedio() {
		_costoPromedio = _costoTotal / _cantSimulaciones * _cantUsuarios;
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

	public String getEscenarioActual() {
		return _escenario + "";
	}

	public String getCostoPromedio() {
		_costoPromedio = Math.round(_costoPromedio * 100.0) / 100.0;
		return _costoPromedio + "";
	}

	public String getPaquetesComprados() {
		return _cantPaquetesComprados + "";
	}

	public String getFigusRepetidas() {
		return _cantFigusRepetidas + "";
	}

	public String getUsuario0() {
		return _s[0].toStringUsuario0();
	}

}