package com.restaurant_backend.controller;

import com.restaurant_backend.dto.CategoriaDTO;
import com.restaurant_backend.model.Categoria;
import com.restaurant_backend.service.ICategoriaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final ICategoriaService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() throws Exception {
        List<CategoriaDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDTO> findById(@PathVariable("id") Integer id) throws Exception {
        CategoriaDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody CategoriaDTO dto) throws Exception {
        Categoria obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody CategoriaDTO dto) throws Exception {
        Categoria obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private CategoriaDTO toDto(Categoria obj) { return mapper.map(obj, CategoriaDTO.class); }

    private Categoria toEntity(CategoriaDTO dto) { return mapper.map(dto, Categoria.class); }
}
