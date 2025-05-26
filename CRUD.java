package Pingpongapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;
//-----------------------------------------------------------------------------------------------//
public class CRUD{
	static Scanner sc = new Scanner(System.in);
	static Connection connection;
	public static void main(String[] args) {
		connect();
		System.out.println("Que accion deseas realizar: Create/Read/Update/Delete");
		Character action = sc.next().charAt(0);
		sc.nextLine();
		switch (Character.toUpperCase(action)) {
		case 'C':
			create.crear(connection,sc);
			break;
		case 'R':
			read.consultar(connection, sc);
			break;
		case 'U':
			update.modificar(connection,sc);
			break;
		case 'D':
			delete.eliminar(connection, sc);
			break;
		default:
			System.err.println("Introduce una acción válida.");
			break;
		}//CIERRE DEL SWITCH
	}//CIERRE DEL MAIN
//--------------------------------------------------------------------------------//
	public static void connect() {
		final String url = "jdbc:mysql://localhost:3306/db_JDBC";
		final String user = "root";
		final String password = "admin";
		try {
			connection = DriverManager.getConnection(url,user,password);
			System.out.println("Connection successful");}
		catch(Exception e) {System.err.println("Error de conexión");}
	}//CIERRE DEL MÉTODO PARA CONECTAR A LA BDD
//-------------------------------------------------------------------------------------------------------//	
}//CIERRE DE LA CLASE