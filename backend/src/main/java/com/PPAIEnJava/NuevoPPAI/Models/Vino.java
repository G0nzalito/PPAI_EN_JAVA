package com.PPAIEnJava.NuevoPPAI.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Vinos")
@Getter
@Setter
public class Vino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IO;

    private String NOMBRE;

    private Bodega BODEGA;

    private int AÑADA;

    private LocalDateTime FECHA_ACTUALIZACION;

    private String IMAGEN_ETIQUETA;

    private String NOTA_CATA;

    private Integer PRECIOARS;


}
