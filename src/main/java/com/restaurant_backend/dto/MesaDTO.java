package com.restaurant_backend.dto;

import com.restaurant_backend.model.Mesa;
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

    public MesaDTO(Mesa mesa) {
        this.id = mesa.getId();
        this.numero = mesa.getNumero();
        this.estado = mesa.getEstado();
        this.ubicacion = mesa.getUbicacion();
    }
}