package com.restaurant_backend.controller;

import com.restaurant_backend.dto.PedidoDTO;
import com.restaurant_backend.model.Pedido;
import com.restaurant_backend.service.IPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor

public class PedidoController {
    private final IPedidoService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll() throws Exception {
        List<PedidoDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable("id") Integer id) throws Exception {
        PedidoDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PedidoDTO dto) throws Exception {
        Pedido obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody PedidoDTO dto) throws Exception {
        Pedido obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private PedidoDTO toDto(Pedido obj) { return mapper.map(obj, PedidoDTO.class); }

    private Pedido toEntity(PedidoDTO dto) { return mapper.map(dto, Pedido.class); }

}
