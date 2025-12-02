package com.restaurant_backend.service.implementation;

import com.restaurant_backend.model.Menu;
import com.restaurant_backend.repository.IMenuRepository;
import com.restaurant_backend.repository.IMenuRolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuRolService {

    private final IMenuRolRepository repo;
    private final IMenuRepository menuRepo;

    public List<Menu> listarMenusPorRol(Integer idRol) {
        return menuRepo.findMenusByRol(idRol);
    }

    public void asignarMenu(Integer idMenu, Integer idRol) {
        repo.asignarMenuRol(idMenu, idRol);
    }

    public void eliminarMenu(Integer idMenu, Integer idRol) {
        repo.eliminarMenuRol(idMenu, idRol);
    }
}


