package Pingpongapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Scanner;

public class delete {

	public static void eliminar (Connection connection, Scanner sc) {
		try {
			Statement statement = connection.createStatement();
			ResultSet result = statement.executeQuery("SELECT * FROM db_jdbc.usuarios LIMIT 1");
			ResultSetMetaData metaData = result.getMetaData();
			int columnCount = metaData.getColumnCount();
			StringBuilder columnas = new StringBuilder();
			for(int i = 1; i <= columnCount; i++){
				columnas.append(metaData.getColumnName(i));
				if(i < columnCount) {columnas.append(",");}
		}
			while(true) {
				System.out.println("Selecciona por que campo quieres filtrar: " + columnas);
				String columnasTexto = columnas.toString();
				String[] col = columnasTexto.split(",");
				String columnacondicion = sc.nextLine();
				if(!Arrays.asList(col).contains(columnacondicion)) {System.err.println("No existe ese campo.");continue;}
				else {
					System.out.println("Que valor debe contener?");
					int value = sc.nextInt();
					String sql = ("DELETE FROM db_JDBC.usuarios WHERE id  = ?");
					PreparedStatement ps = connection.prepareStatement(sql);
					ps.setInt(1, value);
					System.out.print("Vas a ELIMINAR el valor de la columna  cuyo id es " + value + ". Es correcto?(y/n)");
					char execute = sc.next().charAt(0);
					switch(Character.toUpperCase(execute)) {
					case 'Y':
					int filasModificadas = ps.executeUpdate();
					System.out.println(filasModificadas + " fila(s) actualizada(s).");
					break;
					default : System.err.println("No se ejecutó el borrado de datos.");
					break;
					}
					break;
					
				}
			}
		}
		catch(Exception e) {System.err.println("Error. Algo ha salido mal...quizá sea mejor así jeje");}
		// TODO Auto-generated method stub

	}

}
