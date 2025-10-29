package com.restaurant_backend.service.implementation;

import com.restaurant.model.Producto;
import com.restaurant.repository.IGenericRepository;
import com.restaurant.repository.IProductoRepository;
import com.restaurant.service.IProductoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductoService extends GenericService<Producto, Integer> implements IProductoService {
    private final IProductoRepository repo;

    @Override
    protected IGenericRepository<Producto, Integer> getRepo() {
        return repo;
    }
}
