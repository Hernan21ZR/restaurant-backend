package com.restaurant_backend.service.implementation;

import com.restaurant_backend.model.Producto;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.repository.IProductoRepository;
import com.restaurant_backend.service.IProductoService;
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
