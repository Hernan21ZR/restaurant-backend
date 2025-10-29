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
    private int id;

    private int cantidad;
    private double subtotal;

    @ManyToOne
    @JoinColumn(name = "pedidoId", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLE_PEDIDO"))
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "productoId", nullable = false, foreignKey = @ForeignKey(name = "FK_DETALLE_PRODUCTO"))
    private Producto producto;
}
