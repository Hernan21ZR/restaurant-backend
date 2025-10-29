package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComprobanteDTO {
    private Integer id;

    @NotNull
    private String tipo;

    @NotNull
    private String formato;

    @NotNull
    private String numero;

    @NotNull
    private VentaDTO venta;
}
