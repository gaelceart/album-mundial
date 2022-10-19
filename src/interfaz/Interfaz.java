package interfaz;

import java.awt.EventQueue;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import presenter.Presenter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


@SuppressWarnings("rawtypes")
public class Interfaz {
	private Presenter _presenter;
	private JFrame frame;
	private JPanel _albumContainer;
	private JPanel _statisticsContainer;
	private JPanel _userContainer;
	private JLabel _textoFigusPorAlbum;
	private JLabel _textoFigusPorPaquete;
	private JLabel _textoUsuarios;
	private JTextField _figusPorAlbum;
	private JTextField _figusPorPaquete;
	private JTextField _usuarios;
	private JButton _iniciar;
	private JComboBox _escenarios;
	private JLabel _costoPromedio;
	private JTextField _costoTotal;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interfaz window = new Interfaz();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interfaz() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		_presenter = new Presenter(this);
		
		setupFrame();
		
		_userContainer = Recurso.setupUserContainer();
		_albumContainer = Recurso.setupAlbumContainer();
		_statisticsContainer = Recurso.setupStatisticsContainer();
		
		_statisticsContainer.add(Recurso.setupStatisticsImage());
		_albumContainer.add(Recurso.setupAlbumImage());
		
		_iniciar = Recurso.setupBtnIniciar();
		_escenarios = Recurso.setupBtnEscenarios();
		_figusPorAlbum = Recurso.setupCantidadDeFigusAlbum();
		_figusPorPaquete = Recurso.setupFigusPorPaquete();
		_usuarios = Recurso.setupUsuarios();
		_textoFigusPorAlbum = Recurso.setupTextoFigusPorAlbum();
		_textoFigusPorPaquete = Recurso.setupTextoFigusPorPaquete();
		_textoUsuarios = Recurso.setupTextoUsuarios();
		_costoPromedio = Recurso.setupCostoPromedio();
		_costoTotal = Recurso.setupCostoTotal();
		
		_userContainer.add(_iniciar);
		_userContainer.add(_escenarios);
		_userContainer.add(_figusPorAlbum);
		_userContainer.add(_figusPorPaquete);
		_userContainer.add(_usuarios);
		_userContainer.add(_textoFigusPorAlbum);
		_userContainer.add(_textoFigusPorPaquete);
		_userContainer.add(_textoUsuarios);
		_statisticsContainer.add(_costoPromedio);
		_statisticsContainer.add(_costoTotal);
		
		
		frame.getContentPane().add(_costoPromedio);
		frame.getContentPane().add(_statisticsContainer);
		frame.getContentPane().add(_albumContainer);
		frame.getContentPane().add(_userContainer);
		
		
		updateFrame();
		
	}

	private void setupEventosDeAlbum() {
		
	}
	
	private void setupEventosDeEstadisticas() {
		
	}
	
	private void setupEventosDeUsuario() {
		_iniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_presenter.eventoIniciar();
			}
		});
		
		_escenarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				_presenter.eventoElegirEscenario(e, _escenarios);
			}

		});
		
	}
	
	public void setBtnIniciar(boolean value) {
		_iniciar.setEnabled(value);	
	}

	private void setupFrame() {
		frame = Recurso.setupFrame();

		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void updateFrame() {
		SwingUtilities.updateComponentTreeUI(frame);
		frame.setVisible(true);
		frame.toFront();
		frame.requestFocus();
	}

	public void setInteracciones(boolean value) {	
		//desactivar o activar todos los botones y cosas
	}
}
