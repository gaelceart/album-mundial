package simulacion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import albumMundial.Album;
import albumMundial.Paquete;
import generadores.GeneradorPaquetesDoblesEnOrden;
import generadores.GeneradorPaquetesEnOrdenTrue;

public class SimulacionIndividualTest {
	private Album _albumDeDiez;
	private Album _albumDeVeinte;
	private Album _albumDeCien;

	@Before
	public void setAlbum() {
		_albumDeDiez = new Album(10, 0);
		_albumDeVeinte = new Album(20, 0);
		_albumDeCien = new Album(100, 0);
	}

	@Test
	public void completarAlbumTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, _albumDeDiez, 5, 0, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
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

	@Test
	public void comprarPaqueteConUnaFiguritaTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, _albumDeCien, 1, 0, tipoEscenario.individual);
		simulacion.run();
		assertEquals(100, simulacion.getCantidadPaquetesComprados());
	}

	@Test
	public void comprarPaquetesExactosTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, _albumDeCien, 5, 0, tipoEscenario.individual);
		simulacion.run();
		assertEquals(20, simulacion.getCantidadPaquetesComprados());
	}

	@Test
	public void paquetesRepetidosTest() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(1, _albumDeVeinte, 2, 0, tipoEscenario.individual);
		simulacion.run();
		assertEquals(19, simulacion.getCantidadPaquetesComprados());

	}

	@Test
	public void completarAlbumSinSobrantesTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, _albumDeVeinte, 20, 0, tipoEscenario.individual);
		simulacion.run();
		assertEquals(0, simulacion.getCantidadFigusSobrantes());
	}

	@Test
	public void figuritasSobrantesTest() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(1, _albumDeVeinte, 2, 0, tipoEscenario.individual);
		simulacion.run();
		assertEquals(18, simulacion.getCantidadFigusSobrantes());
	}

}
