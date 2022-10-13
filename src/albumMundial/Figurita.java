package albumMundial;

public class Figurita {
	private int _numFigurita;
	private boolean _pocoComun;
	
	public Figurita(int numero, boolean pocoComun) {
		_numFigurita = numero;
		_pocoComun = pocoComun;
	}
	
	public int getNumero() {
		return _numFigurita;
	}
	
	public boolean isPocoComun() {
		return _pocoComun;
	}
}
