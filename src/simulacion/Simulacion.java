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

	public Simulacion(int cantUsuarios) {
		_users = new Usuario[cantUsuarios];
		for (int i = 0; i < _users.length; i++)
			_users[i] = new Usuario(638);
		
		_paquetesTotalesComprados = 0;
		_figusTotalesRepetidas = 0;
	}
	
	public static void main(String[] args) {
		Simulacion s = new Simulacion(5);
		while (!s.albumesCompletos()) {
			//Fase 1 Comprar paquetes
			HashMap<Integer, Paquete> paquetes = s.comprarPaquetes(5);
			
			//Fase 2 Pegar figuritas
			for (int i = 0; i < s._users.length; i++) {
				if (paquetes.containsKey(i))
					s._users[i].pegarFiguritas(paquetes.get(i));
				}
		}
			//Fase 3 Intercambiar -NO IMPLEMENTADO
		s._calcularPaquetesTotales();
		s._calcularFigusRepetidasTotales();
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

	private HashMap<Integer, Paquete> comprarPaquetes(int cantFigus) {
		HashMap<Integer, Paquete> ret = new HashMap<>();
		int index = 0;
		for (Usuario u : _users) {
			if (!u.tieneAlbumCompleto()) {
				ret.put(index, u.comprarPaquete(cantFigus));
				_paquetesTotalesComprados++;
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
