package com.restaurant_backend.service.implementation;

import com.restaurant_backend.dto.DetallePedidoDTO;
import com.restaurant_backend.dto.PedidoDTO;
import com.restaurant_backend.exception.ModelNotFoundException;
import com.restaurant_backend.model.*;
import com.restaurant_backend.repository.*;
import com.restaurant_backend.service.IPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService extends GenericService<Pedido, Integer> implements IPedidoService {

    private final IPedidoRepository repo;
    private final IDetallePedidoRepository detalleRepo;
    private final IProductoRepository productoRepo;
    private final IUsuarioRepository usuarioRepo;
    private final IMesaRepository mesaRepo;
    private final IClienteRepository clienteRepo;
    private final IRolRepository rolRepo;

    @Override
    protected IGenericRepository<Pedido, Integer> getRepo() {
        return repo;
    }

    @Override
    @Transactional
    public Pedido saveFromDto(PedidoDTO dto) throws Exception {
        Pedido p = new Pedido();

        p.setFecha(dto.getFecha() != null ? dto.getFecha() : new Date());
        p.setEstado("PENDIENTE");

        Mesa mesa = mesaRepo.findById(dto.getMesaId())
                .orElseThrow(() -> new ModelNotFoundException("Mesa no encontrada: " + dto.getMesaId()));
        p.setMesa(mesa);

        Usuario usuario = usuarioRepo.findById(dto.getUsuarioId())
                .orElseThrow(() -> new ModelNotFoundException("Usuario (mesero) no encontrado: " + dto.getUsuarioId()));
        boolean esMesero = usuario.getRoles() != null && usuario.getRoles().stream()
                .anyMatch(r -> "MESERO".equalsIgnoreCase(r.getNombre()));
        if (!esMesero) throw new ModelNotFoundException("Usuario no tiene rol MESERO");
        p.setUsuario(usuario);

        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepo.findById(dto.getClienteId())
                    .orElseThrow(() -> new ModelNotFoundException("Cliente no encontrado"));
            p.setCliente(cliente);
        }

        List<DetallePedido> detalles = dto.getDetalles().stream().map(d -> {
            Producto prod = productoRepo.findById(d.getProductoId())
                    .orElseThrow(() -> new ModelNotFoundException("Producto no encontrado: " + d.getProductoId()));
            DetallePedido det = new DetallePedido();
            det.setCantidad(d.getCantidad());
            det.setProducto(prod);
            det.setSubtotal(prod.getPrecio() * d.getCantidad());
            det.setPedido(p);
            return det;
        }).collect(Collectors.toList());

        p.setDetalles(detalles);

        return repo.save(p);
    }

    @Override
    @Transactional
    public Pedido updateFromDto(PedidoDTO dto, Integer id) throws Exception {
        Pedido existing = repo.findById(id).orElseThrow(() -> new ModelNotFoundException("ID NOT FOUND: " + id));

        String estadoAntiguo = existing.getEstado();
        String estadoNuevo = dto.getEstado() != null ? dto.getEstado() : estadoAntiguo;

        if (dto.getMesaId() != null) {
            Mesa mesa = mesaRepo.findById(dto.getMesaId())
                    .orElseThrow(() -> new ModelNotFoundException("Mesa no encontrada: " + dto.getMesaId()));
            existing.setMesa(mesa);
        }

        if (dto.getUsuarioId() != null) {
            Usuario usuario = usuarioRepo.findById(dto.getUsuarioId())
                    .orElseThrow(() -> new ModelNotFoundException("Usuario no encontrado: " + dto.getUsuarioId()));
            boolean esMesero = usuario.getRoles() != null && usuario.getRoles().stream()
                    .anyMatch(r -> "MESERO".equalsIgnoreCase(r.getNombre()));
            if (!esMesero) throw new ModelNotFoundException("Usuario no tiene rol MESERO");
            existing.setUsuario(usuario);
        }

        if (dto.getClienteId() != null) {
            Cliente cliente = clienteRepo.findById(dto.getClienteId())
                    .orElseThrow(() -> new ModelNotFoundException("Cliente no encontrado"));
            existing.setCliente(cliente);
        } else existing.setCliente(null);

        if (dto.getFecha() != null) existing.setFecha(dto.getFecha());

        boolean pasaAPreparacion = !"PREPARACION".equalsIgnoreCase(estadoAntiguo)
                && "PREPARACION".equalsIgnoreCase(estadoNuevo);

        existing.getDetalles().clear();
        List<DetallePedido> nuevosDetalles = dto.getDetalles().stream().map(d -> {
            Producto prod = productoRepo.findById(d.getProductoId())
                    .orElseThrow(() -> new ModelNotFoundException("Producto no encontrado: " + d.getProductoId()));
            DetallePedido det = new DetallePedido();
            det.setCantidad(d.getCantidad());
            det.setProducto(prod);
            det.setSubtotal(prod.getPrecio() * d.getCantidad());
            det.setPedido(existing);
            return det;
        }).collect(Collectors.toList());

        existing.setDetalles(nuevosDetalles);
        existing.setEstado(estadoNuevo);

        if (pasaAPreparacion) {
            for (DetallePedido det : nuevosDetalles) {
                Producto prod = productoRepo.findById(det.getProducto().getId())
                        .orElseThrow(() -> new ModelNotFoundException("Producto no encontrado: " + det.getProducto().getId()));
                if (prod.getStockActual() < det.getCantidad()) {
                    throw new ArithmeticException("Stock insuficiente: " + prod.getNombre());
                }
            }
            for (DetallePedido det : nuevosDetalles) {
                Producto prod = productoRepo.findById(det.getProducto().getId()).get();
                prod.setStockActual(prod.getStockActual() - det.getCantidad());
                productoRepo.save(prod);
            }
        }

        return repo.save(existing);
    }

    @Transactional
    public Pedido updateEstado(Integer id, String estadoNuevo) throws Exception {
        Pedido existing = repo.findById(id)
                .orElseThrow(() -> new ModelNotFoundException("Pedido no encontrado: " + id));

        String estadoAntiguo = existing.getEstado();
        boolean pasaAPreparacion = !"PREPARACION".equalsIgnoreCase(estadoAntiguo)
                && "PREPARACION".equalsIgnoreCase(estadoNuevo);

        existing.setEstado(estadoNuevo);

        if (pasaAPreparacion) {
            for (DetallePedido det : existing.getDetalles()) {
                Producto prod = productoRepo.findById(det.getProducto().getId())
                        .orElseThrow(() -> new ModelNotFoundException("Producto no encontrado: " + det.getProducto().getId()));
                if (prod.getStockActual() < det.getCantidad()) {
                    throw new ArithmeticException("Stock insuficiente: " + prod.getNombre());
                }
                prod.setStockActual(prod.getStockActual() - det.getCantidad());
                productoRepo.save(prod);
            }
        }

        return repo.save(existing);
    }

}
