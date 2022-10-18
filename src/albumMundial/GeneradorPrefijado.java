package albumMundial;

public class GeneradorPrefijado implements Generador {
	private int contador = 0;

	public GeneradorPrefijado() {
		contador = 0;
	}

	@Override
	public int nextInt(int rango) {
		return contador < rango ? contador++ : rango;
	}

}
