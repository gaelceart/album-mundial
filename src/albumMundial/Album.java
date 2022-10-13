package albumMundial;

import java.util.HashMap;
import java.util.Map;

public class Album {
	private int _cantidadFiguritas;
	private Map<Integer, Boolean> _figuritasEncontradas;
	private boolean _completo;

	public Album(int cantidadFiguritas) {
		if (cantidadFiguritas <= 0) {
			throw new IllegalArgumentException("Un album no puede contener menos de 0 figuritas: " + cantidadFiguritas);
		}
		_cantidadFiguritas = cantidadFiguritas;
		_figuritasEncontradas = new HashMap<>();
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
