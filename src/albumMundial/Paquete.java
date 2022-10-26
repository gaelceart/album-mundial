package albumMundial;

import generadores.Generador;


public class Paquete {
	private static Generador _random;

	// setter para el generador
	public static void setGenerador(Generador generador) {
		_random = generador;
	}

	public static Integer[] comprarPaquete(int cantFigus, Album album) {
		excepcionesComprarPaquete(cantFigus, album);
		Integer[] paquete = new Integer[cantFigus];
		for(int i = 0; i < cantFigus; i++)
			paquete[i] = -1;


		int index = 0;
		while (index < cantFigus) {
			int figuritaSeleccionada = _random.nextInt(album.getCantidadFiguritas());
			if (agregarFigurita(album, paquete, figuritaSeleccionada))
				paquete[index++] = figuritaSeleccionada;
		}
		return paquete;
	}

	private static boolean agregarFigurita(Album album, Integer[] paquete, int figuritaSeleccionada) {
		boolean esRara = album.esFiguRara(figuritaSeleccionada);
		boolean chanceDeAgregarFigurita = _random.nextBoolean();
		if (!paqueteContieneFigurita(paquete, figuritaSeleccionada) && esRara && chanceDeAgregarFigurita) 
			return true;

		if (!paqueteContieneFigurita(paquete, figuritaSeleccionada) && !esRara)
			return true;
	
		return false;
	}
	
	private static boolean paqueteContieneFigurita(Integer[] paquete, int f) {
		for(int i = 0; i < paquete.length; i++)
			if (paquete[i] == f)
				return true;
		return false;
	}

	private static void excepcionesComprarPaquete(int cantFigus, Album album) {
		if (cantFigus > album.getCantidadFiguritas()) 
			throw new IllegalArgumentException("Un paquete no puede contener mas figuritas que un album: " + cantFigus);
		
		if (cantFigus <= 0) 
			throw new IllegalArgumentException("Un paquete no puede contener 0 o menos figuritas: " + cantFigus);
		
	}

}
