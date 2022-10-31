package albumMundial;

import java.util.ArrayList;
import java.util.List;
import generadores.Generador;

public class Paquete {
	private static Generador _random;

	// setter para el generador
	public static void setGenerador(Generador generador) {
		_random = generador;
	}

	public static Integer[] comprarPaquete(int cantFigus, Album album) {
		excepcionesComprarPaquete(cantFigus, album);
		List<Integer> paquete = new ArrayList<>();
		while (paquete.size() < cantFigus) {
			int figuritaSeleccionada = _random.nextInt(album.getCantidadFiguritas());
			agregarFigurita(album, paquete, figuritaSeleccionada);
		}
		return paquete.toArray(new Integer[cantFigus]);
	}

	private static void agregarFigurita(Album album, List<Integer> paquete, int figuritaSeleccionada) {
		boolean esRara = album.esFiguRara(figuritaSeleccionada);
		if (!paquete.contains(figuritaSeleccionada) && esRara && _random.nextBoolean()) {
			paquete.add(figuritaSeleccionada);
		} else if (!paquete.contains(figuritaSeleccionada) && !esRara) {
			paquete.add(figuritaSeleccionada);
		}
	}

	private static void excepcionesComprarPaquete(int cantFigus, Album album) {
		if (cantFigus > album.getCantidadFiguritas()) {
			throw new IllegalArgumentException("Un paquete no puede contener mas figuritas que un album: " + cantFigus);
		}
		if (cantFigus <= 0) {
			throw new IllegalArgumentException("Un paquete no puede contener 0 o menos figuritas: " + cantFigus);
		}
	}

}
