package simulacion;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import albumMundial.Album;
import albumMundial.Paquete;
import generadores.GeneradorCuadruplePaquetes;
import generadores.GeneradorPaquetesDoblesEnOrden;
import generadores.GeneradorRandom;

public class SimulacionIndividualDosUsuariosTest {
	Album _album;

	@Before
	public void setAlbum() {
		_album = new Album(10, 0);
	}

	@Test
	public void CompletarAlbumTest() {
		Paquete.setGenerador(new GeneradorRandom());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test
	public void paquetesRepetidosTest() {
		Paquete.setGenerador(new GeneradorCuadruplePaquetes());
		Simulacion simulacion = new Simulacion(2, _album, 2, 1, tipoEscenario.individual);
		simulacion.run();
		assertEquals(18, simulacion.getCantidadPaquetesComprados());
	}

	@Test
	public void figuritasRepetidasTest() {
		Paquete.setGenerador(new GeneradorCuadruplePaquetes());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.individual);
		simulacion.run();
		assertEquals(16, simulacion.getCantidadFigusRepetidas());
	}

	@Test
	public void SinSobrantesTest() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.individual);
		simulacion.run();
		assertEquals(0, simulacion.getCantidadFigusSobrantes());
	}

	@Test
	public void figuritasSobrantesTest() {
		Paquete.setGenerador(new GeneradorCuadruplePaquetes());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.individual);
		simulacion.run();
		assertEquals(16, simulacion.getCantidadFigusSobrantes());
	}

}
