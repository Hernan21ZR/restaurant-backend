package com.restaurant_backend.controller;

import com.restaurant_backend.model.Menu;
import com.restaurant_backend.service.implementation.MenuRolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu-roles")
@RequiredArgsConstructor
public class MenuRolController {

    private final MenuRolService service;

    @GetMapping("/{idRol}")
    public ResponseEntity<List<Menu>> listarMenusPorRol(@PathVariable Integer idRol) {
        return ResponseEntity.ok(service.listarMenusPorRol(idRol));
    }

    @PostMapping
    public ResponseEntity<Void> asignar(@RequestBody Map<String, Integer> body) {
        service.asignarMenu(body.get("idMenu"), body.get("idRol"));
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{idMenu}/{idRol}")
    public ResponseEntity<Void> eliminar(@PathVariable Integer idMenu, @PathVariable Integer idRol) {
        service.eliminarMenu(idMenu, idRol);
        return ResponseEntity.noContent().build();
    }
}
