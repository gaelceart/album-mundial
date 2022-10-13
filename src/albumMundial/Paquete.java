package albumMundial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Paquete {
	private int _Cantidadfiguritas;
	private List<Figurita> _figuritas;

	public Paquete(int figuritasPorPaquete) {
		if (figuritasPorPaquete <= 0) {
			throw new IllegalArgumentException(
					"Paquete debe contener mas de 0 figuritas por paquete:" + figuritasPorPaquete);
		}
		_Cantidadfiguritas = figuritasPorPaquete;
		_figuritas = new ArrayList<>();
		crearPaquete();
	}

	public void crearPaquete() {
		while (tamanoPaquete() < _Cantidadfiguritas) {
			Random random = null;
			int figuritaSeleccionada = random.nextInt(638);
			Figurita figurita = new Figurita(figuritaSeleccionada, false);
			if (!_figuritas.contains(figurita)) {
				_figuritas.add(figurita);
			}
		}
	}

	public int tamanoPaquete() {
		return _figuritas.size();
	}
}
