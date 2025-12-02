package com.restaurant_backend.dto;

import com.mysql.cj.conf.PropertyDefinitions;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagoDTO {

    private Integer id;

    @NotNull
    private String metodo;

    @NotNull
    private Double monto;

    private Date fechaPago;

    @NotNull
    private Integer ventaId;
}
