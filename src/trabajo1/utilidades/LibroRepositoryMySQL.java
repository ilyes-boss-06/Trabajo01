/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo1.utilidades;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import trabajo1.conectores.ConexionesDB;

/**
 * Implementación del repositorio de libros utilizando una base de datos MySQL.
 * 
 * Esta clase permite realizar operaciones de consulta, inserción y eliminación
 * de libros en la tabla "libros", además de copiar información desde un archivo
 * de texto hacia la base de datos.
 *
 * @author Juan David
 */
public class LibroRepositoryMySQL implements LibroRepository<ModeloLibro>{
    
    /**
     * Obtiene todos los libros almacenados en la base de datos.
     *
     * @return lista con todos los libros encontrados en la tabla "libros".
     */
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
    
    /**
     * Busca un libro en la base de datos mediante su título.
     *
     * @param titulo título del libro que se desea buscar.
     * @return libro encontrado o null si no existe ningún libro con ese título.
     */
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
		return null;
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
    public boolean insertar(ModeloLibro libro) {
        String sql = "insert into libros (id, titulo, autor, precio, stock) values (?, ?, ?, ?, ?)";

        try (Connection conn = ConexionesDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, libro.getId());
            pstmt.setString(2, libro.getTitulo());
            pstmt.setString(3, libro.getAutor());
            pstmt.setDouble(4, libro.getPrecio());
            pstmt.setInt(5, libro.getStock());

            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }


 
@Override
public ModeloLibro eliminarPorTitulo(String titulo) {

    String sqlBuscar = "select * from libros where titulo = ?";
    String sqlEliminar = "delete from libros where id = ?";

    try (Connection conn = ConexionesDB.getConnection();
         PreparedStatement pstmtBuscar = conn.prepareStatement(sqlBuscar)) {

        pstmtBuscar.setString(1, titulo);

        try (ResultSet rs = pstmtBuscar.executeQuery()) {

            List<ModeloLibro> librosEncontrados = new ArrayList<>();

            while (rs.next()) {
                librosEncontrados.add(mapearFila(rs));
            }

            
            if (librosEncontrados.isEmpty()) {
                System.out.println("No existe ningún libro con el título: " + titulo);
                return null;
            }

            
            if (librosEncontrados.size() == 1) {

                ModeloLibro libro = librosEncontrados.get(0);

                try (PreparedStatement pstmtEliminar = conn.prepareStatement(sqlEliminar)) {

                    pstmtEliminar.setString(1, libro.getId());

                    if (pstmtEliminar.executeUpdate() > 0) {
                        System.out.println("Libro eliminado correctamente.");
                        return libro;
                    }
                }
            }

            
            System.out.println("Hay varios libros con el título: " + titulo);
            System.out.println("Selecciona el libro que quieres eliminar:");

            for (ModeloLibro libro : librosEncontrados) {
                System.out.println(
                    "ID: " + libro.getId()
                    + " | Título: " + libro.getTitulo()
                    + " | Autor: " + libro.getAutor()
                    + " | Precio: " + libro.getPrecio()
                    + " | Stock: " + libro.getStock()
                );
            }

            Scanner sc = new Scanner(System.in);

            System.out.print("Introduce el ID del libro que quieres eliminar: ");
            String id = sc.nextLine();

            if (eliminarPorId(id)) {
                System.out.println("Libro eliminado correctamente.");
            } else {
                System.out.println("No se ha podido eliminar el libro.");
            }

            return null;
        }
        
    } catch (Exception e) {
        System.out.println("Error al eliminar el libro: " + e.getMessage());
    }

    return null;
}


public boolean eliminarPorId(String id) {
        String sql = "delete from libros where id = ?";

        try (Connection conn = ConexionesDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);
            return pstmt.executeUpdate() > 0;

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return false;
    }


    @Override
    public void CopiarArchivos() {
        List<ModeloLibro> libros = new ArrayList<>();

        // 1. Se leen los libros del archivo. Si falla, se sale sin tocar la base de datos
        try (BufferedReader br = new BufferedReader(new FileReader("libros.txt"))) {
            String linea = br.readLine();
            while (linea != null) {
                if (!linea.trim().equals("")) {
                    String[] campos = linea.split("\\^");
                    ModeloLibro libro = new ModeloLibro();
                    libro.setId(campos[0]);
                    libro.setTitulo(campos[1]);
                    libro.setAutor(campos[2]);
                    libro.setPrecio(Double.parseDouble(campos[3]));
                    libro.setStock(Integer.parseInt(campos[4]));
                    libros.add(libro);
                }
                linea = br.readLine();
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo, no se ha modificado la base de datos: " + e);
            return;
        }

        // 2. Se vacia la tabla y se insertan los libros leidos
        if (guardarLibros(libros)) {
            System.out.println("Se han copiado " + libros.size() + " libros del archivo a MySQL.");
        }
    }
    
    public boolean guardarLibros(List<ModeloLibro> libros) {
        // 1. Se vacia la tabla
        String sql = "delete from libros";

        try (Connection conn = ConexionesDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }

        // 2. Se insertan los libros uno a uno
        for (int i = 0; i < libros.size(); i++) {
            insertar(libros.get(i));
        }
        return true;
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
    public List<ModeloLibro> buscarPorCantidadStock(int stockMinimo) {
        List<ModeloLibro> libros = new ArrayList<>();
        String sql = "select * from libros where stock >= ?";

        try (Connection conn = ConexionesDB.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, stockMinimo);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    libros.add(mapearFila(rs));
                }
            }

        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return libros;
    }

    
}
