package com.restaurant_backend.service.implementation;

import com.restaurant.model.Rol;
import com.restaurant.repository.IGenericRepository;
import com.restaurant.repository.IRolRepository;
import com.restaurant.service.IRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RolService extends GenericService<Rol, Integer> implements IRolService {
    private final IRolRepository repo;

    @Override
    protected IGenericRepository<Rol, Integer> getRepo() {
        return repo;
    }
}
