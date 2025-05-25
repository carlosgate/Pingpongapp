package test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class inicio extends JFrame {
    private CardLayout cardLayout;
    private JPanel panelPrincipal;

    public inicio() {
        super("Maters Tech Talent Ping Pong");

        // CardLayout para cambiar entre vistas
        cardLayout = new CardLayout();
        panelPrincipal = new JPanel(cardLayout);

        // Vista de menú inicial
        JPanel panelMenu = crearPanelMenu();

        // Vista de formulario de registro
        JPanel panelRegistro = crearPanelRegistro();

        // Agregamos las "tarjetas" al panel principal
        panelPrincipal.add(panelMenu, "menu");
        panelPrincipal.add(panelRegistro, "registro");

        // Mostrar el menú inicial
        cardLayout.show(panelPrincipal, "menu");

        add(panelPrincipal);

        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel crearPanelMenu() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JButton btnRegistrar = new JButton("Registrar Usuario");

        btnRegistrar.addActionListener(e -> {
            cardLayout.show(panelPrincipal, "registro");
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(btnRegistrar, gbc);

        return panel;
    }

    private JPanel crearPanelRegistro() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
       
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 10, 10, 10); // margen
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL; // se estira
        gbc.weightx = 1.0; // ocupa espacio extra
        JTextField campoNombre = new JTextField();
        panel.add(campoNombre, gbc);

        // Campo "Email"
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        panel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        JTextField campoEmail = new JTextField();
        panel.add(campoEmail, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 1;
        JButton btnVolver = new JButton("Volver");
        panel.add(btnVolver,gbc);
        
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.weightx = 1;
        JButton btnRegistrar = new JButton("Registrar usuario");
        panel.add(btnRegistrar,gbc);
        
        btnVolver.addActionListener(e -> {
            cardLayout.show(panelPrincipal, "menu");
        });

        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

       

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new inicio());
    }
}
