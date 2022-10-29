package simulacion;

import org.junit.Before;
import org.junit.Test;

import albumMundial.Album;
import albumMundial.Paquete;
import generadores.GeneradorPaquetesEnOrdenTrue;

public class SimulacionTest {
	private Album _albumDeDiez;

	@Before
	public void setAlbum() {
		_albumDeDiez = new Album(10, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void costoPaqueteNegativoTest() {
		Simulacion simulacion = new Simulacion(1, _albumDeDiez, 1, -1, tipoEscenario.individual);
		simulacion.getCostoTotal();
	}

	@Test(expected = IllegalArgumentException.class)
	public void simularConCeroUsuariosTest() {
		Simulacion simulacion = new Simulacion(0, _albumDeDiez, 1, 0, tipoEscenario.individual);
		simulacion.getCantidadFigusDonadas();
	}

	@Test(expected = NullPointerException.class)
	public void completarAlbumNuloTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, null, 1, 0, tipoEscenario.individual);
		simulacion.run();
	}
}
