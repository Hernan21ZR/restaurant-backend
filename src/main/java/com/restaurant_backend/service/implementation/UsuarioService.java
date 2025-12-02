package com.restaurant_backend.service.implementation;

import com.restaurant_backend.model.Rol;
import com.restaurant_backend.model.Usuario;
import com.restaurant_backend.repository.IGenericRepository;
import com.restaurant_backend.repository.IRolRepository;
import com.restaurant_backend.repository.IUsuarioRepository;
import com.restaurant_backend.service.IUsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService extends GenericService<Usuario, Integer> implements IUsuarioService {

    private final IUsuarioRepository repo;
    private final PasswordEncoder passwordEncoder;
    private final IRolRepository rolRepo;

    @Override
    protected IGenericRepository<Usuario, Integer> getRepo() {
        return repo;
    }

    // -----------------------------
    // CREATE
    // -----------------------------
    @Override
    public Usuario save(Usuario usuario) throws Exception {

        // Resolver roles desde la BD para que JPA los gestione correctamente
        List<Rol> resolved = usuario.getRoles().stream()
                .map(r -> rolRepo.findById(r.getId())
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + r.getId())))
                .toList();

        usuario.setRoles(resolved);

        // Encriptar contraseña (aquí siempre viene en texto plano)
        usuario.setContrasena(passwordEncoder.encode(usuario.getContrasena()));

        return repo.save(usuario);
    }

    // -----------------------------
    // UPDATE
    // -----------------------------
    @Override
    public Usuario update(Usuario usuario, Integer id) throws Exception {
        Usuario existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        // Manejo seguro de contraseña
        if (usuario.getContrasena() != null && !usuario.getContrasena().isBlank()) {

            String raw = usuario.getContrasena();

            // Evitar doble hash
            if (raw.startsWith("$2")) {
                // Ya parece hash -> mantener (pero realmente no debería venir del frontend)
                existing.setContrasena(raw);
            } else {
                // Nueva contraseña
                existing.setContrasena(passwordEncoder.encode(raw));
            }
        }
        // Si contrasena viene vacía -> no tocarla

        // Actualizar datos generales
        existing.setNombre(usuario.getNombre());
        existing.setCorreo(usuario.getCorreo());

        // Resolver roles también en UPDATE (MUY IMPORTANTE)
        if (usuario.getRoles() != null) {
            List<Rol> resolved = usuario.getRoles().stream()
                    .map(r -> rolRepo.findById(r.getId())
                            .orElseThrow(() -> new RuntimeException("Rol no encontrado: " + r.getId())))
                    .toList();

            existing.setRoles(resolved);
        }

        return repo.save(existing);
    }
}
