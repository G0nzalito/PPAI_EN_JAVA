package com.PPAIEnJava.NuevoPPAI.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

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

    public Maridaje(long ID, String NOMBRE, String DESCRIPCION) {
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
}
