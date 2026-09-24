/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo1.conectores;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author BOSS
 */
public class ConexionesDB {
	private static final Dotenv dotenv = Dotenv.load();

	private static final String URL = dotenv.get("DB_URL");
	private static final String USER = dotenv.get("DB_USER");
	private static final String PASS = dotenv.get("DB_PASSWORD");

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
