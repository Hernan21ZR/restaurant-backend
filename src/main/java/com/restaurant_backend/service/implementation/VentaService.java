package com.restaurant_backend.service.implementation;

import com.restaurant_backend.exception.ModelNotFoundException;
import com.restaurant_backend.model.DetallePedido;
import com.restaurant_backend.model.Pedido;
import com.restaurant_backend.model.Venta;
import com.restaurant_backend.repository.*;
import com.restaurant_backend.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class VentaService extends GenericService<Venta, Integer> implements IVentaService {

    private final IVentaRepository repo;
    private final IPedidoRepository pedidoRepo;

    @Override
    protected IGenericRepository<Venta, Integer> getRepo() {
        return repo;
    }

    @Override
    public Venta generarVenta(Integer pedidoId) throws Exception {

        // 1. Buscar pedido
        Pedido pedido = pedidoRepo.findById(pedidoId)
                .orElseThrow(() -> new ModelNotFoundException("Pedido no encontrado: " + pedidoId));

        // 2. Validación correcta del estado
        if (!"LISTO".equalsIgnoreCase(pedido.getEstado()) &&
                !"ENTREGADO".equalsIgnoreCase(pedido.getEstado())) {
            throw new Exception("Solo se puede generar una venta para pedidos LISTO o ENTREGADO");
        }

        // 3. Calcular total desde los detalles
        double total = pedido.getDetalles().stream()
                .mapToDouble(DetallePedido::getSubtotal)
                .sum();

        // 4. Crear venta
        Venta venta = new Venta();
        venta.setFecha(new Date());
        venta.setTotal(total);
        venta.setPedido(pedido);
        venta.setCliente(pedido.getCliente());

        // 5. Cambiar estado del pedido a FACTURADO
        pedido.setEstado("FACTURADO");
        pedidoRepo.save(pedido);

        // 6. Guardar venta
        return repo.save(venta);
    }
}
