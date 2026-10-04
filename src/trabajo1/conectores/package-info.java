/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/package-info.java to edit this template
 */
/**
 * Conexion con la base de datos MySQL.
 * <p>
 * Contiene la clase {@link trabajo1.conectores.ConexionesDB}, que se encarga
 * de abrir conexiones JDBC. Es la unica clase que conoce los datos de
 * conexion.
 *
 * <h2>Funcionamiento</h2>
 * <ul>
 * <li>Al cargarse la clase, {@code Dotenv.load()} lee el fichero {@code .env}
 * (libreria dotenv-java) una sola vez.</li>
 * <li>Se guardan tres constantes estaticas con las claves del {@code .env}:
 * <ul>
 * <li>{@code db.url}: URL JDBC de la base de datos</li>
 * <li>{@code db.user}: usuario</li>
 * <li>{@code db.password}: contrasena</li>
 * </ul>
 * Asi las credenciales no estan escritas en el codigo fuente.</li>
 * <li>{@code getConnection()} abre y devuelve una conexion nueva con
 * {@code DriverManager}. Si hay un {@code SQLException} muestra el error y
 * devuelve {@code null}.</li>
 * </ul>
 *
 * <h2>Uso</h2>
 * Los metodos de {@link trabajo1.utilidades.LibroRepositoryMySQL} llaman a
 * {@code ConexionesDB.getConnection()} dentro de un {@code try-with-resources},
 * de modo que cada operacion abre su conexion y se cierra sola al terminar.
 *
 * <h2>Detalles a tener en cuenta</h2>
 * <ul>
 * <li>El fichero {@code .env} debe existir con esas tres claves; si no, la
 * clase falla al cargarse.</li>
 * <li>Si {@code getConnection()} devuelve {@code null}, el codigo que la use
 * lanzara un {@code NullPointerException} al intentar crear el
 * {@code PreparedStatement}.</li>
 * <li>El {@code .env} contiene credenciales: no conviene subirlo a un
 * repositorio ni compartirlo.</li>
 * </ul>
 */
package trabajo1.conectores;
