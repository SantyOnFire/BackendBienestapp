package com.bienestar.controller;

import com.bienestar.model.Recomendacion;
import com.bienestar.service.RecomendacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recomendaciones")
@CrossOrigin(origins = "*")
public class RecomendacionController {

    private final RecomendacionService service;

    public RecomendacionController(RecomendacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Recomendacion> listar() {
        return service.listar();
    }

    @PostMapping
    public Recomendacion guardar(@RequestBody Recomendacion recomendacion) {
        return service.guardar(recomendacion);
    }

    @GetMapping("/{id}")
    public Recomendacion obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
