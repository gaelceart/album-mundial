package simulacion;

import static org.junit.Assert.*;

import org.junit.Test;

import albumMundial.Paquete;
import generadores.GeneradorPaquetesDoblesEnOrden;
import generadores.GeneradorPaquetesEnOrdenTrue;

public class SimulacionIndividualTest {

	@Test
	public void completarAlbumTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, 100, 0, 5, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test(expected = IllegalArgumentException.class)
	public void simularConCeroUsuarios() {
		Simulacion simulacion = new Simulacion(0, 1, 1, 1, tipoEscenario.individual);
		simulacion.getCantidadFigusDonadas();
	}

	@Test(expected = IllegalArgumentException.class)
	public void completarAlbumNuloTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, 0, 0, 0, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test
	public void comprarPaqueteConUnaFiguritaTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, 100, 0, 1, tipoEscenario.individual);
		simulacion.run();
		assertEquals(100, simulacion.getCantidadPaquetesComprados());
	}

	@Test
	public void comprarPaquetesExactosTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, 100, 0, 5, tipoEscenario.individual);
		simulacion.run();
		assertEquals(20, simulacion.getCantidadPaquetesComprados());
	}

	@Test
	public void paquetesRepetidosTest() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(1, 20, 0, 2, tipoEscenario.individual);
		simulacion.run();
		assertEquals(19, simulacion.getCantidadPaquetesComprados());

	}

	@Test
	public void completarAlbumSinSobrantesTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, 20, 20, 20, tipoEscenario.individual);
		simulacion.run();
		assertEquals(0, simulacion.getCantidadFigusSobrantes());
	}

	@Test
	public void figuritasSobrantesTest() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(1, 20, 0, 2, tipoEscenario.individual);
		simulacion.run();
		assertEquals(18, simulacion.getCantidadFigusSobrantes());
	}

	@Test
	public void costoPaquetesExactosTest() {

	}

	@Test
	public void costoPaquetesDoblesTest() {

	}
}
