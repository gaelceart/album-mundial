package interfaz;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

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
	public JPanel _graficoContainer;
	private JPanel _statisticsContainer;
	private JPanel _userContainer;

	private JTextField _figusPorAlbum;
	private JTextField _figusPorPaquete;
	private JTextField _figusRaras;
	private JTextField _precioPaquete;
	private JTextField _usuarios;
	private JComboBox _escenarios;
	private JTextField _simulaciones;

	private JButton _iniciar;
	
	private JProgressBar _barraDeProgreso;
	public ChartPanel _graficoBarras;
	public JFreeChart _grafico;
	public DefaultCategoryDataset _datos;

	public JTextField _costoPromedio;
	public JTextField _tipoDeEscenario;
	public JTextField _paquetesComprados;
	public JTextField _figuritasRepetidas;
	public JTextArea _usuario0;

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
		_tipoDeEscenario = Recurso.setupEscenario();
		_costoPromedio = Recurso.setupCostoPromedio();
		_figuritasRepetidas = Recurso.setupFiguritasRepetidas();
		_paquetesComprados = Recurso.setupPaquetesComprados();
		_usuario0 = Recurso.setupPanelUsuario0();

		_statisticsContainer.add(_costoPromedio);
		_statisticsContainer.add(_tipoDeEscenario);
		_statisticsContainer.add(_figuritasRepetidas);
		_statisticsContainer.add(_paquetesComprados);
		_statisticsContainer.add(_usuario0);
		_statisticsContainer.add(Recurso.setupTextoEscenario());
		_statisticsContainer.add(Recurso.setupTextoCostoPromedio());
		_statisticsContainer.add(Recurso.setupTextoFiguritasRepetidas());
		_statisticsContainer.add(Recurso.setupTextoPaquetesComprados());
		_statisticsContainer.add(Recurso.setupTextoUsuario0());

		_statisticsContainer.add(Recurso.setupStatisticsImage());

		frame.getContentPane().add(_statisticsContainer);
	}

	private void setupAlbumContainer() {
		_albumContainer = Recurso.setupAlbumContainer();
		_barraDeProgreso = Recurso.setupBarraDeProgreso();
		_graficoContainer = Recurso.setupGraficoContainer();
		
		_grafico = Recurso.setupGrafico(_datos);
		_graficoBarras = Recurso.setupChartPanel(_grafico);
		_graficoContainer.add(_graficoBarras);
		
		_albumContainer.add(_graficoContainer);
		_albumContainer.add(Recurso.setupUserLogoUngs());
		_albumContainer.add(_barraDeProgreso);
		_albumContainer.add(Recurso.setupAlbumImage());
		frame.getContentPane().add(_albumContainer);
	}

	private void setupUserContainer() {
		_userContainer = Recurso.setupUserContainer();
		_iniciar = Recurso.setupBtnIniciar();
		_escenarios = Recurso.setupBtnEscenarios();
		_figusPorAlbum = Recurso.setupCantidadDeFigusAlbum();
		_figusPorPaquete = Recurso.setupFigusPorPaquete();
		_figusRaras = Recurso.setupFigusRaras();
		_usuarios = Recurso.setupUsuarios();
		_simulaciones = Recurso.setupSimulaciones();
		_precioPaquete = Recurso.setupPrecioPaquete();

		_userContainer.add(_iniciar);
		_userContainer.add(_escenarios);
		_userContainer.add(_figusPorAlbum);
		_userContainer.add(_figusPorPaquete);
		_userContainer.add(_usuarios);
		_userContainer.add(_figusRaras);
		_userContainer.add(_simulaciones);
		_userContainer.add(_precioPaquete);
		_userContainer.add(Recurso.setupTextoFigusPorAlbum());
		_userContainer.add(Recurso.setupTextoFigusPorPaquete());
		_userContainer.add(Recurso.setupTextoFigusRaras());
		_userContainer.add(Recurso.setupTextoPrecioPaquete());
		_userContainer.add(Recurso.setupTextoUsuarios());
		_userContainer.add(Recurso.setupDivisionSuperior());
		_userContainer.add(Recurso.setupDivisionInferior());
		_userContainer.add(Recurso.setupTextoSimulaciones());

		_userContainer.add(Recurso.setupUserLogoPanini());

		frame.getContentPane().add(_userContainer);
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

		_figusRaras.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				_presenter.eventoTeclado(ke, _figusRaras);
			}
		});
		_precioPaquete.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				_presenter.eventoTeclado(ke, _precioPaquete);
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

		_simulaciones.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) {
				_presenter.eventoTeclado(ke, _simulaciones);
			}
		});

		_iniciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				if (camposContienenTexto() && argumentosValidos()) {
					modoEspera();
					_presenter.eventoIniciar(_figusPorAlbum, _figusPorPaquete, _figusRaras, _precioPaquete, _usuarios,
							_simulaciones);
				}
			}

		});
	}

	public void modoEspera() {
		setInteracciones(false);
		_barraDeProgreso.setIndeterminate(true);
	}

	public void modoNormal() {
		setInteracciones(true);
		_barraDeProgreso.setIndeterminate(false);
	}

	private boolean argumentosValidos() {
		return _presenter.argumentosValidos(_figusPorPaquete, _figusPorAlbum, _figusRaras);
	}

	private boolean camposContienenTexto() {
		return _presenter.camposContienenTexto(_usuarios, _simulaciones, _figusPorAlbum, _figusPorPaquete, _figusRaras,
				_precioPaquete);
	}

	public void mensajeEmergente(String msg) {
		JOptionPane.showMessageDialog(null, msg);
	}

	public void setBtnIniciar(boolean value) {
		_iniciar.setEnabled(value);
		updateFrame();
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
		_figusPorAlbum.setEnabled(value);
		_figusPorPaquete.setEnabled(value);
		_figusRaras.setEnabled(value);
		_precioPaquete.setEnabled(value);
		_usuarios.setEnabled(value);
		_escenarios.setEnabled(value);
		_simulaciones.setEnabled(value);
		_iniciar.setEnabled(value);

		_costoPromedio.setEnabled(value);
		_tipoDeEscenario.setEnabled(value);
		_paquetesComprados.setEnabled(value);
		_figuritasRepetidas.setEnabled(value);
	}

}
