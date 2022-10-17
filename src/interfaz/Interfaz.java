package interfaz;
import simulacion.Simulacion;

import java.awt.EventQueue;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;


@SuppressWarnings("rawtypes")
public class Interfaz {

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
		setupFrame();
		setupContainers();
		updateFrame();
		
	}

	private void setupContainers() {
		setupPanelDeAlbum();
		setupPanelDeEstadisticas();
		setupPanelDeUsuario();
	}

	private void setupPanelDeAlbum() {
		_albumContainer = Recurso.setupAlbumContainer();
		_albumContainer.add(Recurso.setupAlbumImage());
		
		setupEventosDeAlbum();
		
		frame.getContentPane().add(_albumContainer);
	}

	private void setupPanelDeEstadisticas() {
		_statisticsContainer = Recurso.setupStatisticsContainer();
		_statisticsContainer.add(Recurso.setupStatisticsImage());
		
		setupEventosDeEstadisticas();
	
		frame.getContentPane().add(_statisticsContainer);
	}
	
	private void setupPanelDeUsuario() {
		_userContainer = Recurso.setupUserContainer();
		_iniciar = Recurso.setupBtnIniciar();
		_escenarios = Recurso.setupBtnEscenarios();
		_figusPorAlbum = Recurso.setupCantidadDeFigusAlbum();
		_figusPorPaquete = Recurso.setupFigusPorPaquete();
		_usuarios = Recurso.setupUsuarios();
		_textoFigusPorAlbum = Recurso.setupTextoFigusPorAlbum();
		_textoFigusPorPaquete = Recurso.setupTextoFigusPorPaquete();
		_textoUsuarios = Recurso.setupTextoUsuarios();
		
		desactivarBtnIniciar();
		setupEventosDeUsuario();
		
		_userContainer.add(_iniciar);
		_userContainer.add(_escenarios);
		_userContainer.add(_figusPorAlbum);
		_userContainer.add(_figusPorPaquete);
		_userContainer.add(_usuarios);
		_userContainer.add(_textoFigusPorAlbum);
		_userContainer.add(_textoFigusPorPaquete);
		_userContainer.add(_textoUsuarios);
		
		frame.getContentPane().add(_userContainer);
	}

	private void setupEventosDeAlbum() {
		
	}
	
	private void setupEventosDeEstadisticas() {
		
	}
	
	private void setupEventosDeUsuario() {
		_iniciar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		_escenarios.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (_escenarios.getSelectedIndex() == 0) {
					desactivarBtnIniciar();
				}
				if (_escenarios.getSelectedIndex() == 1) {
					//setear enum
					activarBtnIniciar();
				}
				if (_escenarios.getSelectedIndex() == 2) {
					//setear enum
					activarBtnIniciar();
				}
				if (_escenarios.getSelectedIndex() == 3){
					//setear enum
					activarBtnIniciar();
				}
				
			}

		});
		
	}
	
	private void desactivarBtnIniciar() {
		_iniciar.setEnabled(false);
	}
	
	private void activarBtnIniciar() {
		_iniciar.setEnabled(true);	
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
}
