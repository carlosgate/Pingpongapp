package Pingpongapp;

import java.awt.CardLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

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
        RegistroUsuarioSwing panelRegistro = new RegistroUsuarioSwing(cardLayout, panelPrincipal);
        
        //Vista de formulario para borrar datos
        EliminarUsuario panelBaja = new EliminarUsuario(cardLayout, panelPrincipal);

        // Agregamos las "tarjetas" al panel principal
        panelPrincipal.add(panelMenu, "menu");
        panelPrincipal.add(panelRegistro, "registro");
        panelPrincipal.add(panelBaja, "eliminar");

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
        JButton btnModificar = new JButton("Modificar datos de usuario");
        JButton btnEliminar = new JButton ("Eliminar usuario");
        JButton btnRanking = new JButton ("Consultar ranking");

        btnRegistrar.addActionListener(e -> {
            cardLayout.show(panelPrincipal, "registro");
        });
        
        btnEliminar.addActionListener(e -> {
        	cardLayout.show(panelPrincipal, "eliminar");
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(btnRegistrar, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(btnModificar, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(btnEliminar, gbc);
        
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(btnRanking, gbc);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new inicio());
    }
}
