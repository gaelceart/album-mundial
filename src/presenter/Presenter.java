package presenter;

import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JTextField;

import albumMundial.Paquete;
import generadores.GeneradorRandom;
import interfaz.Interfaz;
import simulacion.Model;
import simulacion.tipoEscenario;

@SuppressWarnings({ "rawtypes" })
public class Presenter {
	// rename View ?
	private Interfaz _gui;
	private Model _model;

	public Presenter(Interfaz gui) {
		_gui = gui;
		_model = new Model();
	}

	public void eventoElegirEscenario(JComboBox _escenarios) {
		if (_escenarios.getSelectedIndex() == 0) {
			_gui.setBtnIniciar(false);
			return;
		}
		if (_escenarios.getSelectedIndex() == 1) {
			_model.setTipoEscenario(tipoEscenario.individual);
		} else if (_escenarios.getSelectedIndex() == 2) {
			_model.setTipoEscenario(tipoEscenario.donacion);
		} else if (_escenarios.getSelectedIndex() == 3) {
			_model.setTipoEscenario(tipoEscenario.intercambio);
		}
		_gui.setBtnIniciar(true);
	}

	public void eventoIniciar(JTextField figuAlbum, JTextField figuPaquete, JTextField figusRaras, JTextField precioPaquete, JTextField cantUser, JTextField cantSimulaciones) {
		Paquete.setGenerador(new GeneradorRandom());
		_gui.setInteracciones(false);
		_model.setCantFigusAlbum(Integer.parseInt(figuAlbum.getText()));
		_model.setCantFigusPaquete(Integer.parseInt(figuPaquete.getText()));
		_model.setCantFigusRaras(Integer.parseInt(figusRaras.getText()));
		_model.setPrecioPaquete(Integer.parseInt(figusRaras.getText()));
		_model.setCantUsuarios(Integer.parseInt(cantUser.getText()));
		_model.setCantSimulaciones(Integer.parseInt(cantSimulaciones.getText()));
		_model.iniciarSimulacion();
	}

	public void eventoTeclado(KeyEvent ke, JTextField textField) {
		if (ke.getKeyChar() == 8 || ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
			textField.setEditable(true);
		} else {
			textField.setEditable(false);
		}

	}

}
