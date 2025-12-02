package com.restaurant_backend.controller;

import com.restaurant_backend.dto.AsignarMenuRolDTO;
import com.restaurant_backend.dto.CorreoDTO;
import com.restaurant_backend.dto.MenuDTO;
import com.restaurant_backend.model.Menu;
import com.restaurant_backend.service.IMenuService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;


@RestController
@RequestMapping("/menus")
@RequiredArgsConstructor
public class MenuController {

    private final IMenuService service;
    private final ModelMapper mapper;
    private static final Logger LOG = LoggerFactory.getLogger(MenuController.class);

    @GetMapping
    public ResponseEntity<List<MenuDTO>> findAll() throws Exception {
        List<MenuDTO> list = service.findAll().stream().map(this::toDto).toList();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDTO> findById(@PathVariable Integer id) throws Exception {
        return ResponseEntity.ok(toDto(service.findById(id)));
    }

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MenuDTO dto) throws Exception {
        Menu obj = service.save(toEntity(dto));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MenuDTO> update(
            @PathVariable Integer id,
            @Valid @RequestBody MenuDTO dto
    ) throws Exception {

        Menu obj = service.update(toEntity(dto), id);
        return ResponseEntity.ok(toDto(obj));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) throws Exception {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user")
    public ResponseEntity<List<MenuDTO>> getMenusByUser(@RequestBody CorreoDTO body) {
        String correo = (body == null || body.getCorreo() == null) ? "" : body.getCorreo().trim();
        LOG.info("getMenusByUser called for correo={}", correo);
        List<MenuDTO> list = service.getMenusByCorreo(correo)
                .stream().map(this::toDto).toList();

        return ResponseEntity.ok(list);
    }

    @PostMapping("/asignar")
    public ResponseEntity<Void> asignarRoles(@RequestBody AsignarMenuRolDTO dto) throws Exception {
        service.asignarRoles(dto.getIdMenu(), dto.getIdRoles());
        return ResponseEntity.ok().build();
    }

    private MenuDTO toDto(Menu obj) { return mapper.map(obj, MenuDTO.class); }
    private Menu toEntity(MenuDTO dto) { return mapper.map(dto, Menu.class); }
}
