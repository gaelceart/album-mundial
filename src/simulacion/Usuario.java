package simulacion;

import java.util.LinkedList;
import java.util.List;
import albumMundial.*;

public class Usuario {
	private Album _album;
	private List<Figurita> _figuritasRepetidas;
	private int _paquetesComprados;
	
	public Usuario() {
		_album = new Album(638);
		_figuritasRepetidas = new LinkedList<>();
		_paquetesComprados = 0;
	}
	public static void main(String[] args) {
		Usuario[] users = new Usuario[2];
		for (int i = 0; i < users.length; i++)
			users[i] = new Usuario();
		
		int paquetesTotalesComprados = 0;
		int figusRepetidas = 0;
		while (!users[0].esAlbumCompleto()) {
			Paquete paquete = users[0].comprarPaquete(5);
			System.out.println(paquete);
			paquetesTotalesComprados++;
			for (Figurita f : paquete.getFiguritas()) {
				if (users[0].esFiguritaRepetida(f)) {
					figusRepetidas++;
				}
				else {
					users[0].pegarFigurita(f);
				}
			}
		}
		//System.out.println(album.getFiguritasEncontradas().size());
		System.out.println(paquetesTotalesComprados);
		System.out.println(figusRepetidas);
		System.out.println(users[0].getFiguritasRepetidas().size());
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
	
	public boolean esAlbumCompleto() {
		return _album.isCompleto();
	}
	
	public int getCantidadPaquetesComprados() {
		return _paquetesComprados;
	}
	
	public List<Figurita> getFiguritasRepetidas() {
		return _figuritasRepetidas;
	}
	
}
