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
    
    /**
     * Obtiene el identificador del libro.
     *
     * @return identificador del libro
     */
    public String getId() {
        return id;
    }
    
    /**
     * Obtiene el título del libro.
     *
     * @return título del libro
     */
    public String getTitulo() {
        return titulo;
    }
    
    /**
     * Obtiene el autor del libro.
     *
     * @return autor del libro
     */
    public String getAutor() {
        return autor;
    }
    
    /**
     * Obtiene el precio del libro.
     *
     * @return precio del libro
     */
    public double getPrecio() {
        return precio;
    }
    
    /**
     * Obtiene la cantidad de unidades disponibles.
     *
     * @return cantidad de unidades disponibles
     */
    public int getStock() {
        return stock;
    }
    
    /**
     * Modifica el identificador del libro.
     *
     * @param id nuevo identificador del libro
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Modifica el título del libro.
     *
     * @param titulo nuevo título del libro
     */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    /**
     * Modifica el autor del libro.
     *
     * @param autor nuevo autor del libro
     */
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    /**
     * Modifica el precio del libro.
     *
     * @param precio nuevo precio del libro
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    
    /**
     * Modifica la cantidad de unidades disponibles.
     *
     * @param stock nueva cantidad de unidades disponibles
     */
    public void setStock(int stock) {
        this.stock = stock;
    }
    
    /**
     * Devuelve una representación en forma de texto del libro,
     * mostrando todos sus atributos.
     *
     * @return cadena de texto con los datos del libro
     */
    @Override
    public String toString() {
        return "ModeloLibro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + ", precio=" + precio + ", stock=" + stock + '}';
    }

    
}
