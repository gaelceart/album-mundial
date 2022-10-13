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
			Random random = new Random();
			int figuritaSeleccionada = random.nextInt(Album._cantidadFiguritas);
			Figurita figurita = new Figurita(figuritaSeleccionada, false);
			_figuritas.add(figurita);
		}
	}

	public List<Figurita> getFiguritas() {
		return _figuritas;
	}

	public int tamanoPaquete() {
		return _figuritas.size();
	}

	@Override
	public String toString() {
		return "Paquete [_cantidadFiguritas=" + _cantidadFiguritas + ", _figuritas=" + _figuritas + "]";
	}
	
}
