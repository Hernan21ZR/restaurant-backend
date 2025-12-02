package com.restaurant_backend.dto;

import com.restaurant_backend.model.Usuario;
import jakarta.validation.constraints.*;
import lombok.*;
import java.util.List;
import java.util.stream.Collectors;

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
    private List<RolDTO> roles;

    public UsuarioDTO(Usuario usuario) {
        this.id = usuario.getId();
        this.nombre = usuario.getNombre();
        this.contrasena = usuario.getContrasena();
        this.correo = usuario.getCorreo();
        this.roles = usuario.getRoles().stream().map(r -> new RolDTO(r)).collect(Collectors.toList());
    }
}
