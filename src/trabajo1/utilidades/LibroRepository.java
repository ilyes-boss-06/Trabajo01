/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package trabajo1.utilidades;

import java.util.List;

/**
 *
 * @author 2DAM
 */
public interface LibroRepository<T> {
    List<T> mostrarLibros();
    T obtenerPorTitulo(int titulo);
    public void buscarPorRango();
    public void buscarPorCantidadStock();
    boolean insertar(T objeto);
    T eliminarPorTitulo(int titulo);
    public void CopiarArchivos();
    
}
