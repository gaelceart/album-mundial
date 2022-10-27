package simulacion;

import static org.junit.Assert.*;

import org.junit.Test;

import albumMundial.Paquete;
import generadores.GeneradorIntercambiador;
import generadores.GeneradorIntercambiadorSobrantes;
import generadores.GeneradorPaquetesDoblesEnOrden;

public class SimulacionIntercambioTest {

	@Test
	public void completarAlbumTest() {
		Paquete.setGenerador(new GeneradorIntercambiador());
		Simulacion simulacion = new Simulacion(2, 10, 0, 2, tipoEscenario.intercambio);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test
	public void completarAlbumSinIntercambiarTest() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(2, 20, 0, 2, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(0, simulacion.getcantidadIntercambiosRealizados());
	}

	@Test
	public void completarAlbumIntercambiando() {
		Paquete.setGenerador(new GeneradorIntercambiador());
		Simulacion simulacion = new Simulacion(2, 10, 0, 2, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(4, simulacion.getcantidadIntercambiosRealizados());
	}

	@Test
	public void cantidadFiguritasIntercambiadas() {
		Paquete.setGenerador(new GeneradorIntercambiador());
		Simulacion simulacion = new Simulacion(2, 10, 0, 2, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(8, simulacion.getCantidadFigusIntercambiadas());
	}

	@Test
	public void figuritasRepetidasTest() {
		Paquete.setGenerador(new GeneradorIntercambiador());
		Simulacion simulacion = new Simulacion(2, 10, 0, 2, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(8, simulacion.getCantidadFigusRepetidas());
	}

	@Test
	public void figuritasSobrantesTest() {
		Paquete.setGenerador(new GeneradorIntercambiadorSobrantes());
		Simulacion simulacion = new Simulacion(2, 10, 0, 2, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(4, simulacion.getCantidadFigusSobrantes());
	}
	@Test
	public void sinSobrantesTest() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(2, 10, 0, 2, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(0, simulacion.getCantidadFigusSobrantes());
	}
	@Test
	public void costoCompletarSinIntercambiarTest() {
	}

	@Test
	public void costoPaquetesIntercambiandoTest() {

	}
}
