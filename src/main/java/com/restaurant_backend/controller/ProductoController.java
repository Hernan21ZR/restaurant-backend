package com.restaurant_backend.controller;

import com.restaurant_backend.dto.ProductoDTO;
import com.restaurant_backend.model.Producto;
import com.restaurant_backend.service.IProductoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final IProductoService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> findAll() throws Exception {
        List<ProductoDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> findById(@PathVariable("id") Integer id) throws Exception {
        ProductoDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ProductoDTO dto) throws Exception {
        Producto obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ProductoDTO dto) throws Exception {
        Producto obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ProductoDTO toDto(Producto obj) {
        return mapper.map(obj, ProductoDTO.class);
    }

    private Producto toEntity(ProductoDTO dto) {
        return mapper.map(dto, Producto.class);
    }
}