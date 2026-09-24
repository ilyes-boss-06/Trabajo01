/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo1.conectores;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BOSS
 */
public class ConexionesDB {
    private static final String URL = "jdbc:mysql://localhost:3306/reto_ventas_dao";
	private static final String USER = "dam";
	private static final String PASS =  "1234";

	public static Connection getConnection() {
		Connection con = null; 
		try {
			con = DriverManager.getConnection(URL, USER, PASS);
		} catch (SQLException e) {
	System.out.println("Error al conectar con la base de datos: " + e.getMessage());
		}
		return con;
	}
}
