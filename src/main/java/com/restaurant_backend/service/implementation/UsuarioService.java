package com.restaurant_backend.service.implementation;

import com.restaurant_backend.model.Usuario;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.repository.IUsuarioRepository;
import com.restaurant_backend.service.IUsuarioService;
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
