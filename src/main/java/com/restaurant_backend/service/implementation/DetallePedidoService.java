package com.restaurant_backend.service.implementation;

import com.restaurant_backend.model.DetallePedido;
import com.restaurant_backend.repository.IDetallePedidoRepository;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.service.IDetallePedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DetallePedidoService extends GenericService<DetallePedido, Integer> implements IDetallePedidoService {
    private final IDetallePedidoRepository repo;

    @Override
    protected IGenericRepository<DetallePedido, Integer> getRepo() {
        return repo;
    }
}
