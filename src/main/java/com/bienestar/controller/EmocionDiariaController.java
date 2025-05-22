package com.bienestar.controller;

import com.bienestar.model.EmocionDiaria;
import com.bienestar.service.EmocionDiariaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emociones")
@CrossOrigin(origins = "*")
public class EmocionDiariaController {

    private final EmocionDiariaService service;

    public EmocionDiariaController(EmocionDiariaService service) {
        this.service = service;
    }

    @GetMapping
    public List<EmocionDiaria> listar() {
        return service.listar();
    }

    @PostMapping
    public EmocionDiaria guardar(@RequestBody EmocionDiaria emocion) {
        return service.guardar(emocion);
    }

    @GetMapping("/{id}")
    public EmocionDiaria obtener(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}
