package com.restaurant_backend.controller;

import com.restaurant_backend.dto.VentaDTO;
import com.restaurant_backend.model.Venta;
import com.restaurant_backend.service.IVentaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final IVentaService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> findAll() throws Exception {
        List<VentaDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> findById(@PathVariable("id") Integer id) throws Exception {
        VentaDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody VentaDTO dto) throws Exception {
        Venta obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<VentaDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody VentaDTO dto) throws Exception {
        Venta obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private VentaDTO toDto(Venta obj) {
        return modelMapper.map(obj, VentaDTO.class);
    }

    private Venta toEntity(VentaDTO dto) {
        return modelMapper.map(dto, Venta.class);
    }
}
