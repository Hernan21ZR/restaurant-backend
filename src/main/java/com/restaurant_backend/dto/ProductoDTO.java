package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Integer id;

    @NotNull
    @Size(min = 3, max = 100)
    private String nombre;

    private String descripcion;

    @NotNull
    @Positive
    private Double precio;

    @NotNull
    @PositiveOrZero
    private Integer stockActual;

    @NotNull
    private Integer categoriaId;
}
