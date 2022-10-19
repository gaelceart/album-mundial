package albumMundial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Paquete {
	private static Generador _random;

	// setter para el generador
	public static void setGenerador(Generador generador) {
		_random = generador;
	}

	public static void crearGeneradorAleatorio() {
		setGenerador(new GeneradorRandom(new Random()));
	}

	public static Integer[] comprarPaquete(int cantFigus, Album album) {
		List<Integer> paquete = new ArrayList<>();
		while (paquete.size() < cantFigus) {
			int figuritaSeleccionada = _random.nextInt(album.getCantidadFiguritas());
			agregarFigurita(album, paquete, figuritaSeleccionada);
		}
		return paquete.toArray(new Integer[cantFigus]);
	}

	private static void agregarFigurita(Album album, List<Integer> paquete, int figuritaSeleccionada) {
		boolean esRara = album.esFiguRara(figuritaSeleccionada);
		// las cartas raras tienen 20% de posibilidades de ser agregada al paquete.
		if (!paquete.contains(figuritaSeleccionada) && esRara && _random.nextInt(10) < 2) {
			paquete.add(figuritaSeleccionada);
		} else if (!paquete.contains(figuritaSeleccionada) && !esRara) {
			paquete.add(figuritaSeleccionada);
		}
	}

}
