package simulacion;

import java.util.ArrayList;
import albumMundial.*;

public class Usuario {

	private Album _album;
	private int _paquetesComprados;
	private ArrayList<Integer> _figuritasRepetidas;
	private int _cantidadFigusRepetidasTotal;

	public Usuario() {
		_figuritasRepetidas = new ArrayList<>();
		_paquetesComprados = 0;
		_cantidadFigusRepetidasTotal = 0;
	}
<<<<<<< HEAD
	
	public void comprarAlbum(int cantFigusAlbum, int cantFigusRaras) {
		_album = new Album(cantFigusAlbum, cantFigusRaras);
	}
	
=======

>>>>>>> fr_b
	public Integer[] comprarPaquete(int cantFigus) {
		_paquetesComprados++;
		return Paquete.comprarPaquete(cantFigus, _album);
	}

	public void pegarFigurita(int f) {
		_album.pegarFigurita(f);
		_album.checkEsCompleto();
	}

	public boolean tieneAlbumCompleto() {
		_album.checkEsCompleto();
		return _album.isCompleto();
	}

	public int getCantidadPaquetesComprados() {
		return _paquetesComprados;
	}

	public ArrayList<Integer> getFiguritasRepetidas() {
		return _figuritasRepetidas;
	}

	public boolean esFiguritaRepetida(int n) {
		return _album.esFiguritaRepetida(n);
	}

	public void pegarFiguritas(Integer[] paquete) {
		for (int i = 0; i < paquete.length; i++) {
			if (_album.esFiguritaRepetida(paquete[i])) {
				_figuritasRepetidas.add(paquete[i]);
				_cantidadFigusRepetidasTotal = getCantidadFigusRepetidasTotal() + 1;
			} else {
				pegarFigurita(paquete[i]);
				System.out.println("PEGUÉ figu " + paquete[i]);
			}
		}
	}

	public int getCantidadFigusRepetidasTotal() {
		return _cantidadFigusRepetidasTotal;
	}

}
