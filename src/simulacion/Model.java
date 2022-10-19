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
	tipoEscenario _escenario = tipoEscenario.donacion;
	
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
	
	double main() throws InterruptedException {
		Album.crearGeneradorAleatorio();
		Paquete.crearGeneradorAleatorio();

		double costoTotal = 0.0;
		double costoPromedio = 0.0;

		Simulacion[] s = new Simulacion[_cantSimulaciones];
		Thread[] t = new Thread[_cantSimulaciones];

		for (int i = 0; i < _cantSimulaciones; i++) {
			s[i] = new Simulacion(_cantUsuarios, _cantFigusAlbum, _cantFigusPaquete, _escenario);
			t[i] = new Thread(s[i]);
			t[i].start();
		}

		System.out.println("CARGANDO...\n");

		for (int i = 0; i < _cantSimulaciones; i++) {
			t[i].join();
			costoTotal += s[i].getPaquetesTotalesComprados() * _costoPaquete / _cantUsuarios;
		}
		costoPromedio = costoTotal / _cantSimulaciones;
		System.out.println("COSTO TOTAL: " + costoTotal);
		System.out.println("COSTO PROMEDIO: " + costoPromedio);
		
		return costoPromedio;
	}

	public void iniciarSimulacion() {
		
	}
}
