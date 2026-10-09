USE libreria;

-- Datos ficticios para el caso de estudio.
-- Ejecutar después de schema.sql, que crea las tablas vacías.

-- 1. Tiendas
INSERT INTO Tienda (id_tienda, nombre, direccion, telefono, ciudad) VALUES
(1, 'Centro', 'Calle Mayor 12', '976100101', 'Villa Serena'),
(2, 'Ribera', 'Avenida del Río 8', '976100202', 'Aldeaverde'),
(3, 'Universidad', 'Calle Campus 4', '976100303', 'Villa Serena');

-- 2. Editoriales
INSERT INTO Editorial (id_editorial, nombre, pais, telefono) VALUES
(1, 'Letras del Sur', 'España', '910100101'),
(2, 'Horizonte Editorial', 'Argentina', '910100202'),
(3, 'Nube de Papel', 'España', '910100303');

-- 3. Autores
INSERT INTO Autor (id_autor, nombre, nacionalidad, anio_nacimiento) VALUES
(1, 'Gabriel García Márquez', 'Colombiana', 1927),
(2, 'Julio Cortázar', 'Argentina', 1914),
(3, 'Ana Torres', 'Española', 1982),
(4, 'Luis Vega', 'Española', 1978);

-- 4. Libros
INSERT INTO Libro (isbn, titulo, anio_publicacion, numero_paginas, precio_catalogo, id_editorial) VALUES
('9780000000001', 'Cien años de soledad', 1967, 471, 22.00, 1),
('9780000000002', 'Rayuela', 1963, 600, 18.50, 2),
('9780000000003', 'Historias de cronopios', 1962, 160, 12.90, 2),
('9780000000004', 'La ciudad invisible', 2021, 280, 25.00, 3),
('9780000000005', 'El jardín de papel', 2019, 210, 16.00, 1);

-- 5. Relación entre libros y autores
INSERT INTO Escribe (isbn, id_autor, tipo_autoria) VALUES
('9780000000001', 1, 'principal'),
('9780000000002', 2, 'principal'),
('9780000000003', 2, 'principal'),
('9780000000004', 3, 'principal'),
('9780000000004', 4, 'colaborador'),
('9780000000005', 3, 'principal');

-- 6. Existencias por tienda y libro
INSERT INTO Stock (id_tienda, isbn, copias, fecha_conteo) VALUES
(1, '9780000000001', 0, '2026-10-01'),
(1, '9780000000002', 2, '2026-10-01'),
(1, '9780000000003', 3, '2026-10-01'),
(1, '9780000000004', 1, '2026-10-01'),
(1, '9780000000005', 0, '2026-10-01'),
(2, '9780000000001', 4, '2026-10-02'),
(2, '9780000000002', 0, '2026-10-02'),
(2, '9780000000003', 1, '2026-10-02'),
(2, '9780000000004', 0, '2026-10-02'),
(2, '9780000000005', 2, '2026-10-02'),
(3, '9780000000001', 3, '2026-10-03'),
(3, '9780000000002', 1, '2026-10-03'),
(3, '9780000000003', 0, '2026-10-03'),
(3, '9780000000004', 2, '2026-10-03'),
(3, '9780000000005', 1, '2026-10-03');

-- 7. Empleados
INSERT INTO Empleado (dni, nombre, apellidos, cargo, fecha_contratacion, correo_trabajo, id_tienda) VALUES
('11111111A', 'Marta', 'Sánchez Ruiz', 'Dependienta', '2020-03-15', 'marta@paginasvillas.example', 1),
('22222222B', 'Diego', 'López Martín', 'Encargado', '2019-06-01', 'diego@paginasvillas.example', 2),
('33333333C', 'Lucía', 'Pérez Gil', 'Dependienta', '2022-09-10', 'lucia@paginasvillas.example', 3);

-- 8. Clientes (datos ficticios)
INSERT INTO Cliente (id_cliente, nombre_completo, correo, telefono, fecha_alta) VALUES
(1, 'Sofía Martín', 'sofia.martin@example.com', '600100101', '2024-04-10'),
(2, 'Hugo Navarro', 'hugo.navarro@example.com', '600100202', '2025-01-20'),
(3, 'Clara Romero', 'clara.romero@example.com', '600100303', '2023-11-05'),
(4, 'Mario Gil', 'mario.gil@example.com', NULL, NULL);

-- 9. Pedidos (todos ficticios y fechados en 2026 para la consulta anual)
INSERT INTO Pedido (id_pedido, dni, id_cliente, id_tienda, fecha, forma_pago, estado) VALUES
(1, '11111111A', 1, 1, '2026-01-12 10:15:00', 'tarjeta', 'entregado'),
(2, '11111111A', 2, 1, '2026-02-03 12:30:00', 'efectivo', 'entregado'),
(3, '22222222B', 3, 2, '2026-03-17 16:20:00', 'bizum', 'entregado'),
(4, '33333333C', 1, 3, '2026-04-08 11:05:00', 'tarjeta', 'entregado'),
(5, '11111111A', 3, 1, '2026-05-22 18:40:00', 'efectivo', 'preparado'),
(6, '22222222B', 4, 2, '2026-06-14 09:50:00', 'tarjeta', 'entregado'),
(7, '33333333C', 3, 3, '2026-07-02 13:10:00', 'bizum', 'entregado');

-- 10. Líneas de los pedidos: cada pedido tiene varias líneas
INSERT INTO DetallePedido (id_pedido, isbn, cantidad, precio_unitario) VALUES
(1, '9780000000001', 1, 22.00),
(1, '9780000000002', 1, 18.50),
(2, '9780000000002', 2, 18.50),
(2, '9780000000003', 1, 12.90),
(3, '9780000000001', 1, 22.00),
(3, '9780000000004', 1, 25.00),
(4, '9780000000004', 2, 25.00),
(4, '9780000000005', 1, 16.00),
(5, '9780000000001', 1, 22.00),
(5, '9780000000003', 2, 12.90),
(6, '9780000000001', 2, 22.00),
(6, '9780000000002', 1, 18.50),
(7, '9780000000002', 1, 18.50),
(7, '9780000000005', 1, 16.00);
