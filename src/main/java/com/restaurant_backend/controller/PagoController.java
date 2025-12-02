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
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<PagoDTO>> findAll() throws Exception {
        List<PagoDTO> list = service.findAll()
                .stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PagoDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    // 🔥 Nuevo: Registrar Pago
    @PostMapping("/registrar")
    public ResponseEntity<PagoDTO> registrarPago(@Valid @RequestBody PagoDTO dto) throws Exception {
        Pago pago = service.registrarPago(dto);
        return ResponseEntity.ok(toDto(pago));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PagoDTO dto) throws Exception {
        Pago obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PagoDTO> update(@PathVariable Integer id,
                                          @Valid @RequestBody PagoDTO dto) throws Exception {
        Pago obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    private PagoDTO toDto(Pago obj) { return mapper.map(obj, PagoDTO.class); }
    private Pago toEntity(PagoDTO dto) { return mapper.map(dto, Pago.class); }
}
