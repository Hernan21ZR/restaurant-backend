package com.restaurant_backend.controller;

import com.restaurant_backend.dto.VentaDTO;
import com.restaurant_backend.model.Pedido;
import com.restaurant_backend.model.Venta;
import com.restaurant_backend.repository.IPedidoRepository;
import com.restaurant_backend.service.IVentaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
@RequiredArgsConstructor
public class VentaController {

    private final IVentaService service;
    private final IPedidoRepository pedidoRepo;
    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<List<VentaDTO>> findAll() throws Exception {
        List<VentaDTO> list = service.findAll()
                .stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    // 🔥 SOLO PEDIDOS LISTO O ENTREGADO
    @GetMapping("/pedidos-disponibles")
    public ResponseEntity<List<Pedido>> pedidosDisponiblesParaVenta() {
        List<Pedido> pedidos = pedidoRepo.findAll().stream()
                .filter(p -> "LISTO".equalsIgnoreCase(p.getEstado()) ||
                        "ENTREGADO".equalsIgnoreCase(p.getEstado()))
                .toList();

        return ResponseEntity.ok(pedidos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VentaDTO> findById(@PathVariable Integer id) throws Exception {
        VentaDTO dto = toDto(service.findById(id));
        return ResponseEntity.ok(dto);
    }

    // 🔥 GENERAR VENTA
    @PostMapping("/generar/{pedidoId}")
    public ResponseEntity<VentaDTO> generarVenta(@PathVariable Integer pedidoId) throws Exception {
        Venta venta = service.generarVenta(pedidoId);
        return ResponseEntity.ok(toDto(venta));
    }

    private VentaDTO toDto(Venta obj) {
        VentaDTO dto = new VentaDTO();
        dto.setId(obj.getId());
        dto.setFecha(obj.getFecha());
        dto.setTotal(obj.getTotal());
        dto.setPedidoId(obj.getPedido().getId());
        dto.setClienteId(obj.getCliente().getId());
        return dto;
    }
}
