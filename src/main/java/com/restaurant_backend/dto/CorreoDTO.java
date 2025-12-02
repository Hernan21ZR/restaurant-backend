
package com.restaurant_backend.dto;

public class CorreoDTO {
    private String correo;

    public CorreoDTO() {}

    public CorreoDTO(String correo) { this.correo = correo; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }
}
