package observador;

import presenter.Presenter;

public class ObservadorModel implements Observador {
	
	private Presenter _presenter;
	
	public ObservadorModel(Presenter p) {
		_presenter = p;
	}
		
	@Override
	public void notificar() {
		_presenter.update();
	}

}
