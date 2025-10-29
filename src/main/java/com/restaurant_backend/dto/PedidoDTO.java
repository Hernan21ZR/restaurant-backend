package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {
    private Integer id;

    @NotNull
    private Date fecha;

    @NotNull
    private String estado;

    @NotNull
    private ClienteDTO cliente;

    @NotNull
    private UsuarioDTO usuario;

    private MesaDTO mesa;
}
