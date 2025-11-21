package com.bienestar.service;

import com.bienestar.model.EmocionDiaria;
import com.bienestar.model.Usuario;
import com.bienestar.repository.EmocionDiariaRepository;
import com.bienestar.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmocionDiariaService {

    private final EmocionDiariaRepository repository;
    private final AlertService alertService;
    private final UsuarioRepository usuarioRepository;

    // Lista de emociones que activan la alerta
    private static final List<String> EMOCIONES_NEGATIVAS = List.of(
            "tristeza", "ira", "ansiedad", "estres"
    );

    public EmocionDiariaService(
            EmocionDiariaRepository repository,
            AlertService alertService,
            UsuarioRepository usuarioRepository
    ) {
        this.repository = repository;
        this.alertService = alertService;
        this.usuarioRepository = usuarioRepository;
    }

    // Listar todas las emociones
    public List<EmocionDiaria> listar() {
        return repository.findAll();
    }

    // Guardar emoción + verificar alertas
    public EmocionDiaria guardar(EmocionDiaria emocion) {

        Long usuarioId = emocion.getUsuario().getId();
        LocalDate hoy = emocion.getFecha();

        // Obtener emociones del usuario hoy
        List<EmocionDiaria> emocionesHoy =
                repository.findByUsuarioIdAndFecha(usuarioId, hoy);

        // Contar cuántas emociones negativas hay hoy
        long negativas = emocionesHoy.stream()
                .filter(e -> EMOCIONES_NEGATIVAS.contains(e.getEmocion().toLowerCase()))
                .count();

        // Si llega a 3 → enviar alerta a Teams
        if (negativas >= 3) {

            Usuario user = usuarioRepository.findById(usuarioId).orElse(null);
            String nombreUsuario = (user != null) ? user.getNombre() : ("ID " + usuarioId);

            String mensaje =
                    "⚠️ **Alerta emocional** ⚠️\n\n" +
                            "El usuario **" + nombreUsuario + "** ha registrado **" + negativas +
                            " emociones negativas** hoy.\n" +
                            "Se recomienda hacer seguimiento a su bienestar emocional.";

            alertService.enviarAlerta(mensaje);
        }

        return repository.save(emocion);
    }

    // Obtener por ID
    public EmocionDiaria obtenerPorId(Long id) {
        return repository.findById(id).orElse(null);
    }

    // Eliminar emoción
    public void eliminar(Long id) {
        repository.deleteById(id);
    }
}
