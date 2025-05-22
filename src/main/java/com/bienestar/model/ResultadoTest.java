package com.bienestar.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "resultado_test")
public class ResultadoTest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDate fecha;

    @Column(name = "puntaje_estres")
    private Integer puntajeEstres;

    @Column(name = "puntaje_ansiedad")
    private Integer puntajeAnsiedad;

    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public Integer getPuntajeEstres() { return puntajeEstres; }
    public void setPuntajeEstres(Integer puntajeEstres) { this.puntajeEstres = puntajeEstres; }

    public Integer getPuntajeAnsiedad() { return puntajeAnsiedad; }
    public void setPuntajeAnsiedad(Integer puntajeAnsiedad) { this.puntajeAnsiedad = puntajeAnsiedad; }
}
