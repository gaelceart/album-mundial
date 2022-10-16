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
		setupAlbumContainer();
		setupStatisticsContainer();
		setupUserContainer();
		
	}

	private void setupUserContainer() {
		_userContainer = Recurso.setupUserContainer();
		_userContainer.add(Recurso.setupUserImage());
		frame.getContentPane().add(_userContainer);
	}

	private void setupStatisticsContainer() {
		_statisticsContainer = Recurso.setupStatisticsContainer();
		_statisticsContainer.add(Recurso.setupStatisticsImage());
		frame.getContentPane().add(_statisticsContainer);
	}

	private void setupAlbumContainer() {
		_albumContainer = Recurso.setupAlbumContainer();
		_albumContainer.add(Recurso.setupAlbumImage());
		frame.getContentPane().add(_albumContainer);
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
