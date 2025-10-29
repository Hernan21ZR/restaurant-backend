package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class InventarioDTO {
    private int id;

    @NotNull
    @Size(min = 3, max = 70)
    private String nombre;

    @PositiveOrZero
    private int stock;

    @Size(max = 30)
    private String unidadMedida;
}
