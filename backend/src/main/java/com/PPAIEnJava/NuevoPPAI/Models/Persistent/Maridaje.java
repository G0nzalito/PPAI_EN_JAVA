package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.ArrayList;

@Entity
@Table(name = "Maridajes")
@Getter
@Setter
public class Maridaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String NOMBRE;

    private String DESCRIPCION;

    @ManyToMany(mappedBy = "MaridajesVino")
    private List<Vino> VinosMaridados;

    public Maridaje(String NOMBRE, String DESCRIPCION) {
        this.ID = ID;
        this.NOMBRE = NOMBRE;
        this.DESCRIPCION = DESCRIPCION;
    }

    public Maridaje() {

    }

    @Override
    public String toString() {
        return "Maridaje{" +
                "ID=" + ID +
                ", NOMBRE='" + NOMBRE + '\'' +
                ", DESCRIPCION='" + DESCRIPCION + '\'' +
                '}';
    }

    public boolean sosMaridaje(String tipoAComprobar) {
        return this.NOMBRE.equals(tipoAComprobar);
    }

    public boolean maridaConVino(Vino vino) {
        List<Maridaje> maridajes = vino.getMaridaje();
        List<String> maridajesNombres = new ArrayList<>();

        for (Maridaje maridaje : maridajes) {
            maridajesNombres.add(maridaje.getNOMBRE());
        }

        return maridajesNombres.contains(this.NOMBRE);
    }
}
