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
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer id;

    @Column(length = 15)
    private String telefono;

    @Column(length = 255)
    private String direccion;

    @OneToOne
    @JoinColumn(name = "usuario_id", foreignKey = @ForeignKey(name = "FK_CLIENTE_USUARIO"))
    private Usuario usuario;
}
