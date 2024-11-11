package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Bodega;

import java.time.LocalDateTime;

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

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Enofilo getAmigo() {
        return amigo;
    }

    public void setAmigo(Enofilo amigo) {
        if (this.bodega == null && this.sommelier == null) {
            this.amigo = amigo;
        }
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Bodega getBodega() {
        return bodega;
    }

    public void setBodega(Bodega bodega) {
        if (this.amigo == null && this.sommelier == null) {
            this.bodega = bodega;
        }
    }

    public String getSommelier() {
        return sommelier;
    }

    public void setSommelier(String sommelier) {
        if (this.bodega == null && this.amigo == null) {
            this.sommelier = sommelier;
        }
    }

    public boolean sosDeSommelier(String sommelier) {
        return this.sommelier != null && this.sommelier.equals(sommelier);
    }

    public boolean sosDeAmigo(Enofilo amigo) {
        return this.amigo != null && this.amigo.equals(amigo);
    }

    public boolean sosDeBodega(Bodega bodega) {
        return this.bodega != null && this.bodega.equals(bodega);
    }
}
