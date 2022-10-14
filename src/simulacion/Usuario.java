package simulacion;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import albumMundial.*;

public class Usuario {
	private Album _album;
	private List<Figurita> _figuritasRepetidas;
	private int _paquetesComprados;
	private ArrayList<Integer> _figuritasRepetidasB;
	
	public Usuario(int cantFigusAlbum) {
		_album = new Album(cantFigusAlbum);
		_figuritasRepetidas = new LinkedList<>();
		_figuritasRepetidasB = new ArrayList<>();
		_paquetesComprados = 0;
	}

	public Paquete comprarPaquete(int cantFigus) {
		_paquetesComprados++;
		return new Paquete(cantFigus);
	}

	public Integer[] comprarPaqueteB(int cantFigus) {
		ArrayList<Integer> paquete = new ArrayList<>();
		Random random = new Random();
		while (paquete.size() < cantFigus) {
			int figuritaSeleccionada = random.nextInt(Album._cantidadFiguritas);
			if (!paquete.contains(figuritaSeleccionada))
				paquete.add(figuritaSeleccionada);
		}
		_paquetesComprados++;
		return paquete.toArray(new Integer[cantFigus]);
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

	public void pegarFigurita(int f) {
		_album.pegarFigurita(f);
	}
	
	public boolean tieneAlbumCompleto() {
		return _album.isCompleto();
	}
	
	public int getCantidadPaquetesComprados() {
		return _paquetesComprados;
	}
	
	public ArrayList<Integer> getFiguritasRepetidas() {
		return _figuritasRepetidasB;
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

	public void pegarFiguritas(Integer[] paquete) {
		for (int i = 0; i < paquete.length; i++) {
			if (_album.esFiguritaRepetida(paquete[i])) {
				_figuritasRepetidasB.add(paquete[i]);
			}
			else {
				pegarFigurita(paquete[i]);
			}
		}
	}
	
}
