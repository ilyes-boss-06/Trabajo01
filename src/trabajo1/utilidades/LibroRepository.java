/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trabajo1.utilidades;

import java.util.List;

/**
 * Interfaz que tiene las operaciones básicas que se pueden realizar
 * sobre alguno de los repositorios de libros.
 *
 * @param <T> tipo de objeto que manejará el repositorio.
 * @author Juan David
 */
public interface LibroRepository<T> {
    
    /**
     * Obtiene todos los libros almacenados en el repositorio.
     *
     * @return lista con todos los libros.
     */
    List<T> mostrarLibros();
    
    /**
     * Busca un libro utilizando su título.
     *
     * @param titulo título del libro que se busca.
     * @return libro que coincide con el título que se busco.
     */
    T obtenerPorTitulo(String titulo);
    
    /**
     * Busca los libros que pertenecen a un determinado autor.
     *
     * @param autor nombre del autor que se busca.
     * @return lista de libros escritos por el autor que se busco.
     */
    List<T> buscarPorAutor(String autor);
    
    /**
     * Busca los libros cuyo precio se encuentra dentro de un rango determinado.
     *
     * @param precioMin precio mínimo del rango de búsqueda.
     * @param precioMax precio máximo del rango de búsqueda.
     * @return lista de libros cuyo precio está dentro del rango indicado.
     */
    List<T> buscarPorRango(double precioMin, double precioMax);
    
    /**
     * Busca los libros según la cantidad disponible en stock.
     *
     * @param stock cantidad de unidades en stock que se desea buscar.
     * @return lista de libros que coinciden con la cantidad de stock indicada.
     */
    List<T> buscarPorCantidadStock(int stock);
    
    /**
     * Inserta un nuevo objeto en el repositorio.
     *
     * @param objeto objeto que se desea insertar.
     * @return true si el objeto se ha insertado correctamente,
     *         false en caso contrario.
     */
    boolean insertar(T objeto);
    
    /**
     * Elimina un libro del repositorio utilizando su título.
     * Si el el titulo esta duplicado, pregunta por la id del libro.
     *
     * @param titulo título del libro que se desea eliminar.
     * @return libro que ha sido eliminado.
     */
    T eliminarPorTitulo(String titulo);
    
    /**
     * Copia los archivos utilizados por el repositorio.
     */
    public void CopiarArchivos();
    
}
