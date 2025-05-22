package com.bienestar.controller;

import com.bienestar.model.ResultadoTest;
import com.bienestar.service.ResultadoTestService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resultados")
@CrossOrigin(origins = "*")
public class ResultadoTestController {

    private final ResultadoTestService service;

    public ResultadoTestController(ResultadoTestService service) {
        this.service = service;
    }

    @GetMapping
    public List<ResultadoTest> listar() {
        return service.listar();
    }

    @PostMapping
    public ResultadoTest guardar(@RequestBody ResultadoTest resultado) {
        return service.guardar(resultado);
    }

    @GetMapping("/{id}")
    public ResultadoTest obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
