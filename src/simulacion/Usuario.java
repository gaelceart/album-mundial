package simulacion;

import java.util.ArrayList;
import albumMundial.*;

public class Usuario {

	private Album _album;
	private int _paquetesComprados;
	private ArrayList<Integer> _figuritasRepetidas;
	private int _cantidadFigusRepetidasTotal;

	public void setCantidadFigusRepetidasTotal(int _cantidadFigusRepetidasTotal) {
		this._cantidadFigusRepetidasTotal = _cantidadFigusRepetidasTotal;
	}

	public Usuario() {
		_figuritasRepetidas = new ArrayList<>();
		_paquetesComprados = 0;
		_cantidadFigusRepetidasTotal = 0;
	}

	public void comprarAlbum(int cantFigusAlbum, int cantFigusRaras) {
		_album = new Album(cantFigusAlbum, cantFigusRaras);
	}

	public Integer[] comprarPaquete(int cantFigus) {
		_paquetesComprados++;
		return Paquete.comprarPaquete(cantFigus, _album);
	}

	public void pegarFigurita(int f) {
		_album.pegarFigurita(f);
		_album.checkEsCompleto();
	}

	public void pegarFiguritas(Integer[] paquete) {
		for (Integer figurita : paquete) {
			if (!_album.figuritaPegada(figurita)) {
				_album.pegarFigurita(figurita);
			} else {
				_figuritasRepetidas.add(figurita);
				_cantidadFigusRepetidasTotal++;
			}
		}
	}

	public boolean tieneAlbumCompleto() {
		_album.checkEsCompleto();
		return _album.isCompleto();
	}

	public boolean esFiguritaRepetida(int n) {
		return _album.esFiguritaRepetida(n);
	}
	
	public int getCantidadPaquetesComprados() {
		return _paquetesComprados;
	}

	public ArrayList<Integer> getFiguritasRepetidas() {
		return _figuritasRepetidas;
	}

	public int getCantidadFigusRepetidasTotal() {
		return _cantidadFigusRepetidasTotal;
	}

}
