package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PagoDTO {
    private int id;

    @NotNull
    @Size(max = 30)
    private String metodo;

    @Positive
    private double monto;

    @NotNull
    private int ventaId;
}
