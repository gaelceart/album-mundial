package generadores;

public class GeneradorPaquetesDobles implements Generador {
	int cont = 0;
	int inicioAux = 0;
	int limite = 1;
	boolean repetir = true;

	@Override
	public int nextInt(int rango) {
		if (cont <= limite) {
			return cont++;
		}
		if (repetir) {
			cont = inicioAux;
			repetir = false;
			return cont++;
		}
		repetir = true;
		inicioAux = cont;
		limite += 2;
		return cont++;
	}

	@Override
	public boolean nextBoolean() {
		return true;
	}

}
