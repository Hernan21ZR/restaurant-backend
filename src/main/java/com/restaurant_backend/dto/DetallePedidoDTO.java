package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetallePedidoDTO {
    private Integer id;

    @NotNull
    private Integer cantidad;

    @NotNull
    private Double subtotal;

    @NotNull
    private PedidoDTO pedido;

    @NotNull
    private ProductoDTO producto;
}