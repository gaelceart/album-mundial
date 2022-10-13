package albumMundial;

import java.util.HashMap;
import java.util.Map;

public class Album {
	private int _cantidadFiguritas;
	private Map<Integer, Boolean> _figuritasEncontradas;
	private boolean _completo;

	public Album(int cantidadFiguritas) {
		if (cantidadFiguritas <= 0) {
			throw new IllegalArgumentException("Un Album no puede contener tener 0 o menos figuritas: " + cantidadFiguritas);
		}
		_cantidadFiguritas = cantidadFiguritas;
		_figuritasEncontradas = new HashMap<>();
	}

	public void pegarFigurita(Figurita figurita) {
		_figuritasEncontradas.put(figurita.getNumero(), true);
	}

	public boolean figuritaPegada(Figurita figurita) {
		if (_figuritasEncontradas.containsKey(figurita.getNumero())) {
			return _figuritasEncontradas.get(figurita.getNumero());
		}
		return false;
	}

	public int getCantidadFiguritas() {
		return _cantidadFiguritas;
	}

	public Map<Integer, Boolean> getFiguritasEncontradas() {
		return _figuritasEncontradas;
	}

	public boolean isCompleto() {
		return _completo;
	}

}
