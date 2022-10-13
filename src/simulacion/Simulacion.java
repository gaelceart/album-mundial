package simulacion;

import java.util.ArrayList;

import albumMundial.Figurita;
import albumMundial.Paquete;

public class Simulacion {
	private Usuario[] _users; 
	private	int _paquetesTotalesComprados;
	private	int _figusTotalesRepetidas;

	public Simulacion(int cantUsuarios) {
		_users = new Usuario[cantUsuarios];
		for (int i = 0; i < _users.length; i++)
			_users[i] = new Usuario(5);
		
		_paquetesTotalesComprados = 0;
		_figusTotalesRepetidas = 0;
	}
	
	public static void main(String[] args) {
		Simulacion s = new Simulacion(1);
		while (!s.albumesCompletos()) {
			//Fase 1 Comprar paquetes
			ArrayList<Paquete> paquetes = s.comprarPaquetes(2);
			//System.out.println(paquetes);
			s._paquetesTotalesComprados++;
			
			//Fase 2 Comprobar repetidas
			for (int i = 0; i < s._users.length; i++) {
				s._users[i].descartarRepetidas(paquetes.get(i));
			}

			//Fase 3 Intercambiar -NO IMPLEMENTADO

			for (int i = 0; i < s._users.length; i++) {
				s._users[i].pegarFiguritas(paquetes.get(i));
			}
			
			/*Fase 4 Pegar figuritas
			for (Figurita f : paquete.getFiguritas()) {
				if (s._users[0].esFiguritaRepetida(f)) {
					_figusTotalesRepetidas++;
				}
				else {
					s._users[0].pegarFigurita(f);
				}
			}
*/
		}
		//System.out.println(album.getFiguritasEncontradas().size());
		System.out.println(s._paquetesTotalesComprados);
		System.out.println(s._figusTotalesRepetidas);
		System.out.println(s._users[0].getFiguritasRepetidas().size());
	}

	private void chequearRepetidas(Paquete p) {
		for (Figurita f : p.getFiguritas()) {
			if (_users[0].esFiguritaRepetida(f)) {
				_figusTotalesRepetidas++;
			}
		}
	}

	private ArrayList<Paquete> comprarPaquetes(int cantFigus) {
		ArrayList<Paquete> ret = new ArrayList<>();
		for (Usuario u : _users)
			ret.add(u.comprarPaquete(cantFigus));
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
