package com.bienestar.service;

import com.bienestar.model.ActividadSugerida;
import com.bienestar.repository.ActividadSugeridaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActividadSugeridaService {

    private final ActividadSugeridaRepository repository;

    public ActividadSugeridaService(ActividadSugeridaRepository repository) {
        this.repository = repository;
    }

    public List<ActividadSugerida> listar() {
        return repository.findAll();
    }

    public ActividadSugerida guardar(ActividadSugerida actividad) {
        return repository.save(actividad);
    }

    public ActividadSugerida obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public List<ActividadSugerida> listarPorUsuario(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId);
    }

    public List<ActividadSugerida> listarPorUsuarioYEstado(Long usuarioId, Boolean completada) {
        return repository.findByUsuarioIdAndCompletada(usuarioId, completada);
    }
}
