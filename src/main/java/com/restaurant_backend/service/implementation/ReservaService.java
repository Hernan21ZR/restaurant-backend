package com.restaurant_backend.service.implementation;

import org.springframework.stereotype.Service;

import com.restaurant_backend.model.Reserva;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.repository.IReservaRepository;
import com.restaurant_backend.service.IReservaService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReservaService extends GenericService<Reserva, Integer> implements IReservaService{

    private final IReservaRepository repo;

    @Override
    protected IGenericRepository<Reserva, Integer> getRepo() {
        return repo;
    }
}


