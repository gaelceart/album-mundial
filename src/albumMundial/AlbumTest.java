package albumMundial;

import static org.junit.Assert.*;
import org.junit.Test;

import generadores.Generador;

public class AlbumTest {
	private Album _album;
	@Test(expected = NullPointerException.class)
	public void albumNullTest() {
		_album.isCompleto();
	}
	@Test(expected = IllegalArgumentException.class)
	public void figuritasNegativasTest() {
		_album = new Album(-10, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void albumSinFiguritasTest() {
		_album = new Album(0, 0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void figuritasRarasNegativasTest() {
		_album = new Album(10, -1);
	}

	@Test(expected = IllegalArgumentException.class)
	public void masFiguritasRarasQueElTotalTest() {
		_album = new Album(10, 11);
	}

	@Test
	public void todasFiguritasRarasTest() {
		Generador generador = generadorPrefijado();
		Album.setGenerador(generador);
		_album = new Album(50, 50);
		assertEquals(_album.tamano(), _album.getFiguritasRaras().size());
	}

	@Test
	public void solaUnaFiguritaRaraTest() {
		Album.setGenerador(generadorPrefijado());
		_album = new Album(100, 1);
		assertEquals(1, _album.getCantidadFiguritasRaras());
	}

	@Test
	public void albumDeUnaFiguritaTest() {
		_album = new Album(1, 0);
		assertEquals(1, _album.tamano());
	}

	@Test
	public void pegarFiguritaTest() {
		_album = new Album(1, 0);
		_album.pegarFigurita(0);
		assertTrue(_album.figuritaPegada(0));
	}

	@Test
	public void figuritaNoPegadaTest() {
		_album = new Album(1, 0);
		assertFalse(_album.figuritaPegada(0));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void pegarFiguritaNegativaTest() {
		_album = new Album(1, 0);
		_album.pegarFigurita(-1);
	}

	@Test
	public void esFiguritaRaraTest() {
		Album.setGenerador(generadorPrefijado());
		_album = new Album(10, 1);
		assertTrue(_album.esFiguRara(0));
	}

	@Test
	public void noEsFiguritaRaraTest() {
		Album.setGenerador(generadorPrefijado());
		_album = new Album(10, 1);
		assertFalse(_album.esFiguRara(1));
	}

	@Test
	public void albumCompleto() {
		_album = new Album(1, 0);
		_album.pegarFigurita(0);
		assertTrue(_album.checkEsCompleto());
	}

	@Test
	public void albumIncompleto() {
		_album = new Album(5, 0);
		_album.pegarFigurita(0);
		assertFalse(_album.checkEsCompleto());
	}

	private Generador generadorPrefijado() {
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

}
