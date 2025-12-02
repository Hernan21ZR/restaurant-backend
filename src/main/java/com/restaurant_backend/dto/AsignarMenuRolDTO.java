package com.restaurant_backend.dto;

import lombok.Data;

import java.util.List;

@Data
public class AsignarMenuRolDTO {
    private Integer idMenu;
    private List<Integer> idRoles;
}
