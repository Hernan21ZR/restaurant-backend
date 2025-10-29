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
public class Comprobante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false, length = 30)
    private String tipo;

    @Column(length = 50)
    private String formato;

    @OneToOne
    @JoinColumn(name = "ventaId", nullable = false, foreignKey = @ForeignKey(name = "FK_COMPROBANTE_VENTA"))
    private Venta venta;
}
