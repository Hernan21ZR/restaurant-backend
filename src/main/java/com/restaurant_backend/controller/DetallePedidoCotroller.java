package com.restaurant_backend.controller;

import com.restaurant_backend.dto.DetallePedidoDTO;
import com.restaurant_backend.model.DetallePedido;
import com.restaurant_backend.service.IDetallePedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/detallePedido")
@RequiredArgsConstructor
public class DetallePedidoCotroller {
    private final IDetallePedidoService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<DetallePedidoDTO>> findAll() throws Exception {
        List<DetallePedidoDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallePedidoDTO> findById(@PathVariable("id") Integer id) throws Exception {
        DetallePedidoDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody DetallePedidoDTO dto) throws Exception {
        DetallePedido obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetallePedidoDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody DetallePedidoDTO dto) throws Exception {
        DetallePedido obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private DetallePedidoDTO toDto(DetallePedido obj) { return mapper.map(obj, DetallePedidoDTO.class); }

    private DetallePedido toEntity(DetallePedidoDTO dto) { return mapper.map(dto, DetallePedido.class); }


}
