package com.restaurant_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(length = 30)
    private String metodo;

    private double monto;

    @ManyToOne
    @JoinColumn(name = "ventaId", nullable = false, foreignKey = @ForeignKey(name = "FK_PAGO_VENTA"))
    private Venta venta;
}
