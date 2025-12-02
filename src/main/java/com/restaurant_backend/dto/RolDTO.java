package com.restaurant_backend.dto;

import com.restaurant_backend.model.Rol;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RolDTO {
    private Integer id;

    @NotNull
    private String nombre;

    public RolDTO(Rol rol) {
        this.id = rol.getId();
        this.nombre = rol.getNombre();
    }
}
