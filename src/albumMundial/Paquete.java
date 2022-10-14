package albumMundial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Paquete {
	private int _cantidadFiguritas;
	private List<Figurita> _figuritas;

	public Paquete(int figuritasPorPaquete) {
		if (figuritasPorPaquete <= 0) 
			throw new IllegalArgumentException(
					"Un Paquete no puede contener tener 0 o menos figuritas:" + figuritasPorPaquete);
		
		if (figuritasPorPaquete > Album._cantidadFiguritas)
			throw new IllegalArgumentException("Un paquete no puede contener mas figuritas que el album: Cant. figus paquete = " + figuritasPorPaquete + " > Cant. figus album = " + Album._cantidadFiguritas);
		
		_cantidadFiguritas = figuritasPorPaquete;
		_figuritas = new ArrayList<>();
		crearPaquete();
	}

	// crearPaquete(int figuritasPosibles)? o genera acomplamiento
	public void crearPaquete() {
		while (tamanoPaquete() < _cantidadFiguritas) {
			Figurita figurita = generarFigurita();
			agregarFigurita(figurita);
		}
	}

	private Figurita generarFigurita() {
		Random random = new Random();
		int figuritaSeleccionada = random.nextInt(Album._cantidadFiguritas);
		Figurita figurita = new Figurita(figuritaSeleccionada, false);
		return figurita;
	}
	
	private void agregarFigurita(Figurita f) {
			if (tamanoPaquete() == 0)
				_figuritas.add(f);

			for (int i = 0; i < tamanoPaquete(); i++) {
				if (!f.equals(_figuritas.get(i)))
					_figuritas.add(f);
			}
	}

	public List<Figurita> abrirPaquete() {
		return _figuritas;
	}

	public int tamanoPaquete() {
		return _figuritas.size();
	}

	@Override
	public String toString() {
		return "Paquete [_cantidadFiguritas=" + _cantidadFiguritas + ", _figuritas=" + _figuritas + "]\n";
	}
    
}
