package com.bienestar.service;

import com.bienestar.model.ResultadoTest;
import com.bienestar.repository.ResultadoTestRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultadoTestService {

    private final ResultadoTestRepository repository;

    public ResultadoTestService(ResultadoTestRepository repository) {
        this.repository = repository;
    }

    public List<ResultadoTest> listar() {
        return repository.findAll();
    }

    public ResultadoTest guardar(ResultadoTest resultado) {
        return repository.save(resultado);
    }

    public ResultadoTest obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
