package generadores;

public class GeneradorPaqueteEnOrden4VecesTrue implements Generador {

	int count = 0;

	@Override
	public int nextInt(int rango) {
		return count < rango ? count++ : rango;
	}

	int contadorTrue = 0;

	@Override
	public boolean nextBoolean() {
		if (contadorTrue++ < 4) {
			return true;
		}
		return false;
	}

}
