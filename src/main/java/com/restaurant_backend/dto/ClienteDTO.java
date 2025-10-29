package com.restaurant_backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ClienteDTO {
    private int id;

    @NotNull
    @Size(min = 3, max = 70)
    private String nombre;

    @Email
    @Size(max = 60)
    private String correo;

    @Pattern(regexp = "[0-9]+")
    @Size(max = 20)
    private String telefono;
}
