package com.PPAIEnJava.NuevoPPAI.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Bodegas")
@Getter
@Setter
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long ID;

    private String HISTORIA;

    private String NOMBRE;

    private String DESCRIPCION;

    private Integer PERIDO_ACTUALIZACION;

    private LocalDateTime FECHA_ULTIMA_ACTUALIZACION;

    private int COORDENADAS;

    public Bodega(long ID, String HISTORIA, String NOMBRE, String DESCRIPCION, Integer PERIDO_ACTUALIZACION, LocalDateTime FECHA_ULTIMA_ACTUALIZACION, int COORDENADAS) {
        this.ID = ID;
        this.HISTORIA = HISTORIA;
        this.NOMBRE = NOMBRE;
        this.DESCRIPCION = DESCRIPCION;
        this.PERIDO_ACTUALIZACION = PERIDO_ACTUALIZACION;
        this.FECHA_ULTIMA_ACTUALIZACION = FECHA_ULTIMA_ACTUALIZACION;
        this.COORDENADAS = COORDENADAS;
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "ID=" + ID +
                ", HISTORIA='" + HISTORIA + '\'' +
                ", NOMBRE='" + NOMBRE + '\'' +
                ", DESCRIPCION='" + DESCRIPCION + '\'' +
                ", PERIDO_ACTUALIZACION=" + PERIDO_ACTUALIZACION +
                ", FECHA_ULTIMA_ACTUALIZACION=" + FECHA_ULTIMA_ACTUALIZACION +
                ", COORDENADAS=" + COORDENADAS +
                '}';
    }

    public Bodega() {

    }
}
