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

	@Override
	public String toString() {
		return "Figurita [_numFigurita=" + _numFigurita + ", _pocoComun=" + _pocoComun + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Figurita other = (Figurita) obj;
		return _numFigurita == other._numFigurita && _pocoComun == other._pocoComun;
	}

}
