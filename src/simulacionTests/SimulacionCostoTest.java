package simulacionTests;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import albumMundial.Album;
import albumMundial.Paquete;
import generadores.GeneradorCuadruplePaquetes;
import generadores.GeneradorDonante;
import generadores.GeneradorIntercambiador;
import generadores.GeneradorPaquetesDoblesEnOrden;
import generadores.GeneradorPaquetesEnOrdenTrue;
import simulacion.Simulacion;
import simulacion.tipoEscenario;

public class SimulacionCostoTest {
	private Album _albumDeDiez;

	@Before
	public void setearAlbum() {
		_albumDeDiez = new Album(10, 0);
	}

	@Test
	public void ConCosteCeroTest() {
		// Con album de 10 y paquetes de 2 figuritas con coste 0.
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, _albumDeDiez, 2, 0, tipoEscenario.individual);
		simulacion.run();
		assertEquals(0, simulacion.getCostoTotal());
	}

	@Test
	public void individualSinRepetidasTest() {
		// Con album de 10 y paquetes de 2 figuritas con coste 10.
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Simulacion simulacion = new Simulacion(1, _albumDeDiez, 2, 10, tipoEscenario.individual);
		simulacion.run();
		assertEquals(50, simulacion.getCostoTotal());
	}

	@Test
	public void individualConRepetidasTest() {
		// Con album de 10 y paquetes de 2 figuritas con coste 10.
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(1, _albumDeDiez, 2, 10, tipoEscenario.individual);
		simulacion.run();
		assertEquals(90, simulacion.getCostoTotal());

	}

	@Test
	public void individualConDosUsuariosSinRepetidasTest() {
		// Con album de 10 y paquetes de 2 figuritas con coste 10.
		Paquete.setGenerador(new GeneradorPaquetesDoblesEnOrden());
		Simulacion simulacion = new Simulacion(2, _albumDeDiez, 2, 10, tipoEscenario.individual);
		simulacion.run();
		assertEquals(100, simulacion.getCostoTotal());
	}

	@Test
	public void individualConDosUsuariosYRepetidasTest() {
		Paquete.setGenerador(new GeneradorCuadruplePaquetes());
		Simulacion simulacion = new Simulacion(2, _albumDeDiez, 2, 10, tipoEscenario.individual);
		simulacion.run();
		assertEquals(180, simulacion.getCostoTotal());
	}

	@Test
	public void intercambiandoTest() {
		Paquete.setGenerador(new GeneradorIntercambiador());
		Simulacion simulacion = new Simulacion(2, _albumDeDiez, 2, 10, tipoEscenario.intercambio);
		simulacion.run();
		assertEquals(100, simulacion.getCostoTotal());
	}

	@Test
	public void sinIntercambiarTest() {
		Paquete.setGenerador(new GeneradorIntercambiador());
		Simulacion simulacion = new Simulacion(2, _albumDeDiez, 2, 10, tipoEscenario.individual);
		simulacion.run();
		assertEquals(140, simulacion.getCostoTotal());
	}

	@Test
	public void conDonacionTest() {
		// Con album de 20 y paquetes de 2 figuritas con coste 10.
		Paquete.setGenerador(new GeneradorDonante());
		Simulacion simulacion = new Simulacion(2, _albumDeDiez, 2, 10, tipoEscenario.donacion);
		simulacion.run();
		assertEquals(160, simulacion.getCostoTotal());
	}
	
	@Test
	public void sinDonacionTest() {
		// Con album de 20 y paquetes de 2 figuritas con coste 10.
		Paquete.setGenerador(new GeneradorDonante());
		Simulacion simulacion = new Simulacion(2, _albumDeDiez, 2, 10, tipoEscenario.individual);
		simulacion.run();
		assertEquals(190, simulacion.getCostoTotal());
	}
}
