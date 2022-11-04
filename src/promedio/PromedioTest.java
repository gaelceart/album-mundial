package promedio;

import static org.junit.Assert.*;

import org.junit.Test;

public class PromedioTest {

	@Test(expected = IllegalArgumentException.class)
	public void cantidadNegativaTest() {
		Promedio.calcularPromedio(-1, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cantidadUsuariosNegativosTest() {
		Promedio.calcularPromedio(1, -1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cantidadSimulacionesNegativasTest() {
		Promedio.calcularPromedio(1, 1, -1);
	}

	@Test(expected = ArithmeticException.class)
	public void dividirPorCeroTest() {
		Promedio.calcularPromedio(0, 0, 0);
	}

	@Test
	public void promedioTest() {
		double promedio = Promedio.calcularPromedio(100, 5, 5);
		assertEquals(4, promedio, 0);
	}
}