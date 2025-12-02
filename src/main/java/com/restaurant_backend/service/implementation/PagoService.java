package com.restaurant_backend.service.implementation;

import com.restaurant_backend.dto.PagoDTO;
import com.restaurant_backend.model.Pago;

import com.restaurant_backend.model.Venta;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.repository.IPagoRepository;
import com.restaurant_backend.repository.IVentaRepository;
import com.restaurant_backend.service.IPagoService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class PagoService extends GenericService<Pago, Integer> implements IPagoService {

    private final IPagoRepository repo;
    private final IVentaRepository ventaRepo;

    @Override
    protected IGenericRepository<Pago, Integer> getRepo() {
        return repo;
    }

    @Transactional
    public Pago registrarPago(PagoDTO dto) throws Exception {

        Venta venta = ventaRepo.findById(dto.getVentaId())
                .orElseThrow(() -> new Exception("Venta no encontrada"));

        // Validar que el pago no exceda el total
        double pagosPrevios = repo.findByVentaId(venta.getId())
                .stream()
                .mapToDouble(Pago::getMonto)
                .sum();

        if (pagosPrevios + dto.getMonto() > venta.getTotal()) {
            throw new Exception("El pago excede el total de la venta");
        }

        Pago pago = new Pago();
        pago.setMetodo(dto.getMetodo());
        pago.setMonto(dto.getMonto());
        pago.setFechaPago(new Date());
        pago.setVenta(venta);

        return repo.save(pago);
    }
}
