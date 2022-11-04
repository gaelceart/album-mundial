package promedio;

public class Promedio {
	static public double calcularPromedio(double cantidad, double cantidadSimulaciones, double cantidadUsuarios) {
		if (cantidad < 0 || cantidadUsuarios < 0 || cantidadSimulaciones < 0) {
			throw new IllegalArgumentException("No puede existir parametros negativos: Cantidad: " + cantidad
					+ " cantidadUsuario: " + cantidadUsuarios + " cantidadSimulaciones: " + cantidadSimulaciones);
		}
		if (cantidadUsuarios * cantidadSimulaciones == 0) {
			throw new ArithmeticException("No se puede dividir por cero");
		}
		return cantidad / (cantidadUsuarios * cantidadSimulaciones);
	}

}
