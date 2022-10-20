package interfaz;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import presenter.Presenter;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
	 * 
	 * @wbp.parser.entryPoint
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
		setupUserContainer();
		setupAlbumContainer();
		setupStatsContainer();
		setupEventosDeUsuario();
		updateFrame();
	}

	private void setupStatsContainer() {
		_statisticsContainer = Recurso.setupStatisticsContainer();
		_statisticsContainer.add(Recurso.setupStatisticsImage());
		
		frame.getContentPane().add(_statisticsContainer);
	}

	private void setupAlbumContainer() {
		_albumContainer = Recurso.setupAlbumContainer();
		_albumContainer.add(Recurso.setupAlbumImage());
		_costoPromedio = Recurso.setupCostoPromedio();
		_costoTotal = Recurso.setupCostoTotal();
		_albumContainer.add(_costoPromedio);
		_albumContainer.add(_costoTotal);
		frame.getContentPane().add(_albumContainer);
	}

	private void setupUserContainer() {
		_userContainer = Recurso.setupUserContainer();
		_iniciar = Recurso.setupBtnIniciar();
		_escenarios = Recurso.setupBtnEscenarios();
		_figusPorAlbum = Recurso.setupCantidadDeFigusAlbum();
		_figusPorPaquete = Recurso.setupFigusPorPaquete();
		_usuarios = Recurso.setupUsuarios();
		_textoFigusPorAlbum = Recurso.setupTextoFigusPorAlbum();
		_textoFigusPorPaquete = Recurso.setupTextoFigusPorPaquete();
		_textoUsuarios = Recurso.setupTextoUsuarios();
		
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
		_figusPorAlbum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				_presenter.eventoTeclado(ke, _figusPorAlbum);
			}
		});

		_figusPorPaquete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				_presenter.eventoTeclado(ke, _figusPorPaquete);
			}
		});

		_usuarios.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				_presenter.eventoTeclado(ke, _usuarios);
			}
		});

		_escenarios.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				_presenter.eventoElegirEscenario(_escenarios);
			}

		});

		_iniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				_presenter.eventoIniciar(_figusPorAlbum, _figusPorPaquete, _usuarios);
			}
		});

	}

	public void setBtnIniciar(boolean value) {
		_iniciar.setEnabled(value);
		updateFrame();
	}

	/**
	 * @wbp.parser.entryPoint
	 */
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
		// desactivar o activar todos los botones y cosas
	}
}
