package simulacion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import albumMundial.*;

public class Usuario {

	private Album _album;
	private int _paquetesComprados;
	private ArrayList<Integer> _figuritasRepetidas;
	private int _cantidadFigusRepetidas;
	private int _cantidadFigusDonadas;
	private int _cantidadFigusIntercambiadas;
	private Set<Integer> _figusDescartadas;

	public Usuario() {
		_figuritasRepetidas = new ArrayList<>();
		_paquetesComprados = 0;
		_cantidadFigusRepetidas = 0;
		_figusDescartadas = new HashSet<>();
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
				_cantidadFigusRepetidas++;
			}
		}
	}

	public Album getAlbum() {
		return _album;
	}

	public boolean tieneAlbumCompleto() {
		_album.checkEsCompleto();
		return _album.isCompleto();
	}

	public boolean esFiguritaRepetida(int n) {
		return _album.esFiguritaRepetida(n);
	}

	public void setCantidadFigusRepetidasTotal(int _cantidadFigusRepetidasTotal) {
		this._cantidadFigusRepetidas = _cantidadFigusRepetidasTotal;
	}

	public void contarFiguritaIntercambiada() {
		_cantidadFigusIntercambiadas++;
	}

	public void contarFiguritaDonada() {
		_cantidadFigusDonadas++;
	}

	public int getCantidadPaquetesComprados() {
		return _paquetesComprados;
	}

	public ArrayList<Integer> getFiguritasRepetidas() {
		return _figuritasRepetidas;
	}

	public int getCantidadFigusRepetidas() {
		return _cantidadFigusRepetidas;
	}

	public int getCantidadFigusDonadas() {
		return _cantidadFigusDonadas;
	}

	public int getCantidadFigusIntercambiadas() {
		return _cantidadFigusIntercambiadas;
	}

	public HashSet<Integer> getFigusDescartadas() {
		return (HashSet<Integer>) _figusDescartadas;
	}

}
