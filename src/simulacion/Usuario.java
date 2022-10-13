package simulacion;

import java.util.List;
import albumMundial.*;

public class Usuario {
	private Album _album;
	private List<Figurita> _figuritasRepetidas;
	private int _paquetesComprados;
	
	public Usuario() {
		
	}
	public static void main(String[] args) {
		Album album = new Album(638);
		int paquetesComprados = 0;
		int figusRepetidas = 0;
		while (!album.isCompleto()) {
			Paquete paquete = new Paquete(5);
			System.out.println(paquete);
			for (Figurita f : paquete.getFiguritas()) {
				if (album.figuritaPegada(f)) {
					figusRepetidas++;
				}
				else {
					album.pegarFigurita(f);
				}
			}
			paquetesComprados++;
		}
		System.out.println(album.getFiguritasEncontradas().size());
		System.out.println(paquetesComprados);
		System.out.println(figusRepetidas);
		System.out.println(album);
	}
}
