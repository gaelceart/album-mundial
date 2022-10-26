package generadores;

public class GeneradorCuadruplePaquetes implements Generador {
	int cont = 0;
	int inicioAux = 0;
	int limite = 1;
	int repetir = 3;

	@Override
	public int nextInt(int rango) {
		if (cont <= limite) {
			return cont++;
		}
		if (repetir > 0) {
			cont = inicioAux;
			repetir--;
			return cont++;
		}
		repetir = 3;
		inicioAux = cont;
		limite += 2;
		return cont++;

	}

	@Override
	public boolean nextBoolean() {
		return true;
	}

}
