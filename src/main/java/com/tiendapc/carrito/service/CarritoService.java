
package com.tiendapc.carrito.service;

import com.tiendapc.carrito.model.Carrito;
import com.tiendapc.carrito.repository.CarritoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarritoService {

    private final CarritoRepository carritoRepository;

    public CarritoService(CarritoRepository carritoRepository) {
        this.carritoRepository = carritoRepository;
    }

    // Agregar producto al carrito
    public Carrito agregarProducto(Carrito carrito) {

        Optional<Carrito> existente =
                carritoRepository.findByUsuarioIdAndProductoId(
                        carrito.getUsuarioId(),
                        carrito.getProductoId()
                );

        if (existente.isPresent()) {
            Carrito producto = existente.get();

            producto.setCantidad(
                    producto.getCantidad() + carrito.getCantidad()
            );

            return carritoRepository.save(producto);
        }

        return carritoRepository.save(carrito);
    }

    // Listar todos los registros
    public List<Carrito> listarCarritos() {
        return carritoRepository.findAll();
    }

    // Buscar registro por ID
    public Optional<Carrito> buscarPorId(Long id) {
        return carritoRepository.findById(id);
    }

    // Consultar carrito de un usuario
    public List<Carrito> buscarPorUsuario(Long usuarioId) {
        return carritoRepository.findByUsuarioId(usuarioId);
    }

    // Actualizar cantidad de un producto
    public Optional<Carrito> actualizarCantidad(
            Long id,
            Integer cantidad
    ) {

        if (cantidad == null || cantidad < 1) {
            throw new IllegalArgumentException(
                    "La cantidad debe ser mayor a cero"
            );
        }

        return carritoRepository.findById(id).map(carrito -> {
            carrito.setCantidad(cantidad);
            return carritoRepository.save(carrito);
        });
    }

    // Eliminar producto del carrito
    public boolean eliminarProducto(Long id) {

        if (!carritoRepository.existsById(id)) {
            return false;
        }

        carritoRepository.deleteById(id);
        return true;
    }
}
