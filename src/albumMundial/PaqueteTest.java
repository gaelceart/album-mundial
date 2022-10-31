package albumMundial;

import static org.junit.Assert.*;

import org.junit.Test;

import generadores.GeneradorEnOrdenUnaVezTrue;
import generadores.GeneradorPaqueteEnOrden4VecesTrue;
import generadores.GeneradorPaquetesEnOrdenTrue;
import generadores.GeneradoresPaquetesEnOrdenFalse;

public class PaqueteTest {
	@Test(expected = IllegalArgumentException.class)
	public void paqueteContieneMasQueAlbumTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Paquete.comprarPaquete(101, new Album(100, 5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void paqueteConCeroFiguritasTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Paquete.comprarPaquete(0, new Album(100, 5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void paqueteConFiguritasNegativasTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Paquete.comprarPaquete(-1, new Album(100, 5));
	}

	@Test
	public void cantidadDeFigusPorPaqueteTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Integer[] paquete = Paquete.comprarPaquete(5, new Album(100, 5));
		assertEquals(5, paquete.length);
	}

	@Test
	public void paqueteTodasRarasTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Integer[] paquete = Paquete.comprarPaquete(5, new Album(100, 5));
		Integer[] paqueteEsperado = { 0, 1, 2, 3, 4 };
		assertArrayEquals(paqueteEsperado, paquete);
	}

	@Test
	public void paqueteSinRarasTest() {
		Paquete.setGenerador(new GeneradoresPaquetesEnOrdenFalse());
		Integer[] paquete = Paquete.comprarPaquete(5, new Album(100, 5));
		Integer[] paqueteRaras = { 0, 1, 2, 3, 4 };

		boolean ret = false;
		for (Integer figurita : paquete) {
			for (Integer figuritaRara : paqueteRaras) {
				ret = ret || figurita == figuritaRara;
			}
		}
		assertFalse(ret);
	}

	@Test
	public void paqueteConUnaRaraTest() {
		Paquete.setGenerador(new GeneradorEnOrdenUnaVezTrue());
		Integer[] paquete = Paquete.comprarPaquete(5, new Album(100, 5));
		Integer[] paqueteEsperado = { 0, 5, 6, 7, 8 };
		assertArrayEquals(paqueteEsperado, paquete);

	}

	@Test
	public void paqueteConUnaComunTest() {
		Paquete.setGenerador(new GeneradorPaqueteEnOrden4VecesTrue());
		Integer[] paquete = Paquete.comprarPaquete(5, new Album(100, 5));
		Integer[] paqueteEsperado = { 0, 1, 2, 3, 5 };
		assertArrayEquals(paqueteEsperado, paquete);

	}

	@Test
	public void paqueteSinRepetidasTest() {
		Paquete.setGenerador(new GeneradorPaquetesEnOrdenTrue());
		Integer[] paquete = Paquete.comprarPaquete(5, new Album(100, 5));
		boolean ret = false;
		for (int i = 0; i < paquete.length; i++) {
			for (int j = 0; j < paquete.length; j++) {
				ret = ret || (i != j && paquete[i] == paquete[j]);
			}
		}
		assertFalse(ret);
	}

}
