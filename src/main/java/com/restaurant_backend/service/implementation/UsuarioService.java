package com.restaurant_backend.service.implementation;

import com.restaurant.model.Usuario;
import com.restaurant.repository.IGenericRepository;
import com.restaurant.repository.IUsuarioRepository;
import com.restaurant.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericService<Usuario, Integer> implements IUsuarioService {
    private final IUsuarioRepository repo;

    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }
}
