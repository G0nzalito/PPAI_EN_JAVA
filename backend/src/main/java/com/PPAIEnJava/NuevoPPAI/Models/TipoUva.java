package com.PPAIEnJava.NuevoPPAI.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TipoUva")
@Getter
@Setter
public class TipoUva {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String NOMBRE;

    private String DESCRIPCION;

    @Override
    public String toString() {
        return "TipoUva{" +
                "ID=" + ID +
                ", NOMBRE='" + NOMBRE + '\'' +
                ", DESCRIPCION='" + DESCRIPCION + '\'' +
                '}';
    }

    public TipoUva() {

    }

    public TipoUva(long ID, String NOMBRE, String DESCRIPCION) {
        this.ID = ID;
        this.NOMBRE = NOMBRE;
        this.DESCRIPCION = DESCRIPCION;
    }
}
