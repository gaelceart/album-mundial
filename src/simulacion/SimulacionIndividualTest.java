package simulacion;

import static org.junit.Assert.*;

import org.junit.Test;

import albumMundial.Paquete;
import generadores.Generador;

public class SimulacionIndividualTest {

	@Test
	public void comprarPaquetesJustosTest() {
		Paquete.setGenerador(generadorPrefijado());
		Simulacion simulacion = new Simulacion(1, 100, 5, 5, tipoEscenario.individual);
		simulacion.run();
		assertEquals(20, simulacion.getPaquetesTotalesComprados(), 0);
	}

	@Test
	public void dobleDePaquetesCompradosTest() {
		Paquete.setGenerador(generadorPaquetesDobles());
		Simulacion simulacion = new Simulacion(1, 100, 5, 5, tipoEscenario.individual);
		simulacion.run();
		assertEquals(40, simulacion.getPaquetesTotalesComprados(), 0);

	}

	private Generador generadorPaquetesDobles() {
		Generador generador = new Generador() {
			int cont = 0;
			int limite = 4;
			boolean repetir = true;

			@Override
			public int nextInt(int rango) {
				if (cont < limite) {
					return cont++;
				}
				if (repetir) {
					cont = 0;
					repetir = false;
					return cont++;
				}
				limite += 5;
				repetir = true;
				return cont++;
			}

			@Override
			public boolean nextBoolean() {
				// TODO Auto-generated method stub
				return false;
			}

		};
		return null;
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
