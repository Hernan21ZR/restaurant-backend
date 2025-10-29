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
public class DetallePedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "pedido_id", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLE_PEDIDO_PEDIDO"))
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLE_PEDIDO_PRODUCTO"))
    private Producto producto;
}
