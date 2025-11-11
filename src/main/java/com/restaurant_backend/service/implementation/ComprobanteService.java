package com.restaurant_backend.service.implementation;

import com.restaurant_backend.model.Comprobante;
import com.restaurant_backend.model.DetallePedido;
import com.restaurant_backend.repository.IComprobanteRepository;
import com.restaurant_backend.repository.IDetallePedidoRepository;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.service.IComprobanteService;
import com.restaurant_backend.service.IDetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ComprobanteService extends GenericService<Comprobante, Integer> implements IComprobanteService {
    private final IComprobanteRepository repo;

    @Override
    protected IGenericRepository<Comprobante, Integer> getRepo() {
        return repo;
    }
}