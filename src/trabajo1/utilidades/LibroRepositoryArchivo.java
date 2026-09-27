/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo1.utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio de libros guardado en un archivo de texto.
 * Cada linea del archivo es un libro con el formato: id;titulo;autor;precio;stock
 *
 * @author BOSS
 */
public class LibroRepositoryArchivo implements LibroRepository<ModeloLibro> {

    private static final String archivo = "libros.txt";
    private static final String separador = "^";

    @Override
    public List<ModeloLibro> mostrarLibros() {
        List<ModeloLibro> libros = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(archivo));
            String linea = br.readLine();
            while (linea != null) {
                if (!linea.trim().equals("")) {
                    libros.add(mapearLinea(linea));
                }
                linea = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return libros;
    }

    @Override
    public ModeloLibro obtenerPorTitulo(String titulo) {
        List<ModeloLibro> libros = mostrarLibros();

        for (int i = 0; i < libros.size(); i++) {
            ModeloLibro libro = libros.get(i);
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                return libro;
            }
        }
        return null;
    }
    
    @Override
    public List<ModeloLibro> buscarPorAutor(String autor) {
        List<ModeloLibro> libros = mostrarLibros();
        List<ModeloLibro> resultado = new ArrayList<>();

        for (int i = 0; i < libros.size(); i++) {
            ModeloLibro libro = libros.get(i);
            if (libro.getAutor().equalsIgnoreCase(autor)) {
                resultado.add(libro);
            }
        }
        return resultado;
    }
    
    @Override
    public List<ModeloLibro> buscarPorRango(double precioMin, double precioMax) {
        List<ModeloLibro> libros = mostrarLibros();
        List<ModeloLibro> resultado = new ArrayList<>();

        for (int i = 0; i < libros.size(); i++) {
            ModeloLibro libro = libros.get(i);
            if (libro.getPrecio() >= precioMin && libro.getPrecio() <= precioMax) {
                resultado.add(libro);
            }
        }
        return resultado;
    }

    @Override
    public List<ModeloLibro> buscarPorCantidadStock(int stockMinimo) {
        List<ModeloLibro> libros = mostrarLibros();
        List<ModeloLibro> resultado = new ArrayList<>();

        for (int i = 0; i < libros.size(); i++) {
            ModeloLibro libro = libros.get(i);
            if (libro.getStock() >= stockMinimo) {
                resultado.add(libro);
            }
        }
        return resultado;
    }

    @Override
    public boolean insertar(ModeloLibro libro) {
        List<ModeloLibro> libros = mostrarLibros();
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId().equals(libro.getId())) {
                System.out.println("Ya existe un libro con el id " + libro.getId());
                return false;
            }
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo, true));
            bw.write(libroALinea(libro));
            bw.newLine();
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    @Override
    public ModeloLibro eliminarPorTitulo(String titulo) {
        List<ModeloLibro> libros = mostrarLibros();

        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equals(titulo)) {
                ModeloLibro eliminado = libros.remove(i);
                if (guardarLibros(libros)) {
                    return eliminado;
                }
                return null;
            }
        }
        return null;
    }

    /**
     * Copia todos los libros de la base de datos al archivo de texto,
     * sobrescribiendo lo que hubiera antes.
     */
    @Override
    public void CopiarArchivos() {
        LibroRepositoryMySQL repoMySQL = new LibroRepositoryMySQL();
        List<ModeloLibro> libros = repoMySQL.mostrarLibros();
        BufferedWriter bw = null;

        try {
            bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i < libros.size(); i++) {
                ModeloLibro libro = libros.get(i);
                String linea = libro.getId() + separador
                        + libro.getTitulo() + separador
                        + libro.getAutor() + separador
                        + libro.getPrecio() + separador
                        + libro.getStock();
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Se han copiado " + libros.size() + " libros a " + archivo);
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de libros: " + e.getMessage());
        } finally {
            try {
                if (bw != null) {
                    bw.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo de libros: " + e.getMessage());
            }
        }
    }

    /**
     * Sobrescribe el archivo con la lista de libros recibida.
     */
    private boolean guardarLibros(List<ModeloLibro> libros) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo));
            for (int i = 0; i < libros.size(); i++) {
                bw.write(libroALinea(libros.get(i)));
                bw.newLine();
            }
            bw.close();
            return true;
        } catch (Exception e) {
            System.out.println("Error: " + e);
            return false;
        }
    }

    private String libroALinea(ModeloLibro libro) {
        return libro.getId() + separador
                + libro.getTitulo() + separador
                + libro.getAutor() + separador
                + libro.getPrecio() + separador
                + libro.getStock();
    }

     private ModeloLibro mapearLinea(String linea) {

        String[] campos = linea.split("\\" + separador);
        ModeloLibro libro = new ModeloLibro();
        libro.setId(campos[0]);
        libro.setTitulo(campos[1]);
        libro.setAutor(campos[2]);
        libro.setPrecio(Double.parseDouble(campos[3]));
        libro.setStock(Integer.parseInt(campos[4]));
        return libro;
    }

}
