package com.restaurant_backend.service;

import com.restaurant_backend.model.Menu;
import java.util.List;

public interface IMenuService extends IGenericService<Menu, Integer> {
    List<Menu> getMenusByCorreo(String correo);

    void asignarRoles(Integer idMenu, List<Integer> idRoles) throws Exception;
}
