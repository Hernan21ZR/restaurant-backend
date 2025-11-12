package com.restaurant_backend.service.implementation;

import org.springframework.stereotype.Service;
import com.restaurant_backend.model.Inventario;
import com.restaurant_backend.repository.IInventarioRepository;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.service.IInventarioService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventarioService extends GenericService<Inventario, Integer> implements IInventarioService {

    private final IInventarioRepository repo;

    @Override
    protected IGenericRepository<Inventario, Integer> getRepo() {
        return repo;
    }
}