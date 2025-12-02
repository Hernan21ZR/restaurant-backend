package com.restaurant_backend.repository;

import com.restaurant_backend.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface IMenuRolRepository extends JpaRepository<Menu, Integer> {

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM menu_rol WHERE id_menu = ?1", nativeQuery = true)
    void deleteRolesByMenu(Integer idMenu);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO menu_rol(id_menu, id_rol) VALUES (?1, ?2)", nativeQuery = true)
    void asignarMenuRol(Integer idMenu, Integer idRol);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM menu_rol WHERE id_menu = ?1 AND id_rol = ?2", nativeQuery = true)
    void eliminarMenuRol(Integer idMenu, Integer idRol);

}

