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
    T obtenerPorTitulo(String titulo);
    List<T> buscarPorAutor(String autor);
    List<T> buscarPorRango(double precioMin, double precioMax);
    List<T> buscarPorCantidadStock(int stock);
    boolean insertar(T objeto);
    T eliminarPorTitulo(String titulo);
    public void CopiarArchivos();
    
}
