package Pingpongapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class getColumnas {

    public static void imprimirDatosPorId(Connection connection, int id) {
        try {
            String sql = "SELECT * FROM db_jdbc.usuarios WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();

                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    String valor = rs.getString(i);
                    System.out.println(columnName + ": " + valor);
                }
            } else {
                System.out.println("No se encontró usuario con id " + id);
            }
        } catch (Exception e) {
            System.err.println("Error al obtener datos: " + e.getMessage());
        }
    }
}
