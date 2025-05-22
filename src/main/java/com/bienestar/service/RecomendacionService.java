package com.bienestar.service;

import com.bienestar.model.Recomendacion;
import com.bienestar.repository.RecomendacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecomendacionService {

    private final RecomendacionRepository repository;

    public RecomendacionService(RecomendacionRepository repository) {
        this.repository = repository;
    }

    public List<Recomendacion> listar() {
        return repository.findAll();
    }

    public Recomendacion guardar(Recomendacion recomendacion) {
        return repository.save(recomendacion);
    }

    public Recomendacion obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
