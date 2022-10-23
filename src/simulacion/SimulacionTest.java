package simulacion;

import static org.junit.Assert.*;

import org.junit.Test;

import albumMundial.Album;
import albumMundial.Paquete;
import generadores.Generador;

public class SimulacionTest {

	@Test
	public void comprarPaquetesJustosTest() {
		Album.setGenerador(generadorPrefijado());
		Paquete.setGenerador(generadorPrefijado());
		Simulacion simulacion = new Simulacion(1, 100, 5, 5, tipoEscenario.individual);
		simulacion.run();
		assertEquals(20, simulacion.getPaquetesTotalesComprados(), 0);
	}

	@Test
	public void unAlbumCompletoTest() {
		Album.setGenerador(generadorPrefijado());
		Paquete.setGenerador(generadorPrefijado());
		Simulacion simulacion = new Simulacion(1, 100, 5, 5, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());

	}
	
	@Test
	public void dobleDePaquetesCompradosTest() {
		Album.setGenerador(generadorPrefijado());
		Paquete.setGenerador(generadorPaquetesDobles());
		Simulacion simulacion = new Simulacion(1, 100, 5, 5, tipoEscenario.individual);
		simulacion.run();
		assertEquals(40,simulacion.getPaquetesTotalesComprados(),0);

	}
	private Generador generadorPaquetesDobles() {
		Generador generador = new Generador() {
			int aux = 4;
			int count = 0;
			boolean seRepitio = false;
			@Override
			public int nextInt(int rango) {
				//me fui a dormir
				return 1;
				
			}

			@Override
			public boolean nextBoolean() {
				return true;
			}
		};
		return generador;
	}

	private Generador generadorPrefijado() {
		Generador generador = new Generador() {
			int count = 0;

			@Override
			public int nextInt(int rango) {

				return count < rango ? count++ : rango;
			}

			@Override
			public boolean nextBoolean() {
				return true;
			}
		};
		return generador;
	}

}
