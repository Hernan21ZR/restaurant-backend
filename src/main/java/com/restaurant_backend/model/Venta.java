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
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    private Date fecha;

    @Column(nullable = false)
    private Double total;

    @OneToOne
    @JoinColumn(name = "pedido_id", foreignKey = @ForeignKey(name = "FK_VENTA_PEDIDO"))
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "cliente_id", foreignKey = @ForeignKey(name = "FK_VENTA_CLIENTE"))
    private Cliente cliente;
}
