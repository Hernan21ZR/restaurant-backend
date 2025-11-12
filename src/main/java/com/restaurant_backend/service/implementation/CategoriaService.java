package com.restaurant_backend.service.implementation;

import org.springframework.stereotype.Service;
import com.restaurant_backend.model.Categoria;
import com.restaurant_backend.repository.ICategoriaRepository;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.service.ICategoriaService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService extends GenericService<Categoria, Integer> implements ICategoriaService {

    private final ICategoriaRepository repo;

    @Override
    protected IGenericRepository<Categoria, Integer> getRepo() {
        return repo;
    }
}