package com.restaurant_backend.service.implementation;

import com.restaurant_backend.model.Pago;

import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.repository.IPagoRepository;
import com.restaurant_backend.service.IPagoService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagoService extends GenericService<Pago, Integer> implements IPagoService {
    private final IPagoRepository repo;

    @Override
    protected IGenericRepository<Pago, Integer> getRepo() {
        return repo;
    }
}
