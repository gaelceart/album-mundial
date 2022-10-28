package generadores;

public class GeneradoresPaquetesEnOrdenFalse implements Generador {
	int count = 0;

	@Override
	public int nextInt(int rango) {
		return count < rango ? count++ : rango;
	}

	@Override
	public boolean nextBoolean() {
		return false;

	}
}