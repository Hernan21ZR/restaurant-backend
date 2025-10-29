package com.restaurant_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetallePedidoDTO {
    private int id;

    @Min(1)
    private int cantidad;

    @PositiveOrZero
    private double subtotal;

    @NotNull
    private int pedidoId;

    @NotNull
    private int productoId;
}
