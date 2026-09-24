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

    private static final String ARCHIVO = "libros.txt";
    private static final String SEPARADOR = ";";

    @Override
    public List<ModeloLibro> mostrarLibros() {
        List<ModeloLibro> libros = new ArrayList<>();
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(ARCHIVO));
            String linea = br.readLine();
            while (linea != null) {
                if (!linea.trim().equals("")) {
                    libros.add(mapearLinea(linea));
                }
                linea = br.readLine();
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de libros: " + e.getMessage());
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException e) {
                System.err.println("Error al cerrar el archivo de libros: " + e.getMessage());
            }
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
    public ModeloLibro buscarPorRango(int rango) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ModeloLibro buscarPorCantidadStock(int stock) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean insertar(ModeloLibro objeto) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ModeloLibro eliminarPorTitulo(String titulo) {
        throw new UnsupportedOperationException("Not supported yet.");
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
            bw = new BufferedWriter(new FileWriter(ARCHIVO));
            for (int i = 0; i < libros.size(); i++) {
                ModeloLibro libro = libros.get(i);
                String linea = libro.getId() + SEPARADOR
                        + libro.getTitulo() + SEPARADOR
                        + libro.getAutor() + SEPARADOR
                        + libro.getPrecio() + SEPARADOR
                        + libro.getStock();
                bw.write(linea);
                bw.newLine();
            }
            System.out.println("Se han copiado " + libros.size() + " libros a " + ARCHIVO);
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

    private ModeloLibro mapearLinea(String linea) {
        String[] campos = linea.split(SEPARADOR);
        ModeloLibro libro = new ModeloLibro();
        libro.setId(campos[0]);
        libro.setTitulo(campos[1]);
        libro.setAutor(campos[2]);
        libro.setPrecio(Double.parseDouble(campos[3]));
        libro.setStock(Integer.parseInt(campos[4]));
        return libro;
    }

}
