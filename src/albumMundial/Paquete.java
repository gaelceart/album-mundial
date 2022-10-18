package albumMundial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Paquete {
	private static Random random = new Random();

	public static Integer[] comprarPaquete(int cantFigus, Album album) {
		List<Integer> paquete = new ArrayList<>();
		while (paquete.size() < cantFigus) {
			int figuritaSeleccionada = random.nextInt(album.getCantidadFiguritas());
			agregarFigurita(album, paquete, figuritaSeleccionada);
		}
		return paquete.toArray(new Integer[cantFigus]);
	}

	private static void agregarFigurita(Album album, List<Integer> paquete, int figuritaSeleccionada) {
		boolean esRara = esFiguRara(figuritaSeleccionada, album);
		// las cartas raras tienen 20% de posibilidades de ser agregada al paquete.
		if (!paquete.contains(figuritaSeleccionada) && esRara && random.nextInt(10) < 2) {
			paquete.add(figuritaSeleccionada);
		} else if (!paquete.contains(figuritaSeleccionada) && !esRara) {
			paquete.add(figuritaSeleccionada);
		}
	}

	public static boolean esFiguRara(int figurita, Album album) {
		return album.getFiguritasRaras().contains(figurita);
	}

}
