package com.restaurant_backend.controller;

import com.restaurant_backend.dto.ComprobanteDTO;
import com.restaurant_backend.model.Comprobante;
import com.restaurant_backend.service.IComprobanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/comprobantes")
@RequiredArgsConstructor
public class ComprobanteController {

    private final IComprobanteService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ComprobanteDTO>> findAll() throws Exception {
        List<ComprobanteDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComprobanteDTO> findById(@PathVariable("id") Integer id) throws Exception {
        ComprobanteDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ComprobanteDTO dto) throws Exception {
        Comprobante obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComprobanteDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ComprobanteDTO dto) throws Exception {
        Comprobante obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ComprobanteDTO toDto(Comprobante obj) {
        return mapper.map(obj, ComprobanteDTO.class);
    }

    private Comprobante toEntity(ComprobanteDTO dto) {
        return mapper.map(dto, Comprobante.class);
    }
}