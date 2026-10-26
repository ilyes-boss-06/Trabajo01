# Sistema de Gestión de Biblioteca

Aplicación de consola en Java para gestionar un catálogo de libros (mostrar, buscar por título, autor, precio o stock, insertar, eliminar y copiar datos). Funciona con dos repositorios a elegir al arrancar: una base de datos **MySQL** o un **archivo de texto** (`libros.txt`).

## Requisitos

| Software | Versión probada |
|---|---|
| Windows | 10 (10.0.19045.3803), en máquina virtual VirtualBox |
| Oracle JDK | 21.0.12.1 |
| Apache NetBeans | 31 |
| MySQL Community Server | 8.4.2 (requiere Visual C++ Redistributable x64) |
| Git | 2.56.0 |

## Instalación rápida

1. Clonar: `git clone https://github.com/ilyes-boss-06/Trabajo01.git`
2. Abrir la carpeta en NetBeans (**File → Open Project**, proyecto Ant). Las librerías están en `lib/`.
3. Crear la base de datos: abrir `cmd` en la carpeta `sql`, ejecutar `mysql -u root -p` y después `SOURCE init.sql;` (o abrir `sql/init.sql` en MySQL Workbench y ejecutarlo).
4. Configurar: copiar `.env.example`, pegarlo como `.env` en la raíz del proyecto y editar `db.user` y `db.password`.
5. **Clean and Build** y **Run**.

## Configuración (`.env`)

| Variable | Descripción | Ejemplo |
|---|---|---|
| `db.url` | URL JDBC de la base de datos | `jdbc:mysql://host:puerto/libreria` |
| `db.user` | Usuario de MySQL | `root` |
| `db.password` | Contraseña de ese usuario | `tu_contraseña` |

El `.env` debe estar en la carpeta desde la que se lanza la aplicación (la raíz del proyecto). No se sube a GitHub.

## Documentación

- [Manual de despliegue completo (PDF)](./manual_despliegue.pdf)
- [JavaDoc](../dist/javadoc/index.html)

## Autores

Ilyes Ben Jaber, Juan David Pinzon
