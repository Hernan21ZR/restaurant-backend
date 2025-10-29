package com.restaurant_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MesaDTO {
    private int id;

    @Min(1)
    private int numero;

    @Size(max = 30)
    private String estado;
}
