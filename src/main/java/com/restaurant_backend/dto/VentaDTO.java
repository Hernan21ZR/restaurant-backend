package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class VentaDTO {
    private int id;

    @NotNull
    private Date fecha;

    @PositiveOrZero
    private double total;

    @NotNull
    private int pedidoId;
}
