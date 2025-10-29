package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VentaDTO {
    private Integer id;

    @NotNull
    private Date fecha;

    @NotNull
    private Double total;

    private PedidoDTO pedido;

    private ClienteDTO cliente;
}