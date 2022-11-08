package presenter;

import java.awt.event.KeyEvent;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import albumMundial.Paquete;
import generadores.GeneradorRandom;
import interfaz.Interfaz;
import interfaz.Recurso;
import simulacion.Model;
import simulacion.tipoEscenario;

@SuppressWarnings({ "rawtypes" })
public class Presenter {

	private Interfaz _gui;
	private Model _model;
	private DefaultCategoryDataset baseDeDatos;

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

	public void eventoIniciar(JTextField figuAlbum, JTextField figuPaquete, JTextField figusRaras,
			JTextField precioPaquete, JTextField cantUser, JTextField cantSimulaciones) {
		Paquete.setGenerador(new GeneradorRandom());
		_model.setCantFigusAlbum(Integer.parseInt(figuAlbum.getText()));
		_model.setCantFigusPaquete(Integer.parseInt(figuPaquete.getText()));
		_model.setCantFigusRaras(Integer.parseInt(figusRaras.getText()));
		_model.setPrecioPaquete(Integer.parseInt(precioPaquete.getText()));
		_model.setCantUsuarios(Integer.parseInt(cantUser.getText()));
		_model.setCantSimulaciones(Integer.parseInt(cantSimulaciones.getText()));
		_model.iniciarSimulacion();
	}

	public DefaultCategoryDataset cargarDatos() {
		baseDeDatos = new DefaultCategoryDataset();
		for (int simu = 0; simu < _model.getCantSimulaciones(); simu++) {
			if (simu < 5) {
				baseDeDatos.setValue(_model.getDatosDelGrafico()[simu], "Simu " + simu, "Simu " + simu);
			}
		}
		baseDeDatos.addValue(Double.parseDouble(_model.getCostoPromedio()), "PROM", "Promedio");
		return baseDeDatos;
	}

	public void actualizarGrafico(JFreeChart grafico, ChartPanel graficoBarras, JPanel graficoContainer) {
		graficoContainer.removeAll();
		grafico = ChartFactory.createBarChart3D("Costo promedio de simulaciones", "SIMULACIONES", "COSTO",
				cargarDatos(), PlotOrientation.VERTICAL, true, false, false);
		graficoBarras = Recurso.setupChartPanel(grafico);
		graficoBarras.setVisible(true);
		graficoContainer.add(graficoBarras);
	}

	public void mostrarResultados(JTextField escenarioActual, JTextField costoPromedio, JTextField paqComprados,
			JTextField figusRepetidas, JTextArea usuario0) {
		escenarioActual.setText(_model.getEscenarioActual().toUpperCase());
		costoPromedio.setText(_model.getCostoPromedio());
		paqComprados.setText(_model.getPaquetesComprados());
		figusRepetidas.setText(_model.getFigusRepetidas());
		usuario0.setText(_model.getUsuario0());
		_model.setearVariables();
	}

	public void eventoTeclado(KeyEvent ke, JTextField textField) {
		if (ke.getKeyChar() == 8 || ke.getKeyChar() >= '0' && ke.getKeyChar() <= '9') {
			textField.setEditable(true);
		} else {
			textField.setEditable(false);
		}
	}

	public boolean argumentosValidos(JTextField _figusPorPaquete, JTextField _figusPorAlbum, JTextField _figusRaras) {
		if (Integer.parseInt(_figusPorPaquete.getText()) > Integer.parseInt(_figusPorAlbum.getText())) {
			_gui.mensajeEmergente("Los paquetes no pueden tener mas figuritas que album");
			return false;
		}
		if (Integer.parseInt(_figusRaras.getText()) > Integer.parseInt(_figusPorAlbum.getText())) {
			_gui.mensajeEmergente(
					"La cantidad de figuritas raras no puede ser mayor a la cantidad de figuritas del album");
			return false;
		}
		return true;
	}

	public boolean camposContienenTexto(JTextField _usuarios, JTextField _simulaciones, JTextField _figusPorAlbum,
			JTextField _figusPorPaquete, JTextField _figusRaras, JTextField _precioPaquete) {
		if (campoVacio(_usuarios) || campoVacio(_simulaciones) || campoVacio(_figusPorAlbum)
				|| campoVacio(_figusPorPaquete) || campoVacio(_figusRaras) || campoVacio(_precioPaquete)) {
			_gui.mensajeEmergente("Rellene los campos de texto antes de iniciar la simulacion");
			return false;
		} else if (_usuarios.getText().equals("0") || _simulaciones.getText().equals("0")) {
			_gui.mensajeEmergente("La cantidad de usuarios y simulaciones no puede ser 0");
			return false;
		}
		return true;
	}

	private boolean campoVacio(JTextField campoDeTexto) {
		return campoDeTexto.getText().isEmpty();
	}

}