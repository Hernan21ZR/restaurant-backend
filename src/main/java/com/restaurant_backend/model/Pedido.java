package com.restaurant_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    @Column(length = 30)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false, foreignKey = @ForeignKey(name = "FK_PEDIDO_CLIENTE"))
    private Cliente cliente;

    @OneToMany(mappedBy = "pedido")
    private List<DetallePedido> detalles;

    @OneToOne(mappedBy = "pedido")
    private Venta venta;
}