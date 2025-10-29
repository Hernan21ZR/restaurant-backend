package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoriaDTO {

    private Integer id;

    @NotNull
    @Size(min = 3, max = 100)
    private String nombre;
}