package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Siguiendo {
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private Bodega bodega;
    private String sommelier;
    private Enofilo amigo;

    public Siguiendo(LocalDateTime fechaInicio, Object bodegaOSommelierOamigo) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = null;
        this.bodega = null;
        this.sommelier = null;
        this.amigo = null;

        if (bodegaOSommelierOamigo instanceof String) {
            this.sommelier = (String) bodegaOSommelierOamigo;
        } else if (bodegaOSommelierOamigo instanceof Bodega) {
            this.bodega = (Bodega) bodegaOSommelierOamigo;
        } else if (bodegaOSommelierOamigo instanceof Enofilo) {
            this.amigo = (Enofilo) bodegaOSommelierOamigo;
        }
    }

    public boolean sosDeBodega(Bodega bodega) {
        return this.bodega != null && this.bodega.equals(bodega);
    }
}
