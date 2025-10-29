package com.restaurant_backend.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false, length = 100)
    private String nombre;

    private double precio;

    @ManyToOne
    @JoinColumn(name = "categoriaId", foreignKey = @ForeignKey(name = "FK_PRODUCTO_CATEGORIA"))
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "inventarioId", foreignKey = @ForeignKey(name = "FK_PRODUCTO_INVENTARIO"))
    private Inventario inventario;

    @OneToMany(mappedBy = "producto")
    private List<DetallePedido> detalles;
}
