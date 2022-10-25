package albumMundial;

import java.util.ArrayList;

import generadores.Generador;


public class Album {
	private int _cantidadFiguritas;
	private int _cantidadFiguritasRaras;

	private ArrayList<Integer> _figuritasRaras;
	private boolean[] _figuritas;

	private boolean _completo;

	public static Generador _random;

	public Album(int cantidadFiguritas, int cantidadFigusRaras) {
		irep(cantidadFiguritas, cantidadFigusRaras);

		_cantidadFiguritas = cantidadFiguritas;
		_cantidadFiguritasRaras = cantidadFigusRaras;
		_figuritas = new boolean[cantidadFiguritas];
		_figuritasRaras = new ArrayList<>();
		seleccionFigusRaras();
	}

	private void irep(int cantidadFiguritas, int cantidadFigusRaras) {
		if (cantidadFiguritas <= 0) {
			throw new IllegalArgumentException(
					"Un Album no puede contener tener 0 o menos figuritas: " + cantidadFiguritas);
		}
		if (cantidadFigusRaras < 0) {
			throw new IllegalArgumentException(
					"Un Album no puede contener una cantidad de figuritas raras menores a 0: " + cantidadFigusRaras);
		}
		if (cantidadFigusRaras > cantidadFiguritas) {
			throw new IllegalArgumentException(
					"Un Album no puede tener mas figuritas raras que cantidad de figuritas en total: " + "FigusALbum: "
							+ cantidadFiguritas + "/ CantidadRaras: " + cantidadFigusRaras);
		}
	}

	public static void setGenerador(Generador generador) {
		_random = generador;
	}

	public void pegarFigurita(int n) {
		excepcionFueraDeRango(n);
		_figuritas[n] = true;
	}

	public boolean figuritaPegada(int n) {
		excepcionFueraDeRango(n);
		return _figuritas[n];
	}

	private void excepcionFueraDeRango(int n) {
		if (n < 0 || n >= _figuritas.length) {
			throw new IndexOutOfBoundsException("Fuera de rango: " + n);
		}
	}

	public boolean esFiguRara(int figurita) {
		return _figuritasRaras.contains(figurita);
	}

	public boolean esFiguritaRepetida(int n) {
		return _figuritas[n];
	}

	public int tamano() {
		return _figuritas.length;
	}

	public boolean checkEsCompleto() {
		for (int i = 0; i < _cantidadFiguritas; i++) {
			if (!_figuritas[i]) {
				_completo = false;
				return false;
			}
		}
		_completo = true;
		return true;
	}

	private void seleccionFigusRaras() {
		for (int i = 0; i < _cantidadFiguritasRaras; i++) {
			_figuritasRaras.add(i);
		}
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

	public int getCantidadFiguritasRaras() {
		return _cantidadFiguritasRaras;
	}

}