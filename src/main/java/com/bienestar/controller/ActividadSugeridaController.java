package com.bienestar.controller;

import com.bienestar.model.ActividadSugerida;
import com.bienestar.service.ActividadSugeridaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/actividades")
@CrossOrigin(origins = "*")
public class ActividadSugeridaController {

    private final ActividadSugeridaService service;

    public ActividadSugeridaController(ActividadSugeridaService service) {
        this.service = service;
    }

    @GetMapping
    public List<ActividadSugerida> listar() {
        return service.listar();
    }

    @PostMapping
    public ActividadSugerida guardar(@RequestBody ActividadSugerida actividad) {
        return service.guardar(actividad);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActividadSugerida> obtener(@PathVariable Long id) {
        ActividadSugerida act = service.obtenerPorId(id);
        if (act == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(act);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<ActividadSugerida> listarPorUsuario(@PathVariable Long usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @GetMapping("/usuario/{usuarioId}/estado/{completada}")
    public List<ActividadSugerida> listarPorUsuarioYEstado(
            @PathVariable Long usuarioId,
            @PathVariable Boolean completada) {
        return service.listarPorUsuarioYEstado(usuarioId, completada);
    }
}
