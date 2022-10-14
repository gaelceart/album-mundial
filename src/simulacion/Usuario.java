package simulacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import albumMundial.*;

public class Usuario {
	private Album _album;
	private List<Figurita> _figuritasRepetidas;
	private int _paquetesComprados;
	
	public Usuario(int cantFigusAlbum) {
		_album = new Album(cantFigusAlbum);
		_figuritasRepetidas = new LinkedList<>();
		_paquetesComprados = 0;
	}

	public Paquete comprarPaquete(int cantFigus) {
		_paquetesComprados++;
		return new Paquete(cantFigus);
	}
	
	public boolean esFiguritaRepetida(Figurita f) {
		if (!_album.esFiguritaRepetida(f))
			return false;
		_figuritasRepetidas.add(f);
		return true;
	}

	public void pegarFigurita(Figurita f) {
		_album.pegarFigurita(f);
	}
	
	public boolean tieneAlbumCompleto() {
		return _album.isCompleto();
	}
	
	public int getCantidadPaquetesComprados() {
		return _paquetesComprados;
	}
	
	public List<Figurita> getFiguritasRepetidas() {
		return _figuritasRepetidas;
	}

	public void pegarFiguritas(Paquete paquete) {
		for (Figurita f : paquete.abrirPaquete()) {
			if (_album.esFiguritaRepetida(f)) {
				_figuritasRepetidas.add(f);
			}
			else {
				pegarFigurita(f);
			}
		}
	}
	
}
