package simulacion;

import static org.junit.Assert.*;

import org.junit.Test;

import albumMundial.Paquete;
import generadores.Generador;
import generadores.GeneradorCuadruplePaquetes;
import generadores.GeneradorPaquetesDobles;
import generadores.GeneradorPaquetesEnOrdenTrue;
import generadores.GeneradorRandom;

public class SimulacionIndividualTest {

	@Test
	public void completarAlbumTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, 100, 0, 5, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test
	public void dosUsuariosCompletarAlbum() {
		Paquete.setGenerador(new GeneradorPaquetesDobles());
		Simulacion simulacion = new Simulacion(2, 10, 2, 2, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test(expected = IllegalArgumentException.class)
	public void completarAlbumNuloTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, 0, 0, 0, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test
	public void completarAlbumSinUsuariosTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(0, 10, 0, 1, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());

	}

	@Test
	public void paquetesRepetidosDosUsuariosTest() {
		Paquete.setGenerador(new GeneradorCuadruplePaquetes());
		Simulacion simulacion = new Simulacion(2, 20, 0, 2, tipoEscenario.individual);
		simulacion.run();
		assertEquals(38, simulacion.getPaquetesTotalesComprados());
	}

	@Test
	public void figuritasRepetidasDosUsuariosTest() {
		Paquete.setGenerador(new GeneradorCuadruplePaquetes());
		Simulacion simulacion = new Simulacion(2, 10, 0, 2, tipoEscenario.individual);
		simulacion.run();
		assertEquals(16, simulacion.getFiguritasSobrantes());
	}

	@Test
	public void comprarPaqueteConUnaFiguritaTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, 100, 0, 1, tipoEscenario.individual);
		simulacion.run();
		assertEquals(100, simulacion.getPaquetesTotalesComprados());
	}

	@Test
	public void comprarPaquetesJustosTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, 100, 0, 5, tipoEscenario.individual);
		simulacion.run();
		assertEquals(20, simulacion.getPaquetesTotalesComprados());
	}

	@Test
	public void paquetesRepetidosTest() {
		Paquete.setGenerador(new GeneradorPaquetesDobles());
		Simulacion simulacion = new Simulacion(1, 20, 0, 2, tipoEscenario.individual);
		simulacion.run();
		assertEquals(19, simulacion.getPaquetesTotalesComprados());

	}

	@Test
	public void completarAlbumSinSobrantesTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, 20, 20, 20, tipoEscenario.individual);
		simulacion.run();
		assertEquals(0, simulacion.getFiguritasSobrantes());
	}

	@Test
	public void figuritasSobrantesTest() {
		Paquete.setGenerador(new GeneradorPaquetesDobles());
		Simulacion simulacion = new Simulacion(1, 20, 0, 2, tipoEscenario.individual);
		simulacion.run();
		assertEquals(18, simulacion.getFiguritasSobrantes());
	}


}
