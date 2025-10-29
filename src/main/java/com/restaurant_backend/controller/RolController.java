package com.restaurant_backend.controller;

import com.restaurant_backend.dto.RolDTO;
import com.restaurant_backend.model.Rol;
import com.restaurant_backend.service.IRolService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RolController {

    private final IRolService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<RolDTO>> findAll() throws Exception {
        List<RolDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RolDTO> findById(@PathVariable("id") Integer id) throws Exception {
        RolDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody RolDTO dto) throws Exception {
        Rol obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<RolDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody RolDTO dto) throws Exception {
        Rol obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private RolDTO toDto(Rol obj) { return modelMapper.map(obj, RolDTO.class); }

    private Rol toEntity(RolDTO dto) { return modelMapper.map(dto, Rol.class); }
}
