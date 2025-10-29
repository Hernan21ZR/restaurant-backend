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
    private int id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;

    private double total;

    @OneToOne
    @JoinColumn(name = "pedidoId", nullable = false, foreignKey = @ForeignKey(name = "FK_VENTA_PEDIDO"))
    private Pedido pedido;

    @OneToMany(mappedBy = "venta")
    private List<Pago> pagos;

    @OneToOne(mappedBy = "venta")
    private Comprobante comprobante;
}
