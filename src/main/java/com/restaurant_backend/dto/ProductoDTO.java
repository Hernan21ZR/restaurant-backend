package com.restaurant_backend.dto;

import com.restaurant_backend.model.Categoria;
import com.restaurant_backend.model.Inventario;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductoDTO {

    private Integer id;

    @NotNull
    @Size(min = 3, max = 100, message = "El nombre del producto debe tener entre 3 y 100 caracteres.")
    private String nombre;

    @Size(max = 500, message = "La descripción no debe superar los 500 caracteres.")
    private String descripcion;

    @NotNull
    @Positive(message = "El precio debe ser mayor a 0.")
    private Double precio;

    @NotNull
    @PositiveOrZero(message = "El stock actual no puede ser negativo.")
    private Integer stockActual;

    @NotNull(message = "Debe especificar la categoría del producto.")
    private Categoria categoria;

    @NotNull(message = "Debe especificar el inventario al que pertenece el producto.")
    private Inventario inventario;
}