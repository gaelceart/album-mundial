package albumMundial;

import java.util.HashSet;
import java.util.Set;

public class Album {
	//esta bien esto?
	static int _cantidadFiguritas;
	private Set<Figurita> _figuritasEncontradas;
	private boolean _completo;

	public Album(int cantidadFiguritas) {
		if (cantidadFiguritas <= 0) {
			throw new IllegalArgumentException(
					"Un Album no puede contener tener 0 o menos figuritas: " + cantidadFiguritas);
		}
		_cantidadFiguritas = cantidadFiguritas;
		_figuritasEncontradas = new HashSet<>();
	}

	public void pegarFigurita(Figurita figurita) {
		_figuritasEncontradas.add(figurita);
		if (_figuritasEncontradas.size() == _cantidadFiguritas) {
			_completo = true;
		}
	}

	public boolean esFiguritaRepetida(Figurita figurita) {
		for (Figurita figuritaPegada : _figuritasEncontradas) {
			if (figuritaPegada.equals(figurita)) {
				return true;
			}
		}
		return false;
	}

	public int getCantidadFiguritas() {
		return _cantidadFiguritas;
	}

	public Set<Figurita> getFiguritasEncontradas() {
		return _figuritasEncontradas;
	}

	public boolean isCompleto() {
		return _completo;
	}

}
