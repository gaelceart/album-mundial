package generadores;

public class GeneradorPaquetesEnOrdenTrue implements Generador {
	int count = 0;

	@Override
	public int nextInt(int rango) {
		return count < rango ? count++ : rango;
	}

	@Override
	public boolean nextBoolean() {
		return true;
	}

}
