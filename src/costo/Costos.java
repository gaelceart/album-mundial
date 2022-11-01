package costo;

public class Costos {

	static public double calcularPromedio(double cantidad, double cantidadUsuario, double cantidadSimulaciones) {
		if (cantidad < 0 || cantidadUsuario < 0 || cantidadSimulaciones < 0) {
			throw new IllegalArgumentException("No puede existir parametros negativos: Cantidad: " + cantidad
					+ " cantidadUsuario: " + cantidadUsuario + " cantidadSimulaciones: " + cantidadSimulaciones);
		}
		if (cantidadUsuario * cantidadSimulaciones == 0) {
			throw new ArithmeticException("No se puede dividir por cero");
		}
		return cantidad / (cantidadUsuario * cantidadSimulaciones);
	}

}
