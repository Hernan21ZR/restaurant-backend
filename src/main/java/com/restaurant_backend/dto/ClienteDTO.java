package com.restaurant_backend.dto;

import com.restaurant_backend.model.Cliente;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {

    private Integer id;

    private String telefono;

    private String direccion;

    @NotNull
    private UsuarioDTO usuario;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.telefono = cliente.getTelefono();
        this.direccion = cliente.getDireccion();
        this.usuario = new UsuarioDTO(cliente.getUsuario());
    }
}
