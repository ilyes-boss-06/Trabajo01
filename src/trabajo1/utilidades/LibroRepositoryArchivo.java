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

        try (BufferedReader br = new BufferedReader(new FileReader(ARCHIVO))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.isBlank()) {
                    libros.add(mapearLinea(linea));
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de libros: " + e.getMessage());
        }
        return libros;
    }

    @Override
    public ModeloLibro obtenerPorTitulo(int titulo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void buscarPorRango() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void buscarPorCantidadStock() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public boolean insertar(ModeloLibro objeto) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ModeloLibro eliminarPorTitulo(int titulo) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     * Copia todos los libros de la base de datos al archivo de texto,
     * sobrescribiendo lo que hubiera antes.
     */
    @Override
    public void CopiarArchivos() {
        List<ModeloLibro> libros = new LibroRepositoryMySQL().mostrarLibros();
        guardarLibros(libros);
        System.out.println("Se han copiado " + libros.size() + " libros a " + ARCHIVO);
    }

    private void guardarLibros(List<ModeloLibro> libros) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (ModeloLibro libro : libros) {
                bw.write(libroALinea(libro));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al escribir el archivo de libros: " + e.getMessage());
        }
    }

    private String libroALinea(ModeloLibro libro) {
        return libro.getId() + SEPARADOR
                + libro.getTitulo() + SEPARADOR
                + libro.getAutor() + SEPARADOR
                + libro.getPrecio() + SEPARADOR
                + libro.getStock();
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
