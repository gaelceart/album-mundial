package generadores;

public class GeneradorPaquetesEnOrdenTrue implements Generador {
	int count = 0;

	@Override
	public int nextInt(int rango) {
		System.out.println(count);
		return count < rango ? count++ : rango;
	}

	@Override
	public boolean nextBoolean() {
		return true;
	}

}
