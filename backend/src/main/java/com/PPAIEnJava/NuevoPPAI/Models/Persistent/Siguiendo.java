package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "Siguiendo")
public class Siguiendo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "FECHA_INICIO")
    private LocalDateTime fechaInicio;
    @Column(name = "FECHA_FIN")
    private LocalDateTime fechaFin;
    @OneToOne
    private Bodega BODEGA;
    private String SOMMELIER;
    @OneToOne
    private Enofilo ENOFILO;
    @ManyToOne
    @JoinColumn(name = "ENOFILO_PROPIETARIO")
    private Enofilo ENOFILO_PROPIETARIO;

    public Siguiendo(LocalDateTime fechaInicio, Object bodegaOSommelierOamigo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = null;
        this.BODEGA = null;
        this.SOMMELIER = null;
        this.ENOFILO = null;

        if (bodegaOSommelierOamigo instanceof String) {
            this.SOMMELIER = (String) bodegaOSommelierOamigo;
        } else if (bodegaOSommelierOamigo instanceof Bodega) {
            this.BODEGA = (Bodega) bodegaOSommelierOamigo;
        } else if (bodegaOSommelierOamigo instanceof Enofilo) {
            this.ENOFILO = (Enofilo) bodegaOSommelierOamigo;
        }
    }

    public Siguiendo() {

    }

    public boolean sosDeBodega(Bodega bodega) {
        return this.BODEGA != null && this.BODEGA.equals(bodega);
    }
}
