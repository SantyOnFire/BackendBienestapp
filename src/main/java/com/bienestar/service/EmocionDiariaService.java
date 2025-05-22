package com.bienestar.service;

import com.bienestar.model.EmocionDiaria;
import com.bienestar.repository.EmocionDiariaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmocionDiariaService {

    private final EmocionDiariaRepository repository;

    public EmocionDiariaService(EmocionDiariaRepository repository) {
        this.repository = repository;
    }

    public List<EmocionDiaria> listar() {
        return repository.findAll();
    }

    public EmocionDiaria guardar(EmocionDiaria emocion) {
        return repository.save(emocion);
    }

    public EmocionDiaria obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
