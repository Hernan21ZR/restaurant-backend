package com.restaurant_backend.service.implementation;

import com.restaurant_backend.model.Menu;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.repository.IMenuRepository;
import com.restaurant_backend.repository.IMenuRolRepository;
import com.restaurant_backend.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MenuService extends GenericService<Menu, Integer> implements IMenuService {

    private final IMenuRepository repo;
    private final IMenuRolRepository menuRolRepo;

    @Override
    protected IGenericRepository<Menu, Integer> getRepo() {
        return repo;
    }

    @Override
    public List<Menu> getMenusByCorreo(String correo) {
        return repo.getMenusByCorreo(correo);
    }

    @Override
    public void asignarRoles(Integer idMenu, List<Integer> idRoles) throws Exception {

        menuRolRepo.deleteRolesByMenu(idMenu);

        idRoles.forEach(idRol -> menuRolRepo.asignarMenuRol(idMenu, idRol));
    }
}

