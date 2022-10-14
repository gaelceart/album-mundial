package simulacion;

import java.util.ArrayList;
import java.util.HashMap;

import albumMundial.Figurita;
import albumMundial.Paquete;

public class Simulacion {
	private Usuario[] _users; 
	private int _cantidadFigusPorPaquete;
	private	int _paquetesTotalesComprados;
	private	int _figusTotalesRepetidas;
	private int _cantidadFigusAlbum;

	public Simulacion(int cantUsuarios) {
		_cantidadFigusAlbum = 638;
		
		_users = new Usuario[cantUsuarios];
		for (int i = 0; i < _users.length; i++)
			_users[i] = new Usuario(_cantidadFigusAlbum);
		
		_paquetesTotalesComprados = 0;
		_figusTotalesRepetidas = 0;
		_cantidadFigusPorPaquete = 5;
	}
	
	public static void main(String[] args) {
		Simulacion s = new Simulacion(50000);
		while (!s.albumesCompletos()) {
			//Fase 1 Comprar paquetes
			//HashMap<Integer, Paquete> paquetes = s.comprarPaquetes(s._cantidadFigusPorPaquete);
			HashMap<Integer, Integer[]> paquetes = s.comprarPaquetes(s._cantidadFigusPorPaquete);
			
			//Fase 2 Pegar figuritas
			for (int i = 0; i < s._users.length; i++) {
				if (paquetes.containsKey(i))
					s._users[i].pegarFiguritas(paquetes.get(i));
				}
			//Fase 3 Intercambiar -NO IMPLEMENTADO
		
			//Fase 4 Volver a pegar figuritas si hubo intercambio
			
		}
		
		s._calcularPaquetesTotales();
		s._calcularFigusRepetidasTotales();
		System.out.println("CANT USERS:" + s._users.length);
		System.out.println("CANTIDAD FIGUS ALBUM: " + s._cantidadFigusAlbum);
		System.out.println("CANTIDAD FIGUS PAQUETE: " + s._cantidadFigusPorPaquete);
		System.out.println("Paquetes totales: " + s._paquetesTotalesComprados);
		System.out.println("Figuritas repetidas totales: " + s._figusTotalesRepetidas);
		System.out.println("Figuritas repetidas del usuario 0: " + s._users[0].getFiguritasRepetidas().size());
	}

	private void _calcularFigusRepetidasTotales() {
		for (Usuario u : _users)
			_figusTotalesRepetidas += u.getFiguritasRepetidas().size();
		
	}

	private void _calcularPaquetesTotales() {
		for (Usuario u : _users)
			_paquetesTotalesComprados += u.getCantidadPaquetesComprados();
	}

	private HashMap<Integer, Integer[]> comprarPaquetes(int cantFigus) {
		//HashMap<Integer, Paquete> ret = new HashMap<>();
		HashMap<Integer, Integer[]> ret = new HashMap<>();
		int index = 0;
		for (Usuario u : _users) {
			if (!u.tieneAlbumCompleto()) {
				//ret.put(index, u.comprarPaquete(cantFigus));
				ret.put(index, u.comprarPaqueteB(cantFigus));
			}
			index++;
		}
		return ret;
	}

	private boolean albumesCompletos() {
		for (Usuario u : _users) {
			if (!u.tieneAlbumCompleto())
				return false;
		}
		return true;
	}
}
