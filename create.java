package Pingpongapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class create {
    public static void crear(Connection connection, Usuario usuario) {
        String insertSQL = "INSERT INTO db_JDBC.usuarios (nombre, edad, nacionalidad) VALUES (?, ?, ?)";

        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setInt(2, usuario.getEdad());
            pstmt.setString(3, usuario.getPais());

            int rowsInserted = pstmt.executeUpdate();
            System.out.println(rowsInserted + " fila(s) insertada(s).");

        } catch (Exception e) {
            System.err.println("Error al insertar el usuario:");
            e.printStackTrace();
        }
    }
}
