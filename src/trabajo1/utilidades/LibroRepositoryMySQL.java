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
    public List<ModeloLibro> buscarPorAutor(String autor) {
        List<ModeloLibro> libros = new ArrayList<>();
        String sql = "select * from libros where autor = ?";

		try (Connection conn = ConexionesDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setString(1, autor);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					libros.add(mapearFila(rs));
				}
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al buscar libros de " + autor + ": " + e.getMessage());
		}
		return libros;
    }

    @Override
    public List<ModeloLibro> buscarPorRango(double precioMin, double precioMax) {
        List<ModeloLibro> libros = new ArrayList<>();
        String sql = "select * from libros where precio between ? and ?";

		try (Connection conn = ConexionesDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {

			pstmt.setDouble(1, precioMin);
			pstmt.setDouble(2, precioMax);

			try (ResultSet rs = pstmt.executeQuery()) {
				while (rs.next()) {
					libros.add(mapearFila(rs));
				}
			}

		} catch (SQLException e) {
			System.err.println("Error SQL al buscar libros entre " + precioMin + "€ y " + precioMax + "€: " + e.getMessage());
		}
		return libros;
    }

    @Override
    public List<ModeloLibro> buscarPorCantidadStock(int stock) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
