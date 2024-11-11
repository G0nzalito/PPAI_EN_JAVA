package com.PPAIEnJava.NuevoPPAI.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "Vinos")
@Getter
@Setter
public class Vino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IO;

    private String NOMBRE;

    @OneToOne
    @JoinColumn(referencedColumnName = "ID")
    private Bodega BODEGA;

    private int AÑADA;

    private LocalDateTime FECHA_ACTUALIZACION;

    private String IMAGEN_ETIQUETA;

    private String NOTA_CATA;

    private Integer PRECIOARS;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Maridaje_de_vino",
            joinColumns = @JoinColumn(name = "ID_VINO"),
            inverseJoinColumns = @JoinColumn(name = "ID_MARIDAJE")
    )
    private List<Maridaje> MaridajesVino;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Varietal_de_vino",
            joinColumns = @JoinColumn(name = "ID_VINO"),
            inverseJoinColumns = @JoinColumn(name = "ID_VARIETAL")
    )
    private List<Varietal> VarietalesVino;


}
