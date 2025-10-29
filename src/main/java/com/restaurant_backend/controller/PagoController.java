package com.restaurant_backend.controller;
import com.restaurant_backend.dto.PagoDTO;
import com.restaurant_backend.model.Pago;
import com.restaurant_backend.service.IPagoService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pagos")
@RequiredArgsConstructor
public class PagoController {

    private final IPagoService service;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> findAll() throws Exception {
        List<PagoDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> findById(@PathVariable("id") Integer id) throws Exception {
        PagoDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PagoDTO dto) throws Exception {
        Pago obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody PagoDTO dto) throws Exception {
        Pago obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private PagoDTO toDto(Pago obj) {
        return modelMapper.map(obj, PagoDTO.class);
    }

    private Pago toEntity(PagoDTO dto) {
        return modelMapper.map(dto, Pago.class);
    }
}
