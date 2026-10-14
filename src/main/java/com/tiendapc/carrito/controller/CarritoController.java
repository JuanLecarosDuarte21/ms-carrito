
package com.tiendapc.carrito.controller;

import com.tiendapc.carrito.model.Carrito;
import com.tiendapc.carrito.service.CarritoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carritos")
public class CarritoController {

    private final CarritoService carritoService;

    public CarritoController(CarritoService carritoService) {
        this.carritoService = carritoService;
    }

    // Agregar producto al carrito
    @PostMapping
    public ResponseEntity<Carrito> agregarProducto(
            @Valid @RequestBody Carrito carrito) {

        Carrito guardado = carritoService.agregarProducto(carrito);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(guardado);
    }

    // Listar todos los registros
    @GetMapping
    public List<Carrito> listarCarritos() {
        return carritoService.listarCarritos();
    }

    // Buscar registro por ID
    @GetMapping("/{id}")
    public ResponseEntity<Carrito> buscarPorId(@PathVariable Long id) {

        return carritoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Consultar carrito de un usuario
    @GetMapping("/usuario/{usuarioId}")
    public List<Carrito> buscarPorUsuario(
            @PathVariable Long usuarioId) {

        return carritoService.buscarPorUsuario(usuarioId);
    }

    // Actualizar cantidad
    @PutMapping("/{id}")
    public ResponseEntity<Carrito> actualizarCantidad(
            @PathVariable Long id,
            @RequestBody ActualizarCantidadRequest datos) {

        if (datos.cantidad() == null || datos.cantidad() < 1) {
            return ResponseEntity.badRequest().build();
        }

        return carritoService
                .actualizarCantidad(id, datos.cantidad())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar producto del carrito
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(
            @PathVariable Long id) {

        boolean eliminado = carritoService.eliminarProducto(id);

        if (!eliminado) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }

    // Datos necesarios para actualizar la cantidad
    public record ActualizarCantidadRequest(
            @Min(1) Integer cantidad
    ) {
    }
}
