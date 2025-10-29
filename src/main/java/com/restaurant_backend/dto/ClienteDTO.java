package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {
    private Integer id;

    private String telefono;

    private String direccion;

    @NotNull
    private UsuarioDTO usuario;
}
