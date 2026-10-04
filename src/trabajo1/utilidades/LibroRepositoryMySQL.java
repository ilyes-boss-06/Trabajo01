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

    
    /**
     * Convierte una fila obtenida de la base de datos en un objeto ModeloLibro.
     *
     * @param rs resultado de la consulta SQL que contiene los datos del libro.
     * @return objeto ModeloLibro creado a partir de los datos de la fila.
     * @throws SQLException si se produce un error al obtener los datos del resultado.
     */
    private ModeloLibro mapearFila(ResultSet rs) throws SQLException {
		ModeloLibro libro = new ModeloLibro();
		libro.setId(rs.getString("id"));
		libro.setTitulo(rs.getString("titulo"));
		libro.setAutor(rs.getString("autor"));
		libro.setPrecio(rs.getDouble("precio"));
		libro.setStock(rs.getInt("stock"));
		return libro;

    /**
     * Inserta un nuevo libro en la base de datos.
     *
     * @param libro libro que se desea insertar.
     * @return true si el libro se ha insertado correctamente,
     *         false si se produce algún error.
     */	}
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


/**
     * Elimina un libro utilizando su título.
     *
     * Si existe más de un libro con el mismo título, muestra los libros
     * encontrados y solicita al usuario el ID del libro que desea eliminar.
     *
     * @param titulo título del libro que se desea eliminar.
     * @return libro eliminado si solamente existe uno con ese título;
     *         null si no existe o si se selecciona mediante ID.
     */
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

/**
     * Elimina un libro de la base de datos utilizando su ID.
     *
     * @param id identificador del libro que se desea eliminar.
     * @return true si el libro se ha eliminado correctamente,
     *         false si no se ha podido eliminar.
     */
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

    /**
     * Lee los libros almacenados en el archivo "libros.txt" y los copia
     * a la base de datos MySQL.
     *
     * El archivo debe contener los datos de cada libro separados mediante
     * el carácter "^".
     */
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

    /**
     * Busca todos los libros escritos por un determinado autor.
     *
     * @param autor nombre del autor que se desea buscar.
     * @return lista de libros pertenecientes al autor indicado.
     */
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
     /**
     * Busca los libros cuyo precio se encuentra dentro de un rango determinado.
     *
     * @param precioMin precio mínimo del rango de búsqueda.
     * @param precioMax precio máximo del rango de búsqueda.
     * @return lista de libros cuyo precio está entre el mínimo y el máximo indicados.
     */
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
    /**
     * Busca los libros que tienen una cantidad de stock igual o superior
     * al valor indicado.
     *
     * @param stockMinimo cantidad mínima de stock que debe tener el libro.
     * @return lista de libros que cumplen con la cantidad mínima de stock.
     */
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
