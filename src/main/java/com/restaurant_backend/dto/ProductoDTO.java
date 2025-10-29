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
public class ProductoDTO {
    private int id;

    @NotNull
    @Size(min = 3, max = 100)
    private String nombre;

    @PositiveOrZero
    private double precio;

    private Integer categoriaId;
    private Integer inventarioId;
}
