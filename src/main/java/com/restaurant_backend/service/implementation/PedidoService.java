package com.restaurant_backend.service.implementation;

import com.restaurant_backend.model.Pedido;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.repository.IPedidoRepository;
import com.restaurant_backend.service.IPedidoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PedidoService extends GenericService<Pedido,Integer> implements IPedidoService {
    private final IPedidoRepository repo;

    @Override
    protected IGenericRepository<Pedido, Integer> getRepo(){
        return repo;
    }
}
