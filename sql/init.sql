CREATE DATABASE IF NOT EXISTS libreria CHARACTER SET utf8mb4;
USE libreria;

DROP TABLE IF EXISTS libros;
CREATE TABLE libros (
    id     VARCHAR(20)  PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    autor  VARCHAR(100) NOT NULL,
    precio DECIMAL(6,2) NOT NULL CHECK (precio >= 0),
    stock  INT          NOT NULL CHECK (stock >= 0)
) CHARACTER SET utf8mb4;

INSERT INTO libros (id, titulo, autor, precio, stock) VALUES
    ('L001', 'La torre de los vientos', 'Hermenegildo', 14.90, 12),
    ('L002', 'Los últimos faros', 'Carolina', 12.75, 7),
    ('L003', 'El relojero de Nube Alta', 'Helena', 16.40, 6),
    ('L004', 'El jardín de ceniza', 'Hermenegildo', 18.50, 12),
    ('L005', 'Sombras en el archivo', 'Parros', 24.90, 7),
    ('L006', 'Mapa de las islas dormidas', 'Carolina', 19.95, 5),
    ('L007', 'Cuaderno de aguas perdidas', 'Ignacio', 9.90, 0),
    ('L008', 'La ciudad que olvidó su nombre', 'José', 27.50, 7);
