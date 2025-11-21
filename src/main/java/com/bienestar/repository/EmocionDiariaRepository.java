package com.bienestar.repository;

import com.bienestar.model.EmocionDiaria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface EmocionDiariaRepository extends JpaRepository<EmocionDiaria, Long> {

    List<EmocionDiaria> findByUsuarioIdAndFecha(Long usuarioId, LocalDate fecha);
}
