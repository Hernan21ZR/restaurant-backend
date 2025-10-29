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
public class PedidoDTO {
    private int id;

    @NotNull
    private Date fecha;

    @NotNull
    @Size(max = 30)
    private String estado;

    @NotNull
    private int clienteId;
}
