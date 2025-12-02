package com.restaurant_backend.repository;

import com.restaurant_backend.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRolRepository extends IGenericRepository<Rol, Integer> {

    Rol findByNombre(String nombre);
}
