package simulacion;

import static org.junit.Assert.*;

import org.junit.Test;

import albumMundial.Paquete;
import generadores.GeneradorCuadruplePaquetes;
import generadores.GeneradorPaquetesDoblesEnOrden;

public class SimulacionIndividualDosUsuariosTest {

	@Test
	public void CompletarAlbum() {
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(2, 10, 2, 2, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test
	public void paquetesRepetidosTest() {
		Paquete.setGenerador(new GeneradorCuadruplePaquetes());
		Simulacion simulacion = new Simulacion(2, 10, 0, 2, tipoEscenario.individual);
		simulacion.run();
		assertEquals(18, simulacion.getPaquetesTotalesComprados());
	}

	@Test
	public void figuritasRepetidasTest() {
		Paquete.setGenerador(new GeneradorCuadruplePaquetes());
		Simulacion simulacion = new Simulacion(2, 10, 0, 2, tipoEscenario.individual);
		simulacion.run();
		assertEquals(16, simulacion.getFiguritasSobrantes());
	}

	@Test
	public void costoPaquetesExactosTest() {

	}

	@Test
	public void costoPaquetesDoblesTest() {

	}
}
