package interfaz;
import selecciones.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
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
import javax.swing.JTextPane;
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
		frame.setLayout(null);
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
		statisticsContainer.setBackground(new Color(0, 0, 0));
		return statisticsContainer;
	}

	public static JPanel setupUserContainer() {
		JPanel userContainer = new JPanel();
		userContainer.setBounds(575, 0, 225, 600);
		userContainer.setLayout(null);
		userContainer.setBackground(new Color(90, 15, 40)/*(141, 27, 61)*/);
		return userContainer;
	}

	public static JLabel setupAlbumImage() {
		JLabel albumImagen = new JLabel();
		albumImagen.setSize(575, 400);
		ImageIcon imagen = new ImageIcon("src/album.jpg");
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(albumImagen.getWidth(), albumImagen.getHeight(),
				Image.SCALE_DEFAULT));
		albumImagen.setIcon(icono);
		return albumImagen;
	}

	public static JLabel setupUserImage() {
		JLabel userImagen = new JLabel();
		userImagen.setSize(225, 600);
		ImageIcon imagen = new ImageIcon("src/usuario.jpg");
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(userImagen.getWidth(), userImagen.getHeight(),
				Image.SCALE_DEFAULT));
		userImagen.setIcon(icono);
		return userImagen;
	}

	public static JLabel setupUserLogoPanini() {
		JLabel logoImagen = new JLabel();
		logoImagen.setBounds(0, 370, 210, 215);
		//logoImagen.setSize(150, 150);
		ImageIcon imagen = new ImageIcon("src/panini.png");
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(logoImagen.getWidth(),logoImagen.getHeight(), 
				Image.SCALE_DEFAULT));
		logoImagen.setIcon(icono);
		return logoImagen;
	}
	
	public static JLabel setupStatisticsImage() {
		JLabel statisticsImagen = new JLabel();
		statisticsImagen.setBounds(-25, 0, 150, 160);
		ImageIcon imagen = new ImageIcon("src/copa.png");
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(statisticsImagen.getWidth(),statisticsImagen.getHeight(), 
				Image.SCALE_DEFAULT));
		statisticsImagen.setIcon(icono);
		return statisticsImagen;
	}
	
	public static JLabel setupTextoFigusPorAlbum() {
		JLabel cantidadDeFigusAlbum = new JLabel("Figuritas por Álbum", SwingConstants.CENTER);
		cantidadDeFigusAlbum.setBounds(40, 25, 140, 35);
		cantidadDeFigusAlbum.setFont(new Font("Arial Black", Font.PLAIN, 12));
		cantidadDeFigusAlbum.setForeground(Color.WHITE);
		return cantidadDeFigusAlbum;
	}

	public static JTextField setupCantidadDeFigusAlbum() {
		JTextField cantidadDeFigusAlbum = new JTextField();
		cantidadDeFigusAlbum.setBounds(40, 50, 140, 25);
		return cantidadDeFigusAlbum;
	}

	public static JLabel setupTextoFigusPorPaquete() {
		JLabel cantidadDeFigusPaquete = new JLabel("Figuritas por Paquete", SwingConstants.CENTER);
		cantidadDeFigusPaquete.setBounds(40, 75, 140, 35);
		cantidadDeFigusPaquete.setFont(new Font("Arial Black", Font.PLAIN, 12));
		cantidadDeFigusPaquete.setForeground(Color.WHITE);
		return cantidadDeFigusPaquete;
	}

	public static JTextField setupFigusPorPaquete() {
		JTextField cantidadDeFigusPaquete = new JTextField();
		cantidadDeFigusPaquete.setBounds(40, 100, 140, 25);
		return cantidadDeFigusPaquete;
	}
	
	public static JLabel setupTextoFigusRaras() {
		JLabel usuarios = new JLabel("Figuritas Raras", SwingConstants.CENTER);
		usuarios.setBounds(40, 125, 140, 35);
		usuarios.setFont(new Font("Arial Black", Font.PLAIN, 12));
		usuarios.setForeground(Color.WHITE);
		return usuarios;
	}

	public static JTextField setupFigusRaras() {
		JTextField usuarios = new JTextField();
		usuarios.setBounds(40, 150, 140, 25);
		return usuarios;
	}
	
	public static JLabel setupTextoUsuarios() {
		JLabel usuarios = new JLabel("Usuarios", SwingConstants.CENTER);
		usuarios.setBounds(40, 175, 140, 35);
		usuarios.setFont(new Font("Arial Black", Font.PLAIN, 12));
		usuarios.setForeground(Color.WHITE);
		return usuarios;
	}

	public static JTextField setupUsuarios() {
		JTextField usuarios = new JTextField();
		usuarios.setBounds(40, 200, 140, 25);
		return usuarios;
	}

	public static JButton setupBtnIniciar() {
		JButton btnIniciar = new JButton("Iniciar");
		btnIniciar.setBounds(40, /*255*/330, 140, 25);
		btnIniciar.setFont(new Font("Arial Black", Font.PLAIN, 12));
		btnIniciar.setEnabled(false);
		return btnIniciar;
	}

	public static JComboBox setupBtnEscenarios() {
		JComboBox menuEscenarios = new JComboBox();
		menuEscenarios.setBounds(35, 286, 150, 25);
		menuEscenarios.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		menuEscenarios.setToolTipText("Escenarios");
		String[] escenarios = setupEscenarios();
		DefaultComboBoxModel model = new DefaultComboBoxModel(escenarios);
		menuEscenarios.setModel(model);
		menuEscenarios.setSelectedIndex(0);
		menuEscenarios.setFont(new Font("Arial Black", Font.PLAIN, 12));
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
	
	public static JLabel setupTextoEscenario() {
		JLabel textoEscenario = new JLabel("Escenario Actual", SwingConstants.LEFT);
		textoEscenario.setBounds(110, 25, 125, 30);
		textoEscenario.setFont(new Font("Arial Black", Font.PLAIN, 12));
		textoEscenario.setForeground(Color.yellow);
		return textoEscenario;
	}
	
	public static JTextField setupEscenario() {
		JTextField escenario = new JTextField("");
		escenario.setBounds(240, 25, 125, 25);
		escenario.setEditable(true);
		escenario.setFont(new Font("Arial", Font.PLAIN, 12));
		escenario.setForeground(Color.magenta);
		return escenario;
	}
	
	public static JLabel setupTextoCostoPromedio() {
		JLabel costoPromedio = new JLabel("Costo Promedio", SwingConstants.LEFT);
		costoPromedio.setBounds(110, 60, 125, 30);
		costoPromedio.setFont(new Font("Arial", Font.PLAIN, 12));
		costoPromedio.setForeground(Color.WHITE);
		return costoPromedio;
	}
	
	public static JTextField setupCostoPromedio() {
		JTextField costoPromedio = new JTextField("");
		costoPromedio.setBounds(240, 60, 125, 25);
		costoPromedio.setFont(new Font("Arial", Font.PLAIN, 12));
		costoPromedio.setForeground(Color.black);
		return costoPromedio;
	}
	
	public static JLabel setupTextoPaquetesComprados() {
		JLabel textoPaquetes = new JLabel("Paquetes Comprados", SwingConstants.LEFT);
		textoPaquetes.setBounds(110, 95, 125, 30);
		textoPaquetes.setFont(new Font("Arial", Font.PLAIN, 12));
		textoPaquetes.setForeground(Color.WHITE);
		return textoPaquetes;
	}

	public static JTextField setupPaquetesComprados() {
		JTextField paquetes = new JTextField("");
		paquetes.setBounds(240, 95, 125, 25);
		paquetes.setFont(new Font("Arial", Font.PLAIN, 12));
		paquetes.setForeground(Color.black);
		return paquetes;
	}
	
	public static JLabel setupTextoFiguritasRepetidas() {
		JLabel figuRepetida = new JLabel("Figuritas Repetidas", SwingConstants.LEFT);
		figuRepetida.setBounds(110, 130, 125, 30);
		figuRepetida.setFont(new Font("Arial", Font.PLAIN, 12));
		figuRepetida.setForeground(Color.WHITE);
		return figuRepetida;
	}
	
	public static JTextField setupFiguritasRepetidas() {
		JTextField figuRepetida = new JTextField("");
		figuRepetida.setBounds(240, 130, 125, 25);
		figuRepetida.setFont(new Font("Arial", Font.PLAIN, 12));
		figuRepetida.setForeground(Color.black);
		return figuRepetida;
	}
	
	public static JLabel setupTextoUsuario0() {
		JLabel textoUsuario0 = new JLabel("Usuario 0", SwingConstants.CENTER);
		textoUsuario0.setBounds(415, -5, 125, 30);
		textoUsuario0.setFont(new Font("Arial Black", Font.PLAIN, 16));
		textoUsuario0.setForeground(Color.yellow);
		return textoUsuario0;
	}
	
	public static JTextPane setupPanelUsuario0() {
		JTextPane panelUsuario0 = new JTextPane();
		panelUsuario0.setEditable(true);
		panelUsuario0.setFont(new Font("Arial", Font.PLAIN, 12));
		panelUsuario0.setBackground(new Color(230, 230, 230));
		panelUsuario0.setBounds(417, 25, 120, 128);
		return panelUsuario0;
	}

	public static JPanel setupMundial() {
		JPanel mundial = new JPanel();
		mundial.setBounds(25, 25, 525, 350);
		mundial.setLayout(null);
		mundial.setBackground(new Color(50, 14, 30));
		return mundial;
	}
}
