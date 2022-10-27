package generadores;

public class GeneradorCuadruplePaquetes implements Generador {
	int[] n = {0,1,0,1,0,1,0,1,
			   2,3,2,3,2,3,2,3,
			   4,5,4,5,4,5,4,5,
			   6,7,6,7,6,7,6,7,
			   8,9,8,9,8,9,8,9};
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
