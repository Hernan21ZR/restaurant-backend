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
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false)
    private Double precio;

    @Column(nullable = false)
    private Integer stockActual; // stock individual del producto

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_PRODUCTO_CATEGORIA"))
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "inventario_id", nullable = false,
            foreignKey = @ForeignKey(name = "FK_PRODUCTO_INVENTARIO"))
    private Inventario inventario;
}
