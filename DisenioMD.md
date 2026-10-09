# Apartados de la base de datos

## Apartado 1. Resumen del caso

>La dueña de "Paginas de Villa Serena" nos pide ayuda con la gestion de sus productos entre\
>tiendas, necesita poder ubicar libros entre las diferentes tiendas y poder conocer de\
>ello sus cantidades por tienda, a su vez le gustaria poder encontrar libros buscando\
>simplemente por el nombre de su autor, en base al stock quiere poder saber exactamente\
>el numero de copias de cada libro y cuando fue que se conto su cantidad.\
>Ahora hablando de personal y clientes, pide ayuda para saber sus datos, del personal ademas\
>su fecha de contratacion y correo de trabajo, los clientes pueden convertise en socios\
>si lo son, se guardara tambien su telefono y la fecha en la que se dio de alta como socio.\
>Por ultimo, basandose en un ticket de ejemplo se nos pide que al revisar pedidos antiguos\
>mantengan el precio de los productos que tenian en ese entonces.

## Apartado 2. Análisis del caso

### 2.1 Entidades y atributos

| Entidad | Atributos encontrados | Fragmento del caso |
|---|---|---|
| Tienda | nombre, dirección, teléfono, ciudad | §1: cada tienda tiene su nombre, dirección, teléfono y ciudad. |
| Libro | ISBN, título, año de publicación, número de páginas, precio de catálogo | §2: datos guardados de cada libro. |
| Editorial | nombre, país, teléfono de contacto | §2: datos guardados de cada editorial. |
| Autor | nombre, nacionalidad, año de nacimiento | §2: datos guardados de cada autor. |
| Stock | tienda, libro, número de copias, fecha del último recuento | §3: el stock se controla por libro y tienda. |
| Empleado | DNI, nombre, apellidos, cargo, fecha de contratación, correo de trabajo, tienda | §4: datos de los empleados y tienda en la que trabajan. |
| Cliente | nombre completo, correo electrónico, teléfono y fecha de alta como socio | §5: todos los clientes se registran; teléfono y fecha de alta se guardan para los socios. |
| Pedido | identificador, fecha, forma de pago, estado, tienda, empleado y cliente | §6: datos del pedido que aparecen en el ticket. |
| Detalle de pedido | pedido, libro, cantidad, precio cobrado por unidad | §6: cada pedido puede incluir varios libros y se guarda el precio realmente cobrado. |

### 2.2 Relaciones

| Relación | Cardinalidad | Razonamiento |
|---|---|---|
| Editorial – Libro (`Publica`) | 1:N | Una editorial puede publicar muchos libros; cada libro pertenece a una sola editorial (§2). |
| Autor – Libro (`Escribe`) | N:M | Un autor puede escribir varios libros y un libro puede tener varios autores (§2). |
| Tienda – Libro (`Stock`) | N:M | Una tienda puede tener muchos libros y un libro puede estar en varias tiendas (§3). |
| Tienda – Empleado (`Trabaja`) | 1:N | Una tienda tiene varios empleados; cada empleado trabaja en una sola tienda (§4). |
| Cliente – Pedido (`Realiza`) | 1:N | Un cliente puede realizar varios pedidos; cada pedido corresponde a un cliente (§6). |
| Tienda – Pedido (`Se realiza en`) | 1:N | Una tienda puede gestionar muchos pedidos; cada pedido se realiza en una tienda (§6). |
| Empleado – Pedido (`Atiende`) | 1:N | Un empleado puede atender varios pedidos; cada pedido lo atiende un empleado (§6). |
| Pedido – Libro (`Contiene`) | N:M | Un pedido puede contener varios libros y un libro puede aparecer en muchos pedidos. Se resuelve con `DetallePedido` (§6). |

Las relaciones N:M se resuelven mediante las tablas intermedias `Escribe`, `Stock` y `DetallePedido`. Estas tablas guardan datos propios de la relación: tipo de autoría; copias y fecha de recuento; y cantidad y precio unitario cobrado, respectivamente.

### 2.3 Datos descartados

| Dato | Motivo |
|---|---|
| Total del pedido | Se puede calcular sumando `cantidad * precio_unitario` de sus líneas. |
| Nombre de la editorial dentro de `Libro` como texto repetido | Se guarda una referencia a la tabla `Editorial`, evitando repetir sus datos en cada libro. |
| Nombre del autor dentro de `Libro` como texto único | Un libro puede tener varios autores; se utiliza `Escribe` para relacionarlos. |
| Stock como una única columna en `Libro` | El stock depende de la tienda y del libro, por lo que se guarda en `Stock`. |
| Precio histórico tomado del catálogo actual | No sirve para pedidos antiguos; el precio cobrado se guarda en `DetallePedido`. |
| Historial de cambios de tienda de los empleados | El caso indica que no hace falta conservarlo; solo se guarda la tienda actual. |

### Preguntas A Responder

- ¿Qué libros tiene la tienda Centro y cuántas copias quedan?
- ¿Cuál es el libro más vendido en cada tienda?
- ¿Cuánto ha facturado cada tienda este año?
- ¿Qué clientes han hecho más de dos pedidos?
- ¿Qué libros están agotados en una tienda pero disponibles en otra?
- ¿Qué empleado ha atendido más pedidos?
- ¿Qué autores tienen libros en más de una editorial?

## Notas Gestora

### lo que no queremos tener al final

| Libro | Autor | Editorial | Precio | Stock | Contado el |
|---|---|---|---|---|---|
| Rayuela | Julio Cortázar | Alfaguara | 16,50 | 4 | 02/03/2026 |
| Ficciones | Jorge Luis Borges | Alianza | 12,00 | 2 | 02/03/2026 |
| Cuentos de Eva Luna | Isabel Allende | Plaza & Janés | 14,90 | 0 | 02/03/2026 |
| Antología del cuento | Cortázar / Borges | Alianza | 18,00 | 6 | 28/02/2026 |

>"algunos libros tienen más de un autor"

## 3. Reglas de negocio

1. Cada tienda tiene un identificador y un nombre, dirección, teléfono y ciudad.
2. Cada libro se identifica por un ISBN de 13 cifras.
3. Cada libro pertenece a una sola editorial; una editorial puede publicar varios libros.
4. Un libro puede tener varios autores y un autor puede escribir varios libros.
5. Para cada relación entre un libro y un autor se indica si su participación es principal o colaboradora.
6. El stock se registra para cada combinación de tienda y libro e incluye el número de copias y la fecha del último recuento.
7. Si un libro no está registrado en una tienda, no existe una fila de stock para esa combinación.
8. Cada empleado trabaja en una sola tienda. Si cambia de tienda, se actualiza su tienda asignada y no se conserva el historial.
9. El correo electrónico de cada cliente es único.
10. Los clientes que no son socios también se registran con nombre completo y correo electrónico.
11. El teléfono y la fecha de alta como socio pueden quedar vacíos cuando el cliente no es socio.
12. Cada pedido pertenece a un cliente, se realiza en una tienda y lo atiende un empleado.
13. La forma de pago de un pedido será efectivo, tarjeta o bizum.
14. El estado de un pedido será preparado, entregado o cancelado.
15. Un pedido puede incluir varios libros y cada línea guarda la cantidad de unidades.
16. Cada línea de pedido guarda el precio unitario cobrado en ese momento, que no cambia si se modifica el precio del catálogo.
17. Las cantidades y los precios no pueden ser negativos.

## Apartado 4. Diagrama Entidad-Relacion

![Imagen Entidad-Relacion](./Diagrama.png)

| Relación | Tipo | Cómo se resuelve |
|---|---|---|
| Editorial – Libro | 1:N | `Libro.id_editorial` referencia `Editorial.id_editorial`. |
| Autor – Libro | N:M | Tabla intermedia `Escribe`, con el atributo `tipo_autoria`. |
| Tienda – Libro | N:M | Tabla intermedia `Stock`, con `copias` y `fecha_conteo`. |
| Tienda – Empleado | 1:N | `Empleado.id_tienda` referencia `Tienda.id_tienda`. |
| Cliente – Pedido | 1:N | `Pedido.id_cliente` referencia `Cliente.id_cliente`. |
| Tienda – Pedido | 1:N | `Pedido.id_tienda` referencia `Tienda.id_tienda`. |
| Empleado – Pedido | 1:N | `Pedido.dni` referencia `Empleado.dni`. |
| Pedido – Libro | N:M | Tabla intermedia `DetallePedido`, con `cantidad` y `precio_unitario`. |

## 5. Modelo lógico
## 6. Script SQL (schema.sql)
## 7. Diccionario de datos
## 8. Decisiones de diseño
## 9. Datos de prueba
## 10. Consultas de prueba
## 11. Limitaciones y mejoras futuras