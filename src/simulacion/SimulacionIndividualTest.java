package simulacion;

import static org.junit.Assert.*;

import org.junit.Test;

import albumMundial.Paquete;
import generadores.Generador;

public class SimulacionIndividualTest {

	@Test
	public void completarAlbumTest() {
		Paquete.setGenerador(generadorPrefijado());
		Simulacion simulacion = new Simulacion(1, 100, 5, 5, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void completarAlbumNuloTest() {
		Paquete.setGenerador(generadorPrefijado());
		Simulacion simulacion = new Simulacion(1, 0, 0, 0, tipoEscenario.individual);
		simulacion.run();
		assertTrue(simulacion.albumesCompletos());
	}

	@Test
	public void comprarPaqueteConUnaFiguritaTest() {
		Paquete.setGenerador(generadorPrefijado());
		Simulacion simulacion = new Simulacion(1, 100, 1, 1, tipoEscenario.individual);
		simulacion.run();
		assertEquals(100, simulacion.getPaquetesTotalesComprados());
	}

	@Test
	public void comprarPaquetesJustosTest() {
		Paquete.setGenerador(generadorPrefijado());
		Simulacion simulacion = new Simulacion(1, 100, 5, 5, tipoEscenario.individual);
		simulacion.run();
		assertEquals(20, simulacion.getPaquetesTotalesComprados());
	}

	@Test
	public void paquetesRepetidosTest() {
		Paquete.setGenerador(generadorRepetidor());
		Simulacion simulacion = new Simulacion(1, 20, 2, 2, tipoEscenario.individual);
		simulacion.run();
		assertEquals(19, simulacion.getPaquetesTotalesComprados());

	}

	@Test
	public void completarAlbumSinSobrantesTest() {
		Paquete.setGenerador(generadorPrefijado());
		Simulacion simulacion = new Simulacion(1,20,20,20, tipoEscenario.individual);
		simulacion.run();
		assertEquals(0, simulacion.getFiguritasSobrantes());
	}

	@Test
	public void figuritasSobrantesTest() {
		Paquete.setGenerador(generadorRepetidor());
		Simulacion simulacion = new Simulacion(1, 20, 2, 2, tipoEscenario.individual);
		simulacion.run();
		assertEquals(18, simulacion.getFiguritasSobrantes());
	}

	private Generador generadorRepetidor() {
		Generador generador = new Generador() {
			int cont = 0;
			int inicioAux = 0;
			int limite = 1;
			boolean repetir = true;

			@Override
			public int nextInt(int rango) {
				if (cont < limite) {
					return cont++;
				}
				if (repetir) {
					cont = inicioAux;
					repetir = false;
					return cont++;
				}
				limite += 2;
				inicioAux = cont;
				repetir = true;
				return cont++;

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
