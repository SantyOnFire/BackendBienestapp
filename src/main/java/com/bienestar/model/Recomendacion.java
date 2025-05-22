package com.bienestar.model;

import jakarta.persistence.*;

@Entity
@Table(name = "recomendacion")
public class Recomendacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private String titulo;
    private String descripcion;

    @Column(name = "url_recurso")
    private String urlRecurso;

    @Column(name = "nivel_min")
    private Integer nivelMin;

    @Column(name = "nivel_max")
    private Integer nivelMax;

    // Getters y setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getUrlRecurso() { return urlRecurso; }
    public void setUrlRecurso(String urlRecurso) { this.urlRecurso = urlRecurso; }

    public Integer getNivelMin() { return nivelMin; }
    public void setNivelMin(Integer nivelMin) { this.nivelMin = nivelMin; }

    public Integer getNivelMax() { return nivelMax; }
    public void setNivelMax(Integer nivelMax) { this.nivelMax = nivelMax; }
}
