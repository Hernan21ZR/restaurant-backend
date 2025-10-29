package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
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