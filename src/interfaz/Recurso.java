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
		 albumContainer.setBounds(0, 0, 540, 460);
		 albumContainer.setLayout(null);
		 return albumContainer;
	 }
	 
	 public static JPanel setupStatisticsContainer() {
		 JPanel statisticsContainer = new JPanel();
		 statisticsContainer.setBounds(0, 400, 780, 200);
		 statisticsContainer.setLayout(null);
		 return statisticsContainer;
	 }
	 
	 public static JPanel setupUserContainer() {
		 JPanel userContainer = new JPanel();
		 userContainer.setBounds(550, 0, 225, 400);
		 userContainer.setLayout(null);
		 return userContainer;
	 }
	 
	 public static JComboBox setupBtnEscenarios() {
			JComboBox menuEscenarios = new JComboBox();
			menuEscenarios.setBounds(650, 330, 124, 22);
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
		ret[0] = "Seleccione un archivo...";
		ret[1] = "Individual";
		ret[2] = "Todos Donan";
		ret[3] = "Todos Intercambian";
		return ret;
	}

	 public static JButton setupBtnDonar() {
			JButton btnEliminarAllArcos = new JButton("Donar");
			btnEliminarAllArcos.setBounds(650, 409, 124, 23);
			return btnEliminarAllArcos;
	 }
	 
	 public static JButton setupBtnIntercambiar() {
			JButton btnEliminarAllVertices = new JButton("Intercambiar");
			btnEliminarAllVertices.setBounds(650, 443, 124, 23);
			return btnEliminarAllVertices;
	 }
	 
	 public static JLabel setupAlbumImage() {
		 JLabel albumImagen = new JLabel();
		 albumImagen.setSize(575, 450);
		 ImageIcon imagen = new ImageIcon("src/icono.jpg");
		 Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(albumImagen.getWidth(), albumImagen.getHeight(), Image.SCALE_DEFAULT));
		 albumImagen.setIcon(icono);
		 return albumImagen;
	 }
	 
	 public static JLabel setupUserImage() {
		 JLabel userImagen = new JLabel();
		 userImagen.setSize(225, 400);
		 ImageIcon imagen = new ImageIcon("src/usuario.jpg");
		 Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(userImagen.getWidth(), userImagen.getHeight(), Image.SCALE_DEFAULT));
		 userImagen.setIcon(icono);
		 return userImagen;
	 }
	 
	 public static JLabel setupStatisticsImage() {
		 JLabel statisticsImagen = new JLabel();
		 statisticsImagen.setSize(780, 155);
		 ImageIcon imagen = new ImageIcon("src/estadisticas.jpg");
		 Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(statisticsImagen.getWidth(), statisticsImagen.getHeight(), Image.SCALE_DEFAULT));
		 statisticsImagen.setIcon(icono);
		 return statisticsImagen;
	 }
}
