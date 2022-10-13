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
	
	public void descartarRepetidas(Paquete p) {
		List<Figurita> figus = p.getFiguritas();
		descartarRepetidasEnMano(figus);
		descartarRepetidasEnAlbum(figus);
	}
	
	private void descartarRepetidasEnAlbum(List<Figurita> figus) {
		Iterator<Figurita> it = figus.iterator();
		while (it.hasNext()) {
			Figurita f = it.next();
			
			if (esFiguritaRepetida(f))
				it.remove();
		}
	}


	private void descartarRepetidasEnMano(List<Figurita> figus) {
		ArrayList<Integer> repetidasEnMano = new ArrayList<>();
		for (int i = 0; i < figus.size(); i++) {
			for (int j = i+1; j < figus.size(); j++) {
				if (repetidasEnMano.contains(i))
					break;
				
				if (figus.get(i).equals(figus.get(j))) {
					repetidasEnMano.add(j);
					break;
				}
			}
		}

		Iterator<Figurita> it = figus.iterator();
		int index = 0;
		while (it.hasNext()) {
			Figurita f = it.next();
			if (repetidasEnMano.contains(index)) {
				_figuritasRepetidas.add(f);
				it.remove();
			}
			index++;
		}
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
		for (Figurita f : paquete.getFiguritas())
			pegarFigurita(f);
	}
	
}
