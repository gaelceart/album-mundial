package albumMundial;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

import generador.Generador;

public class PaqueteTest {
	@Test(expected = IllegalArgumentException.class)
	public void paqueteContieneMasQueAlbumTest() {
		Album.setGenerador(generadorPrefijadoTrue());
		Paquete.setGenerador(generadorPrefijadoTrue());
		Paquete.comprarPaquete(101, new Album(100, 5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void paqueteConCeroFiguritasTest() {
		Album.setGenerador(generadorPrefijadoTrue());
		Paquete.setGenerador(generadorPrefijadoTrue());
		Paquete.comprarPaquete(0, new Album(100, 5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void paqueteConFiguritasNegativasTest() {
		Album.setGenerador(generadorPrefijadoTrue());
		Paquete.setGenerador(generadorPrefijadoTrue());
		Paquete.comprarPaquete(-1, new Album(100, 5));
	}

	@Test
	public void paqueteTodasRarasTest() {
		Album.setGenerador(generadorPrefijadoTrue());
		Paquete.setGenerador(generadorPrefijadoTrue());
		Integer[] paquete = Paquete.comprarPaquete(5, new Album(100, 5));
		Integer[] paqueteEsperado = { 0, 1, 2, 3, 4 };
		assertArrayEquals(paqueteEsperado, paquete);
	}

	@Test
	public void paqueteSinRarasTest() {
		Album.setGenerador(generadorPrefijadoFalse());
		Paquete.setGenerador(generadorPrefijadoFalse());
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
		Album.setGenerador(generadorPrefijadoUnaVezTrue());
		Paquete.setGenerador(generadorPrefijadoUnaVezTrue());
		Integer[] paquete = Paquete.comprarPaquete(5, new Album(100, 5));
		Integer[] paqueteEsperado = { 0, 5, 6, 7, 8 };
		assertArrayEquals(paqueteEsperado, paquete);

	}

	@Test
	public void paqueteConUnaComunTest() {
		Album.setGenerador(generadorPrefijado4VecesTrue());
		Paquete.setGenerador(generadorPrefijado4VecesTrue());
		Integer[] paquete = Paquete.comprarPaquete(5, new Album(100, 5));
		Integer[] paqueteEsperado = { 0, 1, 2, 3, 5 };
		assertArrayEquals(paqueteEsperado, paquete);

	}

	@Test
	public void paqueteSinRepetidasTest() {
		Album.setGenerador(generadorPrefijadoRepetitivo());
		Paquete.setGenerador(generadorPrefijadoRepetitivo());
		Integer[] paquete = Paquete.comprarPaquete(5, new Album(100, 5));
		boolean ret = false;
		for (int i = 0; i < paquete.length; i++) {
			for (int j = 0; j < paquete.length; j++) {
				ret = ret || (i != j && paquete[i] == paquete[j]);
			}
		}
		assertFalse(ret);
	}

	private Generador generadorPrefijado4VecesTrue() {
		Generador generador = new Generador() {
			int count = 0;

			@Override
			public int nextInt(int rango) {
				return count < rango ? count++ : rango;
			}

			int contadorTrue = 0;

			@Override
			public boolean nextBoolean() {
				if (contadorTrue++ < 4) {
					return true;
				}
				return false;
			}
		};
		return generador;
	}

	private Generador generadorPrefijadoUnaVezTrue() {
		Generador generador = new Generador() {
			int count = 0;

			@Override
			public int nextInt(int rango) {
				return count < rango ? count++ : rango;
			}

			boolean aux = true;

			@Override
			public boolean nextBoolean() {
				if (aux) {
					aux = false;
					return true;
				}
				return false;
			}
		};
		return generador;
	}

	private Generador generadorPrefijadoRepetitivo() {
		Generador generador = new Generador() {
			Random random = new Random();
			int count = 0;
			int repeticiones = 1;

			@Override
			public int nextInt(int rango) {
				if (count == rango) {
					return rango;
				}
				if (repeticiones >= 1) {
					repeticiones--;
					return count;
				}
				repeticiones++;
				return count++;
			}

			@Override
			public boolean nextBoolean() {
				return random.nextBoolean();
			}
		};
		return generador;
	}

	private Generador generadorPrefijadoFalse() {
		Generador generador = new Generador() {
			int count = 0;

			@Override
			public int nextInt(int rango) {
				return count < rango ? count++ : rango;
			}

			@Override
			public boolean nextBoolean() {
				return false;
			}
		};
		return generador;
	}

	private Generador generadorPrefijadoTrue() {
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
		return generador;
	}
}
