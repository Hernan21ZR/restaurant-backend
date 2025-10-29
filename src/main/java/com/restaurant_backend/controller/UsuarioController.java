package com.restaurant_backend.controller;

import com.restaurant_backend.dto.UsuarioDTO;
import com.restaurant_backend.model.Usuario;
import com.restaurant_backend.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final IUsuarioService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> findAll() throws Exception {
        List<UsuarioDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> findById(@PathVariable("id") Integer id) throws Exception {
        UsuarioDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody UsuarioDTO dto) throws Exception {
        Usuario obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody UsuarioDTO dto) throws Exception {
        Usuario obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private UsuarioDTO toDto(Usuario obj) {
        return modelMapper.map(obj, UsuarioDTO.class);
    }

    private Usuario toEntity(UsuarioDTO dto) {
        return modelMapper.map(dto, Usuario.class);
    }
}
