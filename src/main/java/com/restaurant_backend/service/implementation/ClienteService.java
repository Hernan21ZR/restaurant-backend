package com.restaurant_backend.service.implementation;

import com.restaurant_backend.model.Cliente;
import com.restaurant_backend.repository.IClienteRepository;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.service.IClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClienteService extends GenericService<Cliente, Integer> implements IClienteService {
    private final IClienteRepository repo;

    @Override
    protected IGenericRepository<Cliente, Integer> getRepo() {
        return repo;
    }
}
