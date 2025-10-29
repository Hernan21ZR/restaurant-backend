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
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false, length = 50)
    private String metodo;

    @Column(nullable = false)
    private Double monto;

    private Date fechaPago;

    @ManyToOne
    @JoinColumn(name = "venta_id", nullable = false, foreignKey = @ForeignKey(name = "FK_PAGO_VENTA"))
    private Venta venta;
}