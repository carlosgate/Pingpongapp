package Pingpongapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class update {

	public static void modificar (Connection connection, Scanner sc) {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM db_JDBC.usuarios LIMIT 1");
			ResultSetMetaData metaData = result.getMetaData();
			int columnCount = metaData.getColumnCount();
			StringBuilder columnas = new StringBuilder();
			for(int i = 1; i <= columnCount; i++){
				columnas.append(metaData.getColumnName(i));
				if(i < columnCount) {columnas.append(",");}
			}
			while(true) {
				System.out.println("Que columna quieres modificar?: " + columnas);
				String columnasTexto = columnas.toString();
				String[] col = columnasTexto.split(",");
				String input = sc.nextLine();
				if(!Arrays.asList(col).contains(input)) {System.err.println("No existe esa columna.");continue;}
				else {
					System.out.println("Cuál es el nuevo valor?");
					String value = sc.nextLine();
					System.out.println("Que fila se debe cambiar (introduce su número id)");
					Integer identifier = sc.nextInt();
					String sql = ("UPDATE db_JDBC.usuarios SET " + input + " = ? " + "WHERE " + "id  = ?");
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setString(1, value);
					ps.setInt(2, identifier);
					System.out.print("Vas a realizar el valor de la columna " + input + " cuyo id es " + identifier + " por el valor "+ value + ". Es correcto?(y/n)");
					char execute = sc.next().charAt(0);
					switch(Character.toUpperCase(execute)) {
					case 'Y':
					int filasModificadas = ps.executeUpdate();
					System.out.println(filasModificadas + " fila(s) actualizada(s).");
					break;
					default : System.err.println("No se ejecutó el update.");
					break;
					}
					break;	
				}
			}
		}
		catch(Exception e) {System.err.println("Ups,algo ha salido mal...");}
	}

}
