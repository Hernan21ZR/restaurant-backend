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

    private Integer id;

    @NotNull
    @Size(min = 3, max = 100)
    private String nombre;

    @NotNull
    @PositiveOrZero(message = "El stock total no puede ser negativo.")
    private Integer stockTotal;

    @NotNull
    @Size(min = 1, max = 50)
    private String unidadMedida;

    @NotNull
    @PositiveOrZero(message = "El stock mínimo no puede ser negativo.")
    private Integer minimoStock;
}