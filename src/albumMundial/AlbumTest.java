package albumMundial;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class AlbumTest {

	@Test
	public void figuritasEsperadasTest() {
		GeneradorPrefijado generador = new GeneradorPrefijado();
		Album.setGenerador(generador);
		Album album =  new Album(100);
		//{0,1,2,3,4,5}	
		ArrayList<Integer> figusEsperadas = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			figusEsperadas.add(i);
		}
		assertEquals(figusEsperadas, album.getFiguritasRaras());
	}

}
