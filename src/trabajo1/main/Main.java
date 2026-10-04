/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package trabajo1.main;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import trabajo1.utilidades.LibroRepository;
import trabajo1.utilidades.LibroRepositoryArchivo;
import trabajo1.utilidades.LibroRepositoryMySQL;
import trabajo1.utilidades.ModeloLibro;

/** 
 * @author Ilyes Ben Jaber
 * 
 * Clase principal de la aplicacion de gestion de libros.
 * Muestra los menus por consola y delega el trabajo en el repositorio
 * elegido por el usuario (base de datos MySQL o archivo de texto).
 */
public class Main {

    /**
     * Metodo de entrada del programa. Pregunta con que repositorio se quiere
     * trabajar y muestra el menu principal en bucle hasta que se elige salir.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // Abrimos un Scanner y lo llamamos sc.
        Scanner sc;
        sc = new Scanner(System.in).useLocale(Locale.US);

        // Tipo interfaz: puede contener cualquiera de los dos repositorios (patron repository)
        
        LibroRepository<ModeloLibro> repo;

        // Menu para seleccionar repositorio con el que se desea trabajar.
        
        System.out.println("""
                           Con que repositorio quieres trabajar?
                           1. Base de datos MySQL
                           2. Archivo de texto""");
        System.out.print("Opcion: ");
        int tipo = sc.nextInt();
        // limpia el salto de linea que deja nextInt()
        sc.nextLine();

        if (tipo == 1) {
            repo = new LibroRepositoryMySQL();
            System.out.println("Usando la base de datos MySQL.");
        } else if (tipo == 2) {
            repo = new LibroRepositoryArchivo();
            System.out.println("Usando el archivo de texto.");
        } else {
            System.out.println("Opcion no valida.");
            sc.close();
            return;
        }
        // Abrimos el menu de Trabajo, si el usuario pone 0 sale.
        int opcion;
        do {
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
            System.out.print("Opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            // Refactorice el codigo, todos los casos estan abajo.
            switch (opcion) {
                case 1 -> {
                    caso1(repo);
                }
                case 2 -> {
                    caso2(sc, repo);
                }
                case 3 -> {
                    caso3(sc, repo);
                }
                case 4 -> {
                    caso4(sc, repo);
                }
                case 5 -> {
                    caso5(sc, repo);
                }
                case 6 -> {
                    caso6(sc, repo);
                }
                case 7 -> {
                    caso7(sc, repo);
                }
                case 8 -> {
                    caso8(repo);
                }
                // Con el 0 sales del bucle.
                case 0 -> {
                    System.out.println("Saliendo...");
                }
                // Default, significa que no es ninguna de las opciones que le ofrezco.
                default -> System.out.println("Opcion no valida.");
            }
        } while (opcion != 0);

        sc.close();
    }
    /**
     * Opcion 8: hace una copia de los datos. El sentido depende del
     * repositorio: el de archivo copia desde MySQL al archivo y el de
     * MySQL copia desde el archivo a la base de datos.
     *
     * @param repo 
     */
    public static void caso8(LibroRepository<ModeloLibro> repo) {
        repo.CopiarArchivos();
    }

    /**
     * Opcion 7: pide un titulo y elimina el libro correspondiente.
     * 
     * @param sc
     * @param repo 
     */
    public static void caso7(Scanner sc, LibroRepository<ModeloLibro> repo) {
        System.out.print("Titulo del libro a eliminar: ");
        String titulo = sc.nextLine();
        
        ModeloLibro eliminado = repo.eliminarPorTitulo(titulo);
        if (eliminado != null) {
            System.out.println("Libro eliminado: " + eliminado);
        } else {
            System.out.println("No se ha encontrado ningun libro con ese titulo.");
        }
    }

    /**
     * Opcion 6: pide todos los datos de un libro nuevo y lo inserta.
     * 
     * @param sc
     * @param repo 
     */
    public static void caso6(Scanner sc, LibroRepository<ModeloLibro> repo) {
        System.out.print("Id: ");
        String id = sc.nextLine();
        System.out.print("Titulo: ");
        String titulo = sc.nextLine();
        System.out.print("Autor: ");
        String autor = sc.nextLine();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        sc.nextLine();
        System.out.print("Stock: ");
        int stock = sc.nextInt();
        sc.nextLine();
        
        ModeloLibro libro = new ModeloLibro(id, titulo, autor, precio, stock);
        if (repo.insertar(libro)) {
            System.out.println("Libro insertado correctamente.");
        } else {
            System.out.println("No se ha podido insertar el libro.");
        }
    }

    /**
     * Opcion 5: muestra los libros con stock igual o superior al indicado.
     * 
     * @param sc
     * @param repo 
     */
    public static void caso5(Scanner sc, LibroRepository<ModeloLibro> repo) {
        System.out.print("Stock minimo: ");
        int stock = sc.nextInt();
        sc.nextLine();
        
        List<ModeloLibro> libros = repo.buscarPorCantidadStock(stock);
        if (libros.isEmpty()) {
            System.out.println("No hay libros con ese stock o mas.");
        } else {
            for (ModeloLibro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    /**
     * Opcion 4: muestra los libros cuyo precio esta dentro de un rango.
     * Si el usuario introduce el minimo mayor que el maximo, se intercambian.
     * 
     * @param sc
     * @param repo 
     */
    public static void caso4(Scanner sc, LibroRepository<ModeloLibro> repo) {
        System.out.print("Precio minimo: ");
        double precioMin = sc.nextDouble();
        sc.nextLine();
        System.out.print("Precio maximo: ");
        double precioMax = sc.nextDouble();
        sc.nextLine();
        
        if (precioMin > precioMax) {
            double aux = precioMin;
            precioMin = precioMax;
            precioMax = aux;
        }
        
        List<ModeloLibro> libros = repo.buscarPorRango(precioMin, precioMax);
        if (libros.isEmpty()) {
            System.out.println("No hay libros.");
        } else {
            for (int i = 0; i < libros.size(); i++) {
                System.out.println(libros.get(i));
            }
        }
    }

    /**
     * Opcion 3: muestra todos los libros de un autor.
     * 
     * @param sc
     * @param repo 
     */
    public static void caso3(Scanner sc, LibroRepository<ModeloLibro> repo) {
        System.out.print("Introduce el autor: ");
        String autor = sc.nextLine();
        
        List<ModeloLibro> libros = repo.buscarPorAutor(autor);
        if (libros.isEmpty()) {
            System.out.println("No hay libros de ese autor.");
        } else {
            for (ModeloLibro libro : libros) {
                System.out.println(libro);
            }
        }
    }

    /**
     * Opcion 2: busca un unico libro por su titulo y lo muestra.
     * 
     * @param sc
     * @param repo 
     */
    public static void caso2(Scanner sc, LibroRepository<ModeloLibro> repo) {
        System.out.print("Introduce el titulo: ");
        String titulo = sc.nextLine();
        
        ModeloLibro libro = repo.obtenerPorTitulo(titulo);
        if (libro != null) {
            System.out.println(libro);
        } else {
            System.out.println("No se ha encontrado ningun libro con ese titulo.");
        }
    }

    /**
     * Opcion 1: muestra todos los libros del repositorio.
     * 
     * @param repo 
     */
    public static void caso1(LibroRepository<ModeloLibro> repo) {
        List<ModeloLibro> libros = repo.mostrarLibros();
        if (libros.isEmpty()) {
            System.out.println("No hay libros.");
        } else {
            for (ModeloLibro libro : libros) {
                System.out.println(libro);
            }
        }
    }
}
