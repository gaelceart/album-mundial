package albumMundial;

import java.util.ArrayList;
import java.util.Random;

public class Album {
	private int _cantidadFiguritas;
	private boolean[] _figuritas;
	private boolean _completo;
	private ArrayList<Integer> _figuritasRaras;
	private Random _random;

	public Album(int cantidadFiguritas) {
		if (cantidadFiguritas <= 0) {
			throw new IllegalArgumentException(
					"Un Album no puede contener tener 0 o menos figuritas: " + cantidadFiguritas);
		}
		_random = new Random();
		_cantidadFiguritas = cantidadFiguritas;
		_figuritas = new boolean[cantidadFiguritas];
		agregarFigusRaras();
	}

	private void agregarFigusRaras() {
		// cada 15 figuritas 1 es rara.
		_figuritasRaras = new ArrayList<>();
		int cantFigusRaras = _cantidadFiguritas / 15;
		generarFiguritasRaras(cantFigusRaras);
	}

	private void generarFiguritasRaras(int cantFigusRaras) {
		while (_figuritasRaras.size() != cantFigusRaras) {
			int figuRara = _random.nextInt(_cantidadFiguritas);
			if (!_figuritasRaras.contains(figuRara)) {
				_figuritasRaras.add(figuRara);
			}
		}
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

	public ArrayList<Integer> getFiguritasRaras() {
		return _figuritasRaras;
	}
}
