package Pingpongapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class delete {

    public static void eliminar(Connection connection, Scanner sc) {
        try {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT id FROM db_jdbc.usuarios");
            List<Integer> idsExistentes = new ArrayList<>();
            while (result.next()) {
                idsExistentes.add(result.getInt("id"));
            }

            int id;
            while (true) {
                System.out.println("Escoge el id de usuario a eliminar:");
                if (!sc.hasNextInt()) {
                    System.err.println("Introduce un número válido");
                    sc.next();
                    continue;
                }
                id = sc.nextInt();
                sc.nextLine();

                if (!idsExistentes.contains(id)) {
                    System.err.println("No existe ningún usuario con ese id.");
                    continue;
                }

                System.out.println("Vas a eliminar el usuario:");
                getColumnas.imprimirDatosPorId(connection, id);

                System.out.print("¿Es correcto? (y/n): ");
                String respuesta = sc.nextLine().trim().toLowerCase();

                if (respuesta.equals("y")) {
                    String deleteSql = "DELETE FROM db_jdbc.usuarios WHERE id=?";
                    PreparedStatement ps = connection.prepareStatement(deleteSql);
                    ps.setInt(1, id);
                    int filasEliminadas = ps.executeUpdate();
                    System.out.println(filasEliminadas + " fila(s) eliminada(s)");
                    break;
                } else if (respuesta.equals("n")) {
                    System.out.println("Operación cancelada. Puedes elegir otro id.");
                    continue;
                } else {
                    System.out.println("Respuesta no válida. Saliendo del programa.");
                    break;
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
