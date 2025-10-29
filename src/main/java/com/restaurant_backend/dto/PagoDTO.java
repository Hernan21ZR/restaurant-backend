package com.restaurant_backend.dto;

import com.mysql.cj.conf.PropertyDefinitions;
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
    private Integer id;

    @NotNull
    private String metodo;

    @NotNull
    private Double monto;

    @NotNull
    private PropertyDefinitions.DatabaseTerm fechaPago;

    @NotNull
    private VentaDTO venta;
}
