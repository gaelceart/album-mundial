package generadores;

public class GeneradorDonante implements Generador {
	int[] n = {0,1,0,1,2,3,0,1,2,3,0,1,4,5,0,1,4,5,0,1,6,7,0,1,6,7,0,1,8,9,8,9,2,3,4,5,6,7};
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
