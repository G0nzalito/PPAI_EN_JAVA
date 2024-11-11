package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

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

    public TipoUva(String NOMBRE, String DESCRIPCION) {
        this.ID = ID;
        this.NOMBRE = NOMBRE;
        this.DESCRIPCION = DESCRIPCION;
    }

    public boolean sosTipoUva(String tipoAComprobar) {
        return this.NOMBRE.equals(tipoAComprobar);
    }

    public String mostrarNombre(){
        return this.NOMBRE;
    }

}
