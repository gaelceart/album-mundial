package albumMundial;

import java.util.Random;

public class GeneradorRandom implements Generador {
	private Random _random = new Random();

	public GeneradorRandom(Random random) {
		_random = random;
	}

	@Override
	public int nextInt(int rango) {
		return _random.nextInt(rango);
	}

	@Override
	public boolean nextBoolean() {
		return _random.nextBoolean();
	}

}
