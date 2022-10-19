package presenter;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextField;

import interfaz.Interfaz;
import simulacion.Model;
import simulacion.tipoEscenario;

@SuppressWarnings({"rawtypes"})
public class Presenter {
	//rename View ?
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
			}
			else if (_escenarios.getSelectedIndex() == 2) {
				_model.setTipoEscenario(tipoEscenario.donacion);
			}
			else if (_escenarios.getSelectedIndex() == 3){
				_model.setTipoEscenario(tipoEscenario.intercambio);
			}
			_gui.setBtnIniciar(true);
	}

	public void eventoIniciar() {
		_gui.setInteracciones(false);
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
