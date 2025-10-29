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
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre; // Ej: Inventario de Bebidas, Inventario de Postres

    @Column(nullable = false)
    private Integer stockTotal; // Suma de los stocks de sus productos

    @Column(nullable = false, length = 50)
    private String unidadMedida; // unidades, litros, kg, etc.

    @Column(nullable = false)
    private Integer minimoStock; // Nivel mínimo para alertas

    @OneToMany(mappedBy = "inventario")
    private List<Producto> productos; // Relación inversa (1 Inventario → N Productos)
}
