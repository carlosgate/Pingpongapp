package Pingpongapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class JDBC {
	public static void main(String[] args) {
		Connection connection;
		final String url = "jdbc:mysql://localhost:3306/db_JDBC";
		final String user = "root";
		final String password = "admin";
		try {
			connection = DriverManager.getConnection(url,user,password);
			System.out.println("Connection successful");
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM db_JDBC.usuarios");
			ResultSetMetaData rsmd = result.getMetaData();
			int columnCount = rsmd.getColumnCount();
			for (int i = 1; i <= columnCount; i++) {
				System.out.printf("%15s", rsmd.getColumnName(i).toString());
			}
			int spacing = ((columnCount *15));
			String textoFormateado = String.format("%n%" + spacing +  "s"," "," ").replace(' ','-');
			System.out.println(textoFormateado);
			while(result.next()) {
				int id = result.getInt("id");
				String nombre = result.getString("nombre");
				int edad = result.getInt("edad");
				String nacion = result.getString("nacionalidad");
				System.out.printf("%15d%15s%15d%15s%n", id, nombre, edad, nacion);
			}
			result.close();
			statement.close();
			connection.close();
			System.out.println("Connection closed");
		}
		catch (Exception e) {System.err.println("Connection error");}
	}
}