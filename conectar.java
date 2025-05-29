package Pingpongapp;

import java.sql.Connection;
import java.sql.DriverManager;
//-----------------------------------------------------------------------------------------------//
public class conectar{
	private static final String url = "jdbc:mysql://localhost:3306/db_JDBC";
	private static final String user = "root";
	private static final String password = "admin";
//--------------------------------------------------------------------------------//
	public static Connection connect() {
		try {
			return DriverManager.getConnection(url,user,password);}
		catch(Exception e) {System.err.println("Error de conexión");e.printStackTrace(); return null;}
	}//CIERRE DEL MÉTODO PARA CONECTAR A LA BDD
//-------------------------------------------------------------------------------------------------------//	
}//CIERRE DE LA CLASE
