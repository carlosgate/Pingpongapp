package Pingpongapp;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Scanner;

public class read {

	public static void consultar (Connection connection, Scanner sc) {
		try{
			Statement statement = connection.createStatement();
			ResultSet getcolumns = statement.executeQuery("SELECT * FROM db_jdbc.usuarios LIMIT 1");
			ResultSetMetaData metaData = getcolumns.getMetaData();
			int columnCount = metaData.getColumnCount();
			StringBuilder columnas = new StringBuilder();
			for(int a = 1; a <= columnCount; a++){
				columnas.append(metaData.getColumnName(a));
				if(a < columnCount) {columnas.append(",");}}
			System.out.println("Que campos deseas ver? Introduce una coma (,) entre ellos: " + columnas);
			String campos = sc.nextLine();
			System.out.println("Quieres filtrar? y/n");
			char filtrar = sc.next().charAt(0);
			switch(Character.toUpperCase(filtrar)) {
			case 'Y':
				break;
			case 'N': 
				ResultSet result = statement.executeQuery("SELECT " + campos + " FROM db_JDBC.usuarios");
				ResultSetMetaData md = result.getMetaData();
				int queryColumns = md.getColumnCount();
				for (int i = 1; i <= queryColumns; i++) {
					System.out.printf("%-18s", md.getColumnName(i).toString());
				}
				int spacing = ((queryColumns *15));
				String textoFormateado = String.format("%n%" + spacing +  "s"," "," ").replace(' ','-');
				System.out.println(textoFormateado);
				while(result.next()) {
					for (int i = 1; i <= queryColumns; i++) {
						Object value = result.getObject(i);
						System.out.printf("%-18s", value != null ? value.toString() : "NULL");
					}
	            System.out.println();
				
				}
			break;
			}
			
		}
		catch(Exception e) {System.err.println("Este es un mensaje de error genérico.");}

	}

}
