/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo1.main;

import java.util.Locale;
import java.util.Scanner;
import trabajo1.utilidades.LibroRepositoryMySQL;
import trabajo1.utilidades.ModeloLibro;

/**
 *
 * @author BOSS
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        LibroRepositoryMySQL libroRepo = new LibroRepositoryMySQL();
        
        System.out.println("""
                                     **MENU** 
                           1. Mostrar todos los libros 
                           2. Buscar libro por titulo 
                           3. Buscar libro por autor 
                           4. Buscar libro por rango de precio 
                           5. Buscar libro por cantidad de stock 
                           6. Insertar libro 
                           7. Eliminar libro por titulo 
                           8. Hacer copia de los datos del repositorio 
                           0. Salir"""); 
        
         Scanner sc;
        sc = new Scanner(System.in).useLocale(Locale.US);
        
        switch (sc.nextInt()) {
            case 1 -> { 
                for (ModeloLibro libro : libroRepo.mostrarLibros()) {
                    System.out.println(libro);
                   }
            }
            case 2 -> {
                
            }
            case 3 -> {
                
            }
            case 4 -> {
                
            }
            case 5 -> {
                
            }
            case 6 -> {
                
            }
            case 7 -> {
                
            }
            case 8 -> {
                
            }
            case 0 -> {
                
            }
            default -> throw new AssertionError();
        }
        
        
   sc.close();
    }
}
