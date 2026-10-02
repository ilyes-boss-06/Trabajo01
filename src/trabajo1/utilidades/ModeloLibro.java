/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo1.utilidades;

/**
 * Clase que representa el modelo de un libro que se puede
 * almacenar y gestionar en la base de datos.
 *
 * @author Juan David
 */
public class ModeloLibro{
    
    /**
     * Identificador único del libro.
     */
    String id;
    
    /**
     * Título del libro.
     */
    String titulo;

    /**
     * Autor del libro.
     */
    String autor;

    /**
     * Precio del libro.
     */
    double precio;
    /**
     * Cantidad de unidades disponibles del libro.
     */
    int stock;
    
    /**
     * Constructor vacío de la clase ModeloLibro.
     *
     * Permite crear el objeto sin establecer sus atributos
     * inicialmente.
     */
    public ModeloLibro() {
    }


    /**
     * Constructor de la clase ModeloLibro.
     *
     * Permite crear un libro estableciendo todos sus atributos.
     *
     * @param id identificador del libro
     * @param titulo título del libro
     * @param autor autor del libro
     * @param precio precio del libro
     * @param stock cantidad de unidades disponibles
     */
    public ModeloLibro(String id, String titulo, String autor, double precio, int stock) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.precio = precio;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public double getPrecio() {
        return precio;
    }

    public int getStock() {
        return stock;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "ModeloLibro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + ", stock=" + stock + '}';
    }

    
}
