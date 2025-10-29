package com.restaurant_backend.service.implementation;

import org.springframework.stereotype.Service;

import com.restaurant_backend.model.Mesa;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.repository.IMesaRepository;
import com.restaurant_backend.service.IMesaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MesaService extends GenericService<Mesa, Integer> implements IMesaService{

    private final IMesaRepository repo;

    @Override
    protected IGenericRepository<Mesa, Integer> getRepo() {
        return repo;
    }
}
