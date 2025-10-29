package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservaDTO {
    private Integer id;

    @NotNull
    private Date fechaHora;

    @NotNull
    private String estado;

    @NotNull
    private Integer numeroPersonas;

    private String observaciones;

    @NotNull
    private ClienteDTO cliente;

    @NotNull
    private MesaDTO mesa;
}
