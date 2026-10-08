# Apartados de la base de datos

## Las tiendas
1. Tiendas (Centro, Ribera *(está en el pueblo vecino de Aldeaverde)* y Universidad)
2. Tiendas/Atributos (Nombre, Dirección, Numero de teléfono y Ciudad)

## El catalogo
1. Libros
 - ISBN (13 cifras)
 - Titulo
 - Año de publicación
 - Numero de paginas
 - Precio en el catalogo
---
2. Editoriales 
 - Nombre
 - País
 - Teléfono de contacto

 >"Un libro lo publica una sola editorial"
---
3. Autores 
 - Nombre
 - Nacionalidad
 - Año de nacimiento

 >"Hay libros escritos por dos o tres personas"\
 >"Distinguir si el autor es principal o colaborador en cada libro (por ejemplo, quién hace el prólogo)."
---
## Inventario

1.Stock
 - Copias de libro por tienda
 - Ultima vez que se conto el stock

>"Un libro puede no estar en una tienda; en ese caso, simplemente no aparece."
---
## Personal

1.Empleados
 - DNI
 - Nombre
 - Apellidos
 - Cargo
 - Fecha Contratacion
 - Correo de trabajo

>"En cada tienda trabajan varios empleados, y cada empleado trabaja en una sola tienda."\
>"Si un empleado cambia de tienda, no me importa guardar el historial; simplemente que figure en la nueva."
---
2. Clientes
 - Nombre completo
 - Correo (único, lo usan para enviar avisos)

>"Los clientes pueden darse de alta como socios y así obtienen descuentos."
---
3. Socios
 - Nombre completo
 - Correo (único, lo usan para enviar avisos)
 - Telefono (Opcional)
 - Fecha Alta
---
## Ventas

1. Pedido
 - Fecha
 - Forma de pago (efectivo, tarjeta o bizum)
 - Estado (preparado, entregado o cancelado)

>"Un pedido se hace siempre en una tienda, lo atiende un empleado y lo compra un cliente."
>"Si abro un pedido antiguo y me enseña el precio nuevo, la factura ya no cuadra. Quiero que cada pedido guarde lo que realmente se cobró por cada libro."
---
## Preguntas A Responder

- ¿Qué libros tiene la tienda Centro y cuántas copias quedan?
- ¿Cuál es el libro más vendido en cada tienda?
- ¿Cuánto ha facturado cada tienda este año?
- ¿Qué clientes han hecho más de dos pedidos?
- ¿Qué libros están agotados en una tienda pero disponibles en otra?
- ¿Qué empleado ha atendido más pedidos?
- ¿Qué autores tienen libros en más de una editorial?
---
## Notas Gestora

### lo que no queremos tener al final

| Libro | Autor | Editorial | Precio | Stock | Contado el |
|---|---|---|---|---|---|
| Rayuela | Julio Cortázar | Alfaguara | 16,50 | 4 | 02/03/2026 |
| Ficciones | Jorge Luis Borges | Alianza | 12,00 | 2 | 02/03/2026 |
| Cuentos de Eva Luna | Isabel Allende | Plaza & Janés | 14,90 | 0 | 02/03/2026 |
| Antología del cuento | Cortázar / Borges | Alianza | 18,00 | 6 | 28/02/2026 |

>"algunos libros tienen más de un autor"