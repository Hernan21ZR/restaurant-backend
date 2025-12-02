package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoDTO {

    private Integer id;

    private Date fecha;

    private String estado;

    // Objetos completos para mostrar en tabla
    private ClienteDTO cliente;
    private UsuarioDTO usuario;
    private MesaDTO mesa;

    @NotNull(message = "Mesa obligatoria")
    private Integer mesaId;

    @NotNull(message = "Usuario (mesero) obligatorio")
    private Integer usuarioId;

    // Cliente puede ser NULL
    private Integer clienteId;

    @NotNull
    private List<DetallePedidoDTO> detalles;
}
