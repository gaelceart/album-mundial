package generadores;

public class GeneradorDonante implements Generador {
	int[] figusUsuario1 = { 0, 1, 2, 3, 2, 3, 4, 5, 4, 5, 6, 7, 6, 7, 8, 9};
	int puntero = 0;
	int figusEnPaquete = 0;
	int usuario = 1;
	int aux = 8;
	@Override
	public int nextInt(int rango) {
		if (puntero >=16) {
			return aux++;
		}
		if (usuario == 1) {
			if (figusEnPaquete == 2) {
				figusEnPaquete = 0;
				usuario = 2;
				System.out.println("-");
				System.out.println(0);
				return 0;
			}
			System.out.println(figusUsuario1[puntero]);
			++figusEnPaquete;
			return figusUsuario1[puntero++];
		}
		usuario = 1;
		System.out.println(1);
		System.out.println("-");
		return 1;
	}

	@Override
	public boolean nextBoolean() {
		return true;
	}

}
