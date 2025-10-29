package com.restaurant_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MesaDTO {
    private Integer id;

    @NotNull
    private Integer numero;

    @NotNull
    private String estado;

    private String ubicacion;
}