package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Vino;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VinoActualizado {
//    private Vino vinoAMostrar;
    private String nombreVino;
    private String nombreBodega;
    private String añanda;
    private String fechaActualizacion;
    private String imagen_etiqueta;
    private String nota_cata;
    private Integer precioArs;
    private Integer puntaje;

    public VinoActualizado(String nombreVino, String nombreBodega, String añanda, String fechaActualizacion, String imagen_etiqueta, String nota_cata, Integer precioArs, String estado, String varietalesAMostrar, String maridajesAActualizar, Integer puntaje) {
        this.nombreVino = nombreVino;
        this.nombreBodega = nombreBodega;
        this.añanda = añanda;
        this.fechaActualizacion = fechaActualizacion;
        this.imagen_etiqueta = imagen_etiqueta;
        this.nota_cata = nota_cata;
        this.precioArs = precioArs;
        this.estado = estado;
        this.varietalesAMostrar = varietalesAMostrar;
        this.maridajesAActualizar = maridajesAActualizar;
        this.puntaje = puntaje;
    }


    @Override
    public String toString() {
        return "VinoActualizado{" +
                "nombreVino='" + nombreVino + '\'' +
                ", nombreBodega='" + nombreBodega + '\'' +
                ", añanda='" + añanda + '\'' +
                ", fechaActualizacion='" + fechaActualizacion + '\'' +
                ", imagen_etiqueta='" + imagen_etiqueta + '\'' +
                ", nota_cata='" + nota_cata + '\'' +
                ", precioArs=" + precioArs +
                ", estado='" + estado + '\'' +
                ", varietalesAMostrar='" + varietalesAMostrar + '\'' +
                ", maridajesAActualizar='" + maridajesAActualizar + '\'' +
                '}';
    }

    private String estado;
    private String varietalesAMostrar;
    private String maridajesAActualizar;


}
