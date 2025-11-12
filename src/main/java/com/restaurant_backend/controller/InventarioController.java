package com.restaurant_backend.controller;

import com.restaurant_backend.dto.InventarioDTO;
import com.restaurant_backend.model.Inventario;
import com.restaurant_backend.service.IInventarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/inventarios")
@RequiredArgsConstructor
public class InventarioController {

    private final IInventarioService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<InventarioDTO>> findAll() throws Exception {
        List<InventarioDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InventarioDTO> findById(@PathVariable("id") Integer id) throws Exception {
        InventarioDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody InventarioDTO dto) throws Exception {
        Inventario obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<InventarioDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody InventarioDTO dto) throws Exception {
        Inventario obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private InventarioDTO toDto(Inventario obj) { return mapper.map(obj, InventarioDTO.class); }

    private Inventario toEntity(InventarioDTO dto) { return mapper.map(dto, Inventario.class); }
}
