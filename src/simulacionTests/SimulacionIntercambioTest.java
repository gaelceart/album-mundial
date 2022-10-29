package simulacionTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import albumMundial.Album;
import albumMundial.Paquete;
import generadores.GeneradorIntercambiador;
import generadores.GeneradorIntercambiadorSobrantes;
import generadores.GeneradorPaquetesDoblesEnOrden;
import simulacion.Simulacion;
import simulacion.tipoEscenario;

public class SimulacionIntercambioTest {
	private Album _album;

	@Before
	public void setAlbum() {
		_album = new Album(10, 0);
	}

	@Test
	public void completarAlbumTest() {
		Paquete.setGenerador(new GeneradorIntercambiador());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.intercambio);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test
	public void completarSinIntercambiarTest() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(0, simulacion.getcantidadIntercambiosRealizados());
	}

	@Test
	public void completarIntercambiandoTest() {
		Paquete.setGenerador(new GeneradorIntercambiador());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(4, simulacion.getcantidadIntercambiosRealizados());
	}

	@Test
	public void cantidadFiguritasIntercambiadasTest() {
		Paquete.setGenerador(new GeneradorIntercambiador());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(8, simulacion.getCantidadFigusIntercambiadas());
	}

	@Test
	public void figuritasRepetidasTest() {
		Paquete.setGenerador(new GeneradorIntercambiador());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(8, simulacion.getCantidadFigusRepetidas());
	}

	@Test
	public void figuritasSobrantesTest() {
		Paquete.setGenerador(new GeneradorIntercambiadorSobrantes());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(4, simulacion.getCantidadFigusSobrantes());
	}

	@Test
	public void sinSobrantesTest() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(2, _album, 2, 0, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(0, simulacion.getCantidadFigusSobrantes());
	}
}
