package com.restaurant_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Reserva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private Date fechaHora;
    private String estado;
    private Integer numeroPersonas;
    private String observaciones;

    @ManyToOne
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "FK_RESERVA_CLIENTE"))
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mesa_id", foreignKey = @ForeignKey(name = "FK_RESERVA_MESA"))
    private Mesa mesa;
}