package generadores;

public class GeneradorIntercambiadorSobrantes implements Generador {
	int[] n = { 0, 1, 2, 3, 0, 1, 2, 3, 0, 1, 2, 3, 4, 5, 6, 7, 4, 5, 6, 7, 8, 9, 8, 9 };
	int puntero = 0;

	@Override
	public int nextInt(int rango) {
		return n[puntero++];
	}

	@Override
	public boolean nextBoolean() {
		return true;
	}

}
