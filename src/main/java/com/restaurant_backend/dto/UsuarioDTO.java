package com.restaurant_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UsuarioDTO {
    private int id;

    @NotNull
    @Size(min = 3, max = 70)
    private String nombre;

    @NotNull
    @Email
    @Size(max = 60)
    private String correo;

    @NotNull
    @Size(min = 6, max = 100)
    private String contrasena;

    @NotNull
    private int roleId;

    private Integer clienteId;
}
