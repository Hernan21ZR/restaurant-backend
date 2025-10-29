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
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(nullable = false, length = 70)
    private String nombre;

    @Column(nullable = false, length = 60, unique = true)
    private String correo;

    @Column(nullable = false, length = 100)
    private String contrasena;

    @ManyToOne
    @JoinColumn(name = "rolId", nullable = false, foreignKey = @ForeignKey(name = "FK_USUARIO_ROLE"))
    private Rol rol;

    @ManyToOne
    @JoinColumn(name = "clienteId", foreignKey = @ForeignKey(name = "FK_USUARIO_CLIENTE"))
    private Cliente cliente;
}