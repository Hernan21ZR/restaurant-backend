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
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaHora;

    @Column(length = 30)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false, foreignKey = @ForeignKey(name = "FK_RESERVA_CLIENTE"))
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "mesaId", nullable = false, foreignKey = @ForeignKey(name = "FK_RESERVA_MESA"))
    private Mesa mesa;
}