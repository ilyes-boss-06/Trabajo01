/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo1.utilidades;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import trabajo1.conectores.ConexionesDB;
/**
 *
 * @author 2DAM
 */
public class LibroRepositoryMySQL implements LibroRepository<ModeloLibro>{

    @Override
    public List<ModeloLibro> mostrarLibros() {
        List<ModeloLibro> Libros = new ArrayList<>();
	    String sql = "select * from libros;";

			try (Connection conn = ConexionesDB.getConnection();
					PreparedStatement pstmt = conn.prepareStatement(sql);
					ResultSet rs = pstmt.executeQuery()) {

				while (rs.next()) {
					Libros.add(mapearFila(rs));
					
				}

			} catch (SQLException e) {
				System.err.println("Error SQL al obtener todos los libros: " + e.getMessage());
			}
			return Libros;
    }

    @Override
    public ModeloLibro obtenerPorTitulo(String titulo) {
        String sql = "select * from libros where titulo = ?";

		try (Connection conn = ConexionesDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, titulo);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return mapearFila(rs);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al buscar " + titulo + ": " + e.getMessage());
		}
		return null; // no encontrado
    }

    
    
    private ModeloLibro mapearFila(ResultSet rs) throws SQLException {
		ModeloLibro libro = new ModeloLibro();
		libro.setId(rs.getString("id"));
		libro.setTitulo(rs.getString("titulo"));
		libro.setAutor(rs.getString("autor"));
		libro.setPrecio(rs.getDouble("precio"));
		libro.setStock(rs.getInt("stock"));
		return libro;

	}
    @Override
    public boolean insertar(ModeloLibro objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModeloLibro eliminarPorTitulo(String titulo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void CopiarArchivos() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModeloLibro buscarPorRango(int rango) {
         String sql = "select * from libros where precio > ?";

		try (Connection conn = ConexionesDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setInt(1, rango);

			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return mapearFila(rs);
				}
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al buscar libros con precio mayor a " + rango + "€ " + e.getMessage());
		}
		return null; // no encontrado
    }

    @Override
    public ModeloLibro buscarPorCantidadStock(int stock) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
