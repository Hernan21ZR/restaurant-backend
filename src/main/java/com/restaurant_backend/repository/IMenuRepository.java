package com.restaurant_backend.repository;

import com.restaurant_backend.model.Menu;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IMenuRepository extends IGenericRepository<Menu, Integer> {

    @Query(value = """
        SELECT m.* 
        FROM menu m
        INNER JOIN menu_rol mr ON mr.id_menu = m.id
        INNER JOIN rol r ON r.id = mr.id_rol
        INNER JOIN usuario_rol ur ON ur.id_rol = r.id
        INNER JOIN usuario u ON u.id = ur.id_usuario
        WHERE u.correo = :correo
        """, nativeQuery = true)
    List<Menu> getMenusByCorreo(@Param("correo") String correo);

    @Query(value = """
        SELECT m.* 
        FROM menu m
        INNER JOIN menu_rol mr ON m.id = mr.id_menu
        WHERE mr.id_rol = ?1
        """, nativeQuery = true)
    List<Menu> findMenusByRol(Integer idRol);
}

