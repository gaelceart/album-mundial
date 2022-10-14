package simulacion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import albumMundial.Album;

public class Simulacion implements Runnable {
	private Usuario[] _users; 
	private int _cantidadFigusPorPaquete;
	private	int _paquetesTotalesComprados;
	private	int _figusTotalesRepetidas;
	private int _cantidadFigusAlbum;
	private enum tipoEscenario{individual, donacion, intercambio};
	private tipoEscenario _escenario;

	public Simulacion(int cantUsuarios, int cantFigusAlbum, int cantFigusPorPaquete, tipoEscenario e) {
		_cantidadFigusAlbum = cantFigusAlbum;
		_cantidadFigusPorPaquete = cantFigusPorPaquete;
		_users = inicializarUsers(cantUsuarios);
		_paquetesTotalesComprados = 0;
		_figusTotalesRepetidas = 0;
		_escenario = e;
	}

	
	private Usuario[] inicializarUsers(int cantUsuarios) {
		Usuario[] ret = new Usuario[cantUsuarios];
		for (int i = 0; i < cantUsuarios; i++)
			ret[i] = new Usuario(_cantidadFigusAlbum);
		return ret;
	}

	public static void main(String[] args) throws InterruptedException {
		Simulacion s = new Simulacion(220, 638, 5, tipoEscenario.donacion);
		Thread t1 = new Thread(s);
		Simulacion s5 = new Simulacion(219, 638, 5, tipoEscenario.individual);
		Thread t5 = new Thread(s5);
		t1.start();
		t5.start();
		System.out.println("CARGANDO...\n");
		t1.join();
		t5.join();
		/*
		Simulacion s2 = new Simulacion(15000, 638, 5, tipoEscenario.individual);
		Thread t2 = new Thread(s2);
		Simulacion s3 = new Simulacion(15000, 638, 5, tipoEscenario.individual);
		Thread t3 = new Thread(s3);
		Simulacion s4 = new Simulacion(15000, 638, 5, tipoEscenario.individual);
		Thread t4 = new Thread(s4);
		
		t2.start();
		t3.start();
		t4.start();
		
		t2.join();
		t3.join();
		t4.join();
		*/
	}
	
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		while (!albumesCompletos()) {
			//Fase 1 Comprar paquetes
			HashMap<Integer, Integer[]> paquetes = comprarPaquetes(_cantidadFigusPorPaquete);

			//Fase 2 Pegar figuritas
			for (int i = 0; i < _users.length; i++)
				if (paquetes.containsKey(i))
					_users[i].pegarFiguritas(paquetes.get(i));
			
			//Fase 3 Donar y pegar donadas
			if (_escenario == tipoEscenario.donacion) {
				//donar
				for (int donante = 0; donante < _users.length; donante++) {
					ArrayList<Integer> repetidasDonante = _users[donante].getFiguritasRepetidas();
					HashMap<Integer, ArrayList<Integer>> repesADonar = new HashMap<>();	

					//recorro los destinos
					for (int destino = donante + 1; destino < _users.length; destino++) {
						repesADonar.put(destino, new ArrayList<Integer>());
						//recorro las cartas de donante
						for (Integer i : repetidasDonante) {
							// si no es repetida lo agrego
							if (!_users[destino].esFiguritaRepetida(i))
								repesADonar.get(destino).add(i);
						}
					}
					
					for (int d = donante + 1; d < _users.length; d++) {
						if (repesADonar.containsKey(d)) {
							for (Integer f : repesADonar.get(d)) {
								_users[d].pegarFigurita(f);
								_users[donante].getFiguritasRepetidas().remove(f);
							}
						}
					}
				}
			}
			
			//Fase 3 Intercambiar -NO IMPLEMENTADO
			if (_escenario == tipoEscenario.intercambio) {
				//intercambio
			}
		
			
		}
		
		_calcularPaquetesTotales();
		_calcularFigusRepetidasTotales();
		System.out.println("CANT USERS:" + _users.length);
		System.out.println("CANTIDAD FIGUS ALBUM: " + _cantidadFigusAlbum);
		System.out.println("CANTIDAD FIGUS PAQUETE: " + _cantidadFigusPorPaquete);
		System.out.println("Paquetes totales: " + _paquetesTotalesComprados);
		System.out.println("Figuritas repetidas totales: " + _figusTotalesRepetidas);
		System.out.println("Figuritas repetidas del usuario 0: " + _users[0].getFiguritasRepetidas().size());
		System.out.println();
		long endTime = System.currentTimeMillis();
		System.out.println((endTime - startTime));

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
		HashMap<Integer, Integer[]> ret = new HashMap<>();
		int index = 0;
		for (Usuario u : _users) {
			if (!u.tieneAlbumCompleto()) {
				ret.put(index, u.comprarPaquete(cantFigus));
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
	
	public static Integer[] comprarPaquete(int cantFigus) {
		List<Integer> paquete = new ArrayList<>();
		Random random = new Random();
		while (paquete.size() < cantFigus) {
			int figuritaSeleccionada = random.nextInt(Album._cantidadFiguritas);
			if (!paquete.contains(figuritaSeleccionada))
				paquete.add(figuritaSeleccionada);
		}
		return paquete.toArray(new Integer[cantFigus]);
	}

}
