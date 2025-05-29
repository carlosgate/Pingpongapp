package Pingpongapp;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class RegistroUsuarioSwing extends JPanel {
	private void cargarPaises() {
		String[] countryCodes = Locale.getISOCountries();
		for (String code : countryCodes) {
			Locale locale = new Locale("", code);
			String countryName = locale.getDisplayCountry(new Locale("es")); // Español
			paisComboBox.addItem(countryName);
		}
	}

	private JTextField nombreField;
	private JTextField edadField;
	private JComboBox<String> paisComboBox;
	private JButton registrarBtn;
	private JTextArea resultadoArea;
	private JButton volverBtn;
	private CardLayout cardLayout;
	private JPanel panelPrincipal;

	public RegistroUsuarioSwing(CardLayout cardLayout, JPanel panelPrincipal) {
		this.cardLayout = cardLayout;
		this.panelPrincipal = panelPrincipal;

		// Layout básico con GridBagLayout
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(10, 10, 10, 10);

		// Nombre
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		add(new JLabel("Nombre:"), gbc);
		nombreField = new JTextField(20);
		gbc.gridx = 1;
		add(nombreField, gbc);

		// País
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(new JLabel("País:"), gbc);
		paisComboBox = new JComboBox<>();
		cargarPaises();
		gbc.gridx = 1;
		add(paisComboBox, gbc);

		// Edad
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(new JLabel("Edad:"), gbc);
		edadField = new JTextField(20);
		gbc.gridx = 1;
		add(edadField, gbc);

		// Botón Registrar
		registrarBtn = new JButton("Registrar");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.NONE;
		gbc.anchor = GridBagConstraints.EAST;
		add(registrarBtn, gbc);

		volverBtn = new JButton("Volver");
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		gbc.anchor = GridBagConstraints.WEST;
		add(volverBtn, gbc);

		// Área de resultado
		resultadoArea = new JTextArea(5, 30);
		resultadoArea.setEditable(false);
		gbc.gridy = 4;
		add(new JScrollPane(resultadoArea), gbc);

		// Acción botón
		registrarBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario usuario = registrarUsuario();
				Connection conn = null;
				try {
					conn = conectar.connect();
					create.crear(conn, usuario);
					conectar.cerrarConexion(conn);
				}
				catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		// Acción botón volver
		volverBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panelPrincipal, "menu");
			}
		});

	}

	private Usuario registrarUsuario() {
		try {
			String nombre = nombreField.getText();
			int edad = Integer.parseInt(edadField.getText());
			String paisSeleccionado = (String) paisComboBox.getSelectedItem();

			Usuario usuario = new Usuario.Builder()
					.setNombre(nombre)
					.setPais(paisSeleccionado)
					.setEdad(edad)
					.build();

			resultadoArea.setText("Usuario registrado:\n");
			resultadoArea.append("Nombre: " + usuario.getNombre() + "\n");
			resultadoArea.append("pais: " + usuario.getPais() + "\n");
			resultadoArea.append("Edad: " + usuario.getEdad() + "\n");
			return usuario;

		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Edad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);return null;
		}
	}

	public String getNombre() {
		return nombreField.getText().trim();
	}

	public int getEdad() {
		try {
			return Integer.parseInt(edadField.getText().trim());
		} catch (NumberFormatException e) {
			return -1;
		}
	}

	public String getPaisSeleccionado() {
		return (String) paisComboBox.getSelectedItem();
	}

}
