package albumMundial;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PaqueteTest {
	@Test
	public void figusEnOrdenTest() {
		Generador generador = new Generador() {
			int count = 0;

			@Override
			public int nextInt(int rango) {
				return count < rango ? count++ : rango;
			}

			@Override
			public boolean nextBoolean() {
				return true;
			}
		};
		Album.setGenerador(generador);
		Paquete.setGenerador(generador);
		Album album = new Album(100, 99);
		Integer [] paquete = Paquete.comprarPaquete(5, album);
		for (Integer integer : paquete) {
			System.out.println(integer);
		}
		Integer [] paquetesEsperados = {0,1,2,3,4};
		assertEquals(paquetesEsperados, paquete);
	}

}
