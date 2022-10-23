package simulacion;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

import albumMundial.Album;
import albumMundial.Paquete;
import generadores.GeneradorRandom;

public class UsuarioTest {

	@Test
	public void compraUnPaqueteTest() {
		Album.setGenerador(new GeneradorRandom());
		Paquete.setGenerador(new GeneradorRandom());
		Usuario usuario = new Usuario(5, 1);
		usuario.comprarPaquete(1);
		assertEquals(1, usuario.getCantidadPaquetesComprados());
	}

	@Test
	public void comprarCienPaquetesTest() {
		Album.setGenerador(new GeneradorRandom());
		Paquete.setGenerador(new GeneradorRandom());
		Usuario usuario = new Usuario(5, 1);
		for (int i = 0; i < 100; i++) {
			usuario.comprarPaquete(1);
		}
		assertEquals(100, usuario.getCantidadPaquetesComprados());
	}

	@Test
	public void sinFiguritasRepetidasTest() {
		Usuario usuario = new Usuario(10, 5);
		Integer [] figuritas = {0,1,2,3,4,5};
		usuario.pegarFiguritas(figuritas);
		assertEquals(0, usuario.getCantidadFigusRepetidasTotal());
	}
	
	@Test
	public void cantidadFiguritasRepetidasTest() {
		Usuario usuario = new Usuario(10, 5);
		Integer [] figuritas = {0,0,1,1,2,2,3,3,4,4,5,5};
		usuario.pegarFiguritas(figuritas);
		assertEquals(6, usuario.getCantidadFigusRepetidasTotal());
		
	}
	@Test
	public void figuritasRepetidasTest(){
		Usuario usuario = new Usuario(10, 5);
		Integer [] figuritas = {0,0,1,1,2,2,3,3,4,4,5,5};
		Integer [] figuritasRepetidas = {0,1,2,3,4,5};
		usuario.pegarFiguritas(figuritas);
		assertArrayEquals(figuritasRepetidas, usuario.getFiguritasRepetidas().toArray(new Integer[usuario.getCantidadFigusRepetidasTotal()]));
	}
 
}
