package com.restaurant_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DetallePedidoDTO {
    private Integer id;
    private Integer cantidad;

    // Datos del producto para mostrar en tabla
    private Integer productoId;
    private String productoNombre;
    private Double productoPrecio;

    private Double subtotal;
}
