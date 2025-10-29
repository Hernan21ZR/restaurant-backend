package com.restaurant_backend.controller;

import com.restaurant_backend.dto.ClienteDTO;
import com.restaurant_backend.model.Cliente;
import com.restaurant_backend.service.IClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final IClienteService service;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() throws Exception {
        List<ClienteDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable("id") Integer id) throws Exception {
        ClienteDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody ClienteDTO dto) throws Exception {
        Cliente obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDTO> update(@Valid @PathVariable("id") Integer id, @RequestBody ClienteDTO dto) throws Exception {
        Cliente obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ClienteDTO toDto(Cliente obj) { return mapper.map(obj, ClienteDTO.class); }

    private Cliente toEntity(ClienteDTO dto) { return mapper.map(dto, Cliente.class); }
}
