/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/package-info.java to edit this template
 */
/**
 * Punto de entrada de la aplicacion de gestion de libros.
 * <p>
 * Contiene la clase {@link trabajo1.main.Main} con el metodo {@code main()} y
 * toda la interaccion con el usuario por consola. No sabe nada de SQL ni de
 * ficheros: solo usa la interfaz {@link trabajo1.utilidades.LibroRepository}.
 *
 * <h2>Funcionamiento</h2>
 * <ol>
 * <li>Al arrancar pregunta con que repositorio se quiere trabajar:
 * <ul>
 * <li>1: {@link trabajo1.utilidades.LibroRepositoryMySQL} (base de datos)</li>
 * <li>2: {@link trabajo1.utilidades.LibroRepositoryArchivo} (archivo
 * {@code libros.txt})</li>
 * </ul>
 * Cualquier otra opcion termina el programa.</li>
 * <li>El repositorio elegido se guarda en una variable de tipo interfaz
 * ({@code LibroRepository<ModeloLibro>}), asi el resto del codigo funciona
 * igual con cualquiera de los dos (polimorfismo).</li>
 * <li>Muestra el menu principal en un bucle {@code do-while} hasta que se
 * elige 0:
 * <ul>
 * <li>1. Mostrar todos los libros: {@code caso1}</li>
 * <li>2. Buscar libro por titulo: {@code caso2}</li>
 * <li>3. Buscar libro por autor: {@code caso3}</li>
 * <li>4. Buscar libro por rango de precio: {@code caso4}</li>
 * <li>5. Buscar libro por cantidad de stock: {@code caso5}</li>
 * <li>6. Insertar libro: {@code caso6}</li>
 * <li>7. Eliminar libro por titulo: {@code caso7}</li>
 * <li>8. Hacer copia de los datos del repositorio: {@code caso8}</li>
 * <li>0. Salir</li>
 * </ul>
 * </li>
 * <li>Cada opcion tiene su propio metodo estatico ({@code caso1} a
 * {@code caso8}) que pide los datos por teclado, llama al repositorio y
 * muestra el resultado.</li>
 * </ol>
 *
 * <h2>Detalles a tener en cuenta</h2>
 * <ul>
 * <li>Despues de cada {@code nextInt()} o {@code nextDouble()} se llama a
 * {@code nextLine()} para limpiar el salto de linea pendiente.</li>
 * <li>En el caso 4, si el precio minimo es mayor que el maximo, se
 * intercambian.</li>
 * <li>La opcion 8 copia en un sentido u otro segun el repositorio activo:
 * MySQL lee {@code libros.txt} y lo vuelca en la base de datos; Archivo lee la
 * base de datos y la vuelca en {@code libros.txt}.</li>
 * </ul>
 */
package trabajo1.main;
