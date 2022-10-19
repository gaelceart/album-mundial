package albumMundial;

import java.util.ArrayList;
import java.util.Random;

public class Album {
	private int _cantidadFiguritas;
	private boolean[] _figuritas;
	private boolean _completo;
	private ArrayList<Integer> _figuritasRaras;
	private static Generador _random;

	public Album(int cantidadFiguritas) {
		if (cantidadFiguritas <= 0) {
			throw new IllegalArgumentException(
					"Un Album no puede contener tener 0 o menos figuritas: " + cantidadFiguritas);
		}
		_cantidadFiguritas = cantidadFiguritas;
		_figuritas = new boolean[cantidadFiguritas];
		agregarFigusRaras();
	}

	public static void setGenerador(Generador generador) {
		_random = generador;
	}

	public static void crearGeneradorAleatorio() {
		setGenerador(new GeneradorRandom(new Random()));
	}

	public void pegarFigurita(int n) {
		_figuritas[n] = true;
		if (checkEsCompleto())
			_completo = true;
	}

	public boolean esFiguRara(int figurita) {
		return _figuritasRaras.contains(figurita);
	}

	public boolean esFiguritaRepetida(int n) {
		return _figuritas[n];
	}

	private void agregarFigusRaras() {
		// cada 15 figuritas 1 es rara.
		_figuritasRaras = new ArrayList<>();
		int cantFigusRaras = _cantidadFiguritas / 15;
		System.out.println("Cant figus raras :" + cantFigusRaras);
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

	private boolean checkEsCompleto() {
		for (int i = 0; i < _cantidadFiguritas; i++) {
			if (!_figuritas[i])
				return false;
		}
		return true;
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
