# Microservicio de Carrito de Compras — Tienda PC

## Descripción

Microservicio desarrollado con Java y Spring Boot para gestionar el carrito de compras de una tienda en línea de componentes de PC.

Permite agregar productos, consultar los productos de un usuario, modificar cantidades y eliminar productos del carrito.

## Tecnologías utilizadas

- Java 21
- Spring Boot 4.0.8
- Spring Data JPA
- MySQL
- Maven
- Postman
- Git y GitHub

## Configuración

- **Puerto:** 8084
- **Base de datos:** `tienda_carrito_db`
- **URL base:** `http://localhost:8084/api/carritos`

## Funcionalidades

- Agregar productos al carrito.
- Acumular cantidades cuando se agrega nuevamente el mismo producto.
- Consultar todos los registros.
- Consultar el carrito de un usuario.
- Buscar un registro por ID.
- Actualizar cantidades.
- Eliminar productos del carrito.

## Endpoints REST

| Método | Endpoint | Descripción |
|---|---|---|
| POST | `/api/carritos` | Agregar producto |
| GET | `/api/carritos` | Listar registros |
| GET | `/api/carritos/{id}` | Buscar por ID |
| GET | `/api/carritos/usuario/{usuarioId}` | Consultar carrito del usuario |
| PUT | `/api/carritos/{id}` | Actualizar cantidad |
| DELETE | `/api/carritos/{id}` | Eliminar producto |

## Ejemplo para agregar un producto

**POST** `/api/carritos`

```json
{
  "usuarioId": 1,
  "productoId": 2,
  "cantidad": 2
}
```

## Ejemplo para actualizar la cantidad

**PUT** `/api/carritos/1`

```json
{
  "cantidad": 5
}
```

## Base de datos

```sql
CREATE DATABASE IF NOT EXISTS tienda_carrito_db;
```

La aplicación utiliza JPA para gestionar la tabla `carritos`.

## Ejecución

Configurar la variable de entorno `DB_PASSWORD` con la contraseña de MySQL.

En PowerShell:

```powershell
$env:DB_PASSWORD = "TU_CONTRASEÑA_MYSQL"
.\mvnw.cmd spring-boot:run
```

## Compilación

```powershell
.\mvnw.cmd clean package
```

## Estructura del proyecto

- `model`: entidades de la base de datos.
- `repository`: acceso a datos mediante JPA.
- `service`: lógica de negocio.
- `controller`: endpoints REST.
- `config`: configuraciones adicionales.

## Alcance actual

Los identificadores de usuario y producto se almacenan como referencias numéricas. La validación automática contra otros microservicios y la comprobación de stock todavía no están implementadas.

## Proyecto académico

Este microservicio forma parte de una arquitectura de ocho microservicios para una tienda de componentes de PC.