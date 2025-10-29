package com.restaurant_backend.controller;

import java.net.URI;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.restaurant_backend.dto.MesaDTO;
import com.restaurant_backend.model.Mesa;
import com.restaurant_backend.service.IMesaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/mesas")
@RequiredArgsConstructor
public class MesaController {

    private final IMesaService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<MesaDTO>> findAll() throws Exception {
        List<MesaDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MesaDTO> findById(@PathVariable("id") Integer id) throws Exception {
        MesaDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MesaDTO dto) throws Exception {
        Mesa obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MesaDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody MesaDTO dto)
            throws Exception {
        Mesa obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private MesaDTO toDto(Mesa obj) {
        return mapper.map(obj, MesaDTO.class);
    }

    private Mesa toEntity(MesaDTO dto) {
        return mapper.map(dto, Mesa.class);
    }

}
