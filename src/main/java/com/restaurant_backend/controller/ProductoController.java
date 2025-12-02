package com.restaurant_backend.controller;

import java.net.URI;
import java.util.List;

import com.restaurant_backend.model.Categoria;
import com.restaurant_backend.model.Producto;
import com.restaurant_backend.service.IProductoService;
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

import com.restaurant_backend.dto.ProductoDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> update(@PathVariable("id") Integer id, @Valid @RequestBody ProductoDTO dto)
            throws Exception {
        Producto obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ProductoDTO toDto(Producto obj) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(obj.getId());
        dto.setNombre(obj.getNombre());
        dto.setDescripcion(obj.getDescripcion());
        dto.setPrecio(obj.getPrecio());
        dto.setStockActual(obj.getStockActual());
        dto.setCategoriaId(obj.getCategoria().getId());
        return dto;
    }


    private Producto toEntity(ProductoDTO dto) {
        Producto obj = new Producto();
        obj.setId(dto.getId());
        obj.setNombre(dto.getNombre());
        obj.setDescripcion(dto.getDescripcion());
        obj.setPrecio(dto.getPrecio());
        obj.setStockActual(dto.getStockActual());

        Categoria cat = new Categoria();
        cat.setId(dto.getCategoriaId());
        obj.setCategoria(cat);

        return obj;
    }

}
