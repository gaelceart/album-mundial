package generadores;

public class GeneradorPaquetesDoblesEnOrden implements Generador {
	int [] n = {0,1,0,1,2,3,2,3,4,5,4,5,6,7,6,7,8,9,8,9,10,11,10,11,12,13,12,13,14,15,14,15,16,17,16,17,18,19,18,19};
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
