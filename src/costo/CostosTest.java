package simulacion;

import static org.junit.Assert.*;

import org.junit.Test;

public class CostosTest {

	@Test(expected = IllegalArgumentException.class)
	public void cantidadNegativaTest() {
		Costos.calcularPromedio(-1, 1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cantidadUsuariosNegativosTest() {
		Costos.calcularPromedio(1, -1, 1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void cantidadSimulacionesNegativasTest() {
		Costos.calcularPromedio(1, 1, -1);
	}

	@Test(expected = ArithmeticException.class)
	public void dividirPorCeroTest() {
		Costos.calcularPromedio(0, 0, 0);
	}

	@Test
	public void promedioTest() {
		double promedio = Costos.calcularPromedio(100, 5, 5);
		assertEquals(4, promedio, 0);
	}
}
