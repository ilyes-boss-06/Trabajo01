/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/package-info.java to edit this template
 */
/**
 * Modelo de datos y acceso a datos de la aplicacion (patron Repository).
 *
 * <h2>Clases</h2>
 * <ol>
 * <li>{@link trabajo1.utilidades.ModeloLibro}: POJO que representa un libro
 * con {@code id} (String), {@code titulo}, {@code autor}, {@code precio}
 * (double) y {@code stock} (int). Tiene constructor vacio, constructor
 * completo, getters, setters y {@code toString()}.</li>
 * <li>{@link trabajo1.utilidades.LibroRepository}: interfaz generica que define
 * el contrato de cualquier repositorio: {@code mostrarLibros()},
 * {@code obtenerPorTitulo()}, {@code buscarPorAutor()},
 * {@code buscarPorRango()}, {@code buscarPorCantidadStock()},
 * {@code insertar()}, {@code eliminarPorTitulo()} y
 * {@code CopiarArchivos()}. {@code Main} solo depende de esta interfaz, no de
 * las implementaciones.</li>
 * <li>{@link trabajo1.utilidades.LibroRepositoryArchivo}: implementacion que
 * guarda los libros en el archivo {@code libros.txt}, una linea por libro con
 * el formato {@code id^titulo^autor^precio^stock}.
 * <ul>
 * <li>Para cada consulta lee todo el archivo y filtra la lista en
 * memoria.</li>
 * <li>{@code insertar()} comprueba que el id no exista y anade la linea al
 * final.</li>
 * <li>{@code eliminarPorTitulo()} quita el libro de la lista y reescribe el
 * archivo.</li>
 * <li>{@code CopiarArchivos()} vuelca la base de datos MySQL en
 * {@code libros.txt}.</li>
 * </ul>
 * </li>
 * <li>{@link trabajo1.utilidades.LibroRepositoryMySQL}: implementacion que usa
 * la tabla {@code libros} ({@code id, titulo, autor, precio, stock}) con
 * JDBC.
 * <ul>
 * <li>Todas las consultas usan {@code PreparedStatement} y
 * {@code try-with-resources}.</li>
 * <li>{@code eliminarPorTitulo()}: si no hay coincidencias avisa; si hay una
 * la borra; si hay varias las lista y pide el ID a eliminar.</li>
 * <li>{@code CopiarArchivos()} lee {@code libros.txt} y reemplaza el contenido
 * de la tabla.</li>
 * <li>{@code buscarPorRango()} usa {@code BETWEEN}, que incluye ambos
 * extremos.</li>
 * </ul>
 * </li>
 * </ol>
 *
 * <h2>Diferencias entre los dos repositorios</h2>
 * <ul>
 * <li>Busqueda por titulo y autor: el de archivo ignora mayusculas y
 * minusculas; el de MySQL usa coincidencia exacta (depende de la collation de
 * la base de datos).</li>
 * <li>{@code eliminarPorTitulo()}: el de archivo borra el primero que
 * coincide; el de MySQL gestiona el caso de varios libros con el mismo
 * titulo.</li>
 * <li>Los dos se copian entre si con la opcion 8 del menu.</li>
 * </ul>
 */
package trabajo1.utilidades;
