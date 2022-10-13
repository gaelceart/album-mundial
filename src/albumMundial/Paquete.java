package albumMundial;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Paquete {
	private int _cantidadFiguritas;
	private List<Figurita> _figuritas;
	private int _currentSize;

	public Paquete(int figuritasPorPaquete) {
		if (figuritasPorPaquete <= 0) {
			throw new IllegalArgumentException(
					"Un Paquete no puede contener tener 0 o menos figuritas:" + figuritasPorPaquete);
		}
		_cantidadFiguritas = figuritasPorPaquete;
		_figuritas = new ArrayList<>();
		crearPaquete();
		_currentSize = figuritasPorPaquete;
	}

	// crearPaquete(int figuritasPosibles)? o genera acomplamiento
	public void crearPaquete() {
		while (tamanoPaquete() < _cantidadFiguritas) {
			Random random = new Random();
			int figuritaSeleccionada = random.nextInt(Album._cantidadFiguritas);
			Figurita figurita = new Figurita(figuritaSeleccionada, false);
			_figuritas.add(figurita);
		}
	}

	public List<Figurita> getFiguritas() {
		return _figuritas;
	}

	public int tamanoPaquete() {
		return _figuritas.size();
	}

	@Override
	public String toString() {
		return "Paquete [_cantidadFiguritas=" + _cantidadFiguritas + ", _figuritas=" + _figuritas + "]\n";
	}
	
    public Iterator<Figurita> iterator() {
		Iterator<Figurita> it = new Iterator<Figurita>() {
			boolean canRemove = false;
		    int previousLoc = -1;

			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < _currentSize;
			}

			@Override
			public Figurita next() {
				previousLoc++;
				canRemove = true;
				return _figuritas.get(currentIndex++);
			}

			@Override
			public void remove() {
				if (!canRemove)
					throw new IllegalStateException();
				_figuritas.remove(previousLoc);
				canRemove = false;
				_currentSize--;
			}
			
		};
		return it;
	}
    
    public static void main(String[] args) {
    	Album a = new Album(638);
		Paquete p = new Paquete(3);
		System.out.println(p.toString());
		
		Iterator<Figurita> it = p.iterator();
		while (it.hasNext()) {
			Figurita f = it.next();
			if (f.getNumero() % 2 == 0) {
				it.remove();
			}
		}
		System.out.println(p.toString());
		
	}
	
}
