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
    private Integer id;

    @Column(nullable = false, length = 50)
    private String tipo; // Boleta / Factura

    @Column(nullable = false, length = 50)
    private String formato; // Físico / Electrónico

    @Column(nullable = false, unique = true, length = 50)
    private String numero;

    @OneToOne
    @JoinColumn(name = "venta_id", nullable = false, foreignKey = @ForeignKey(name = "FK_COMPROBANTE_VENTA"))
    private Venta venta;
}
