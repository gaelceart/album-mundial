package interfaz;

import java.awt.EventQueue;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Cursor;
import java.awt.event.MouseMotionAdapter;

@SuppressWarnings("rawtypes")
public class Interfaz {

	private JFrame frame;
	private JPanel _albumContainer;
	private JPanel _statisticsContainer;
	private JPanel _userContainer;
	private JComboBox _escenarios;
	private JButton _donar;
	private JButton _intercambiar;
	private JButton _iniciar;
	
	
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
		_donar = Recurso.setupBtnDonar();
		_intercambiar = Recurso.setupBtnIntercambiar();
		
		desactivarBtnIniciar();
		setupEventosDeUsuario();
		
		_userContainer.add(_iniciar);
		_userContainer.add(_escenarios);
		_userContainer.add(_donar);
		_userContainer.add(_intercambiar);
		
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
					
				}
				else {
					activarBtnIniciar();
				}
				
			}

		});
		
		_donar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		_intercambiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
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
