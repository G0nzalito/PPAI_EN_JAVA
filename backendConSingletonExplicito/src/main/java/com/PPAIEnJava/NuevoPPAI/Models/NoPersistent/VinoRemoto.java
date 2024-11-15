package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Bodega;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "vinos_remotos")
public class VinoRemoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    private String NOMBRE;

    private int BODEGA;

    @Column(name = "ANIADA")
    private int AÑADA;

    private LocalDateTime FECHA_ACTUALIZACION;

    private String IMAGEN_ETIQUETA;

    private String NOTA_CATA;


    private Integer PRECIOARS;

    private String Maridajes;

    private String Varietales;

    public VinoRemoto() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VinoRemoto that = (VinoRemoto) o;
        return ID == that.ID && AÑADA == that.AÑADA && Objects.equals(NOMBRE, that.NOMBRE) && Objects.equals(BODEGA, that.BODEGA) && Objects.equals(FECHA_ACTUALIZACION, that.FECHA_ACTUALIZACION) && Objects.equals(IMAGEN_ETIQUETA, that.IMAGEN_ETIQUETA) && Objects.equals(NOTA_CATA, that.NOTA_CATA) && Objects.equals(PRECIOARS, that.PRECIOARS) && Objects.equals(Maridajes, that.Maridajes) && Objects.equals(Varietales, that.Varietales);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, NOMBRE, BODEGA, AÑADA, FECHA_ACTUALIZACION, IMAGEN_ETIQUETA, NOTA_CATA, PRECIOARS, Maridajes, Varietales);
    }

    public VinoRemoto(String NOMBRE, int BODEGA, int AÑADA, LocalDateTime FECHA_ACTUALIZACION, String IMAGEN_ETIQUETA, String NOTA_CATA, Integer PRECIOARS, String maridajes, String varietales) {
        this.NOMBRE = NOMBRE;
        this.BODEGA = BODEGA;
        this.AÑADA = AÑADA;
        this.FECHA_ACTUALIZACION = FECHA_ACTUALIZACION;
        this.IMAGEN_ETIQUETA = IMAGEN_ETIQUETA;
        this.NOTA_CATA = NOTA_CATA;
        this.PRECIOARS = PRECIOARS;
        Maridajes = maridajes;
        Varietales = varietales;
    }

    @Override
    public String toString() {
        return "VinoRemoto{" +
                "Maridajes='" + Maridajes + '\'' +
                ", Varietales='" + Varietales + '\'' +
                ", PRECIOARS=" + PRECIOARS +
                ", NOTA_CATA='" + NOTA_CATA + '\'' +
                ", IMAGEN_ETIQUETA='" + IMAGEN_ETIQUETA + '\'' +
                ", FECHA_ACTUALIZACION=" + FECHA_ACTUALIZACION +
                ", AÑADA=" + AÑADA +
                ", BODEGA=" + BODEGA +
                ", NOMBRE='" + NOMBRE + '\'' +
                '}';
    }
}
