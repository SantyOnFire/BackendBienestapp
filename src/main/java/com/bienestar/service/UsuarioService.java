package com.bienestar.service;

import com.bienestar.model.Usuario;
import com.bienestar.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public List<Usuario> listarUsuarios() {
        return repository.findAll();
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return repository.save(usuario);
    }

    public Usuario obtenerUsuarioPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminarUsuario(Long id) {
        repository.deleteById(id);
    }

    public Usuario obtenerPorEmail(String email) {
        return repository.findByEmail(email);
    }
}
