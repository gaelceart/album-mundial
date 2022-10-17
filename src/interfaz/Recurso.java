package interfaz;

import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Recurso {

	 public static JFrame setupFrame() {
		JFrame frame = new JFrame();
		frame.setResizable(false);
		frame.setTitle("Simulador Album Mundial");
		Toolkit miPantalla = Toolkit.getDefaultToolkit();
		Image miIcono = miPantalla.getImage("src/icono.jpg");
		frame.setIconImage(miIcono);
		frame.setBounds(0, 0, 800, 600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return frame;
	 }
	 
	 public static JPanel setupAlbumContainer() {
		 JPanel albumContainer = new JPanel();
		 albumContainer.setBounds(0, 0, 575, 400);
		 albumContainer.setLayout(null);
		 return albumContainer;
	 }
	 
	 public static JPanel setupStatisticsContainer() {
		 JPanel statisticsContainer = new JPanel();
		 statisticsContainer.setBounds(0, 400, 575, 200);
		 statisticsContainer.setLayout(null);
		 return statisticsContainer;
	 }
	 
	 public static JPanel setupUserContainer() {
		 JPanel userContainer = new JPanel();
		 userContainer.setBounds(575, 0, 225, 600);
		 userContainer.setLayout(null);
		 return userContainer;
	 }
	 
	 public static JLabel setupAlbumImage() {
		 JLabel albumImagen = new JLabel();
		 albumImagen.setSize(575, 400);
		 ImageIcon imagen = new ImageIcon("src/album.jpg");
		 Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(albumImagen.getWidth(), albumImagen.getHeight(), Image.SCALE_DEFAULT));
		 albumImagen.setIcon(icono);
		 return albumImagen;
	 }
	 
	 public static JLabel setupUserImage() {
		 JLabel userImagen = new JLabel();
		 userImagen.setSize(225, 600);
		 ImageIcon imagen = new ImageIcon("src/usuario.jpg");
		 Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(userImagen.getWidth(), userImagen.getHeight(), Image.SCALE_DEFAULT));
		 userImagen.setIcon(icono);
		 return userImagen;
	 }
	 
	 public static JLabel setupStatisticsImage() {
		 JLabel statisticsImagen = new JLabel();
		 statisticsImagen.setSize(170, 160);
		 ImageIcon imagen = new ImageIcon("src/copa.png");
		 Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(statisticsImagen.getWidth(), statisticsImagen.getHeight(), Image.SCALE_DEFAULT));
		 statisticsImagen.setIcon(icono);
		 return statisticsImagen;
	 }
	 
	 public static JLabel setupTextoFigusPorAlbum() {
		 JLabel cantidadDeFigusAlbum = new JLabel("Figuritas por Álbum", SwingConstants.CENTER);
		 cantidadDeFigusAlbum.setBounds(615, 75, 140, 35);
		 return cantidadDeFigusAlbum;
	 }
	 
	 public static JTextField setupCantidadDeFigusAlbum() {
		 JTextField cantidadDeFigusAlbum = new JTextField();
		 cantidadDeFigusAlbum.setBounds(615, 100, 140, 25);
		 return cantidadDeFigusAlbum;
	 }
	 
	 public static JLabel setupTextoFigusPorPaquete() {
		 JLabel cantidadDeFigusAlbum = new JLabel("Figuritas por Paquete", SwingConstants.CENTER);
		 cantidadDeFigusAlbum.setBounds(615, 125, 140, 35);
		 return cantidadDeFigusAlbum;
	 }
	 
	 public static JTextField setupFigusPorPaquete() {
		 JTextField cantidadDeFigusAlbum = new JTextField();
		 cantidadDeFigusAlbum.setBounds(615, 150, 140, 25);
		 return cantidadDeFigusAlbum;
	 }
	 
	 public static JLabel setupTextoUsuarios() {
		 JLabel cantidadDeFigusAlbum = new JLabel("Usuarios", SwingConstants.CENTER);
		 cantidadDeFigusAlbum.setBounds(615, 175, 140, 35);
		 return cantidadDeFigusAlbum;
	 }

	 public static JTextField setupUsuarios() {
		 JTextField cantidadDeFigusAlbum = new JTextField();
		 cantidadDeFigusAlbum.setBounds(615, 200, 140, 25);
		 return cantidadDeFigusAlbum;
	 }
	 
	 public static JButton setupBtnIniciar() {
			JButton btnIniciar = new JButton("Iniciar");
			btnIniciar.setBounds(615, 260, 140, 25);
			return btnIniciar;
	 }
	 
	 public static JComboBox setupBtnEscenarios() {
			JComboBox menuEscenarios = new JComboBox();
			menuEscenarios.setBounds(615, 294, 140, 25);
			menuEscenarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			menuEscenarios.setToolTipText("Escenarios");
			String [] escenarios = setupEscenarios();
			DefaultComboBoxModel model = new DefaultComboBoxModel(escenarios);
			menuEscenarios.setModel(model);
			menuEscenarios.setSelectedIndex(0);
			return menuEscenarios;
	 }
	 
	 private static String[] setupEscenarios() {
		String[] ret = new String[4];
		ret[0] = "Elija un escenario";
		ret[1] = "Individual";
		ret[2] = "Todos Donan";
		ret[3] = "Todos Intercambian";
		return ret;
	}


}
