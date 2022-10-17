package albumMundial;

public class Album {
	private int _cantidadFiguritas;
	private boolean[] _figuritas;
	private boolean _completo;

	public Album(int cantidadFiguritas) {
		if (cantidadFiguritas <= 0) {
			throw new IllegalArgumentException(
					"Un Album no puede contener tener 0 o menos figuritas: " + cantidadFiguritas);
		}
		_cantidadFiguritas = cantidadFiguritas;
		_figuritas = new boolean[cantidadFiguritas];
	}

	public void pegarFigurita(int n) {
		_figuritas[n] = true;
		if (checkEsCompleto())
			_completo = true;
	}

	private boolean checkEsCompleto() {
		for (int i = 0; i < _cantidadFiguritas; i++) {
			if (!_figuritas[i])
				return false;
		}
		return true;
	}

	public boolean esFiguritaRepetida(int n) {
		return _figuritas[n];
	}

	public int getCantidadFiguritas() {
		return _cantidadFiguritas;
	}

	public boolean isCompleto() {
		return _completo;
	}

}
