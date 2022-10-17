package albumMundial;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Paquete {

	public static Integer[] comprarPaquete(int cantFigus, int figuritasPosibles) {
		List<Integer> paquete = new ArrayList<>();
		Random random = new Random();
		while (paquete.size() < cantFigus) {
			int figuritaSeleccionada = random.nextInt(figuritasPosibles);
			if (!paquete.contains(figuritaSeleccionada)) 
				paquete.add(figuritaSeleccionada);
			
		}
		return paquete.toArray(new Integer[cantFigus]);
	}
}
