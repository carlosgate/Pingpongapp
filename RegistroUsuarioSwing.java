package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegistroUsuarioSwing extends JFrame {

    private JTextField nombreField;
    private JTextField emailField;
    private JTextField edadField;
    private JButton registrarBtn;
    private JTextArea resultadoArea;

    public RegistroUsuarioSwing() {
        super("Registro de Usuario");

        // Layout básico con GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Nombre
        gbc.gridx = 0; gbc.gridy = 0;
        add(new JLabel("Nombre:"), gbc);
        nombreField = new JTextField(20);
        gbc.gridx = 1;
        add(nombreField, gbc);

        // Email
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        // Edad
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Edad:"), gbc);
        edadField = new JTextField(20);
        gbc.gridx = 1;
        add(edadField, gbc);

        // Botón Registrar
        registrarBtn = new JButton("Registrar");
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(registrarBtn, gbc);

        // Área de resultado
        resultadoArea = new JTextArea(5, 30);
        resultadoArea.setEditable(false);
        gbc.gridy = 4;
        add(new JScrollPane(resultadoArea), gbc);

        // Acción botón
        registrarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null); // Centrar ventana
        setVisible(true);
    }

    private void registrarUsuario() {
        try {
            String nombre = nombreField.getText();
            String email = emailField.getText();
            int edad = Integer.parseInt(edadField.getText());

            Usuario usuario = new Usuario.Builder()
                    .setNombre(nombre)
                    .setEmail(email)
                    .setEdad(edad)
                    .build();

            resultadoArea.setText("Usuario registrado:\n");
            resultadoArea.append("Nombre: " + usuario.getNombre() + "\n");
            resultadoArea.append("Email: " + usuario.getEmail() + "\n");
            resultadoArea.append("Edad: " + usuario.getEdad() + "\n");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Edad debe ser un número válido", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new RegistroUsuarioSwing());
    }
}
