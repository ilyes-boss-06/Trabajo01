/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trabajo1.utilidades;

import java.util.List;

/**
 *
 * @author 2DAM
 * @param <T>
 */
public interface LibroRepository<T> {
    List<T> mostrarLibros();
    T obtenerPorTitulo(String titulo);
    T buscarPorRango(int rango);
    T buscarPorCantidadStock(int stock);
    boolean insertar(T objeto);
    T eliminarPorTitulo(String titulo);
    public void CopiarArchivos();
    
}
