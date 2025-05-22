package com.bienestar.repository;

import com.bienestar.model.ActividadSugerida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActividadSugeridaRepository extends JpaRepository<ActividadSugerida, Long> {

    List<ActividadSugerida> findByUsuarioId(Long usuarioId);

    List<ActividadSugerida> findByUsuarioIdAndCompletada(Long usuarioId, Boolean completada);
}
