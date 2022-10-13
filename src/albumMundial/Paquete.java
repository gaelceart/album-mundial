package albumMundial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Paquete {
	private int _cantidadFiguritas;
	private List<Figurita> _figuritas;

	public Paquete(int figuritasPorPaquete) {
		if (figuritasPorPaquete <= 0) {
			throw new IllegalArgumentException(
					"Un Paquete no puede contener tener 0 o menos figuritas:" + figuritasPorPaquete);
		}
		_cantidadFiguritas = figuritasPorPaquete;
		_figuritas = new ArrayList<>();
		crearPaquete();
	}

	// crearPaquete(int figuritasPosibles)? o genera acomplamiento
	public void crearPaquete() {
		while (tamanoPaquete() < _cantidadFiguritas) {
			Figurita figurita = crearFiguritaAleatoriaComun();
			if(!_figuritas.contains(figurita))
				_figuritas.add(figurita);
		}
	}

	private Figurita crearFiguritaAleatoriaComun() {
		Random random = new Random();
		int figuritaSeleccionada = random.nextInt(Album._cantidadFiguritas);
		Figurita figurita = new Figurita(figuritaSeleccionada, false);
		return figurita;
	}
	
	public List<Figurita> getFiguritas() {
		return _figuritas;
	}

	public int tamanoPaquete() {
		return _figuritas.size();
	}

	@Override
	public String toString() {
		return "\nPaquete de "+ _cantidadFiguritas + "\n[" + " figuritas =\n" + _figuritas + "]";
	}
	
}
