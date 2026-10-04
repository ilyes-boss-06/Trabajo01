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
 * Se encarga de abrir conexiones con la base de datos MySQL.
 * Las credenciales no estan en el codigo: se leen de un fichero .env
 * mediante la libreria dotenv-java.
 * 
 * @author Ilyes Ben Jaber
 */
public class ConexionesDB {
    
        /** 
         * Fichero .env cargado una unica vez.
         */
	private static final Dotenv dotenv = Dotenv.load();

        /** 
         * URL JDBC de la base de datos (clave db.url del .env). 
         */
	private static final String URL = dotenv.get("db.url");
        
        /** 
         * Usuario de la base de datos (clave db.user del .env). 
         */
	private static final String USER = dotenv.get("db.user");
        
        /**
         * Contrasena de la base de datos (clave db.password del .env). 
         */
	private static final String PASS = dotenv.get("db.password");

        /**
         * Abre una conexion nueva con la base de datos.
         * 
         * @return la conexion abierta, o null si no se ha podido conectar
         */
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
