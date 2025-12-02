package com.restaurant_backend.controller;

import com.restaurant_backend.dto.*;
import com.restaurant_backend.model.Pedido;
import com.restaurant_backend.service.IPedidoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/pedidos")
@RequiredArgsConstructor
public class PedidoController {

    private final IPedidoService service;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> findAll() throws Exception {
        List<PedidoDTO> list = service.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> findById(@PathVariable("id") Integer id) throws Exception {
        Pedido pedido = service.findById(id);
        return ResponseEntity.ok(toDTO(pedido));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody PedidoDTO dto) throws Exception {
        Pedido obj = service.saveFromDto(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> update(@PathVariable("id") Integer id,
                                            @Valid @RequestBody PedidoDTO dto) throws Exception {
        Pedido obj = service.updateFromDto(dto, id);
        return ResponseEntity.ok(toDTO(obj));
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<PedidoDTO> cambiarEstado(@PathVariable Integer id,
                                                   @RequestBody Map<String, String> body) throws Exception {
        String estado = body.get("estado");
        Pedido pedido = service.updateEstado(id, estado);
        return ResponseEntity.ok(toDTO(pedido));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // -------------------
// Mapeo Pedido -> PedidoDTO
// -------------------
    private PedidoDTO toDTO(Pedido pedido) {
        List<DetallePedidoDTO> detallesDto = pedido.getDetalles().stream().map(det -> {
            DetallePedidoDTO d = new DetallePedidoDTO();
            d.setId(det.getId());
            d.setCantidad(det.getCantidad());
            d.setProductoId(det.getProducto().getId());
            d.setProductoNombre(det.getProducto().getNombre());
            d.setProductoPrecio(det.getProducto().getPrecio());
            d.setSubtotal(det.getSubtotal());
            return d;
        }).collect(Collectors.toList());

        PedidoDTO dto = new PedidoDTO();
        dto.setId(pedido.getId());
        dto.setFecha(pedido.getFecha());
        dto.setEstado(pedido.getEstado());
        dto.setMesa(new MesaDTO(pedido.getMesa()));      // Asegúrate que MesaDTO tenga constructor que acepte Mesa
        dto.setUsuario(new UsuarioDTO(pedido.getUsuario())); // UsuarioDTO constructor con Usuario
        dto.setCliente(pedido.getCliente() != null ? new ClienteDTO(pedido.getCliente()) : null);
        dto.setDetalles(detallesDto);

        return dto;
    }

}
