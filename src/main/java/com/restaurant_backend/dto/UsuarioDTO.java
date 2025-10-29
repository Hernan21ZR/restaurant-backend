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
    private Integer id;

    @NotNull
    @Size(min = 3, max = 100)
    private String nombre;

    @Email
    @NotNull
    private String correo;

    @NotNull
    @Size(min = 6, max = 100)
    private String contrasena;

    @NotNull
    private RolDTO rol;
}