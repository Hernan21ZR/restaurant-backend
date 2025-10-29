package com.restaurant_backend.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ComprobanteDTO {
    private int id;

    @NotNull
    @Size(max = 30)
    private String tipo;

    @Size(max = 50)
    private String formato;

    @NotNull
    private int ventaId;
}
