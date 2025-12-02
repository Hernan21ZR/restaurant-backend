package com.restaurant_backend.repository;

import com.restaurant_backend.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuarioRepository extends IGenericRepository<Usuario, Integer> {

    Usuario findOneByCorreo(String correo);
}
