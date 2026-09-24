/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo1.main;

import java.util.Scanner;

/**
 *
 * @author BOSS
 */
public class Main {
    
    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        System.out.println("          **MENU** \n"
                + "1. Mostrar todos los libros \n"
                + "2. Buscar libro por titulo \n"
                + "3. Buscar libro por autor \n"
                + "4. Buscar libro por rango de precio \n"
                + "5. Buscar libro por cantidad de stock \n"
                + "6. Insertar libro \n"
                + "7. Eliminar libro por titulo \n"
                + "8. Hacer copia de los datos del repositorio \n"
                + "0. Salir" ); 
        
         Scanner sc = new Scanner(System.in);
        
        switch (sc.nextInt()) {
            case 1: 
                
                break;
            case 2: 
                
                break;
            case 3: 
                
                break;
            case 4: 
                
                break;
            case 5: 
                
                break;
            case 6: 
                
                break;
            case 7: 
                
                break;
            case 8: 
                
                break;
            case 0:
                break;  
            default:
                throw new AssertionError();
        }
        
        
   sc.close();
    }
}
