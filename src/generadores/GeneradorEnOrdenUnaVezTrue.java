package generadores;

public class GeneradorEnOrdenUnaVezTrue implements Generador {
	int count = 0;
	
	@Override
	public int nextInt(int rango) {
		return count < rango ? count++ : rango;
	}

	boolean aux = true;

	@Override
	public boolean nextBoolean() {
		if (aux) {
			aux = false;
			return true;
		}
		return false;
	}

}
