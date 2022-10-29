package simulacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import albumMundial.Album;
import albumMundial.Paquete;
import generadores.GeneradorCuadruplePaquetes;
import generadores.GeneradorDonante;
import generadores.GeneradorPaquetesDoblesEnOrden;
import generadores.GeneradorRandom;

public class SimulacionDonacionTest {
	Album _album;

	@Before
	public void setAlbum() {
		_album = new Album(10, 0);
	}

	@Test
	public void completarAlbumTest() {
		Paquete.setGenerador(new GeneradorRandom());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.donacion);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test
	public void completarAlbumSinDonarTest() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.donacion);
		simulacion.run();
		assertEquals(0, simulacion.getCantidadFigusDonadas());
	}

	@Test
	public void paquetesRepetidosTest() {
		Paquete.setGenerador(new GeneradorCuadruplePaquetes());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.donacion);
		simulacion.run();
		assertEquals(18, simulacion.getCantidadPaquetesComprados());
	}

	@Test
	public void figuritasSobrantesTest() {
		Paquete.setGenerador(new GeneradorDonante());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.donacion);
		simulacion.run();
		assertEquals(12, simulacion.getCantidadFigusSobrantes());
	}

	@Test
	public void figuritasDonadasTest() {
		Paquete.setGenerador(new GeneradorDonante());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.donacion);
		simulacion.run();
		assertEquals(6, simulacion.getCantidadFigusDonadas());
	}

	@Test
	public void figuritasRepetidasTest() {
		Paquete.setGenerador(new GeneradorCuadruplePaquetes());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.donacion);
		simulacion.run();
		assertEquals(16, simulacion.getCantidadFigusRepetidas());
	}
}