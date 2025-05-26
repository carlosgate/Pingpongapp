package Pingpongapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class create {

	public static void crear(Connection connection, Scanner sc) {
		// TODO Auto-generated method stub
		try{
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM db_JDBC.usuarios LIMIT 1");
			ResultSetMetaData metaData = result.getMetaData();
	        int columnCount = metaData.getColumnCount();
	        System.out.println("Introduce los siguientes campos:");
	        for (int i = 2; i <= columnCount; i++) {
	            System.out.println("- " + metaData.getColumnName(i));}
	        StringBuilder values = new StringBuilder();
	        for (int i = 2; i <= columnCount; i++) {
	            System.out.print(metaData.getColumnName(i) + ": ");
	            String input = sc.nextLine();
	            values.append("'").append(input).append("'");
	            if (i < columnCount) values.append(", ");
	        }

	        // Construir la sentencia INSERT
	        StringBuilder columns = new StringBuilder();
	        for (int i = 2; i <= columnCount; i++) {
	            columns.append(metaData.getColumnName(i));
	            if (i < columnCount) columns.append(", ");
	        }

	        String insertSQL = "INSERT INTO db_JDBC.usuarios (" + columns + ") VALUES (" + values + ")";
	        int rowsInserted = statement.executeUpdate(insertSQL);

	        System.out.println(rowsInserted + " fila(s) insertada(s).");
			}
			catch(Exception e){System.err.println("Algo ha salido mal");}
	}

}
