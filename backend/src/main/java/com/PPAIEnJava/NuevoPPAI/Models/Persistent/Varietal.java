package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "Varietal")
@Getter
@Setter
public class Varietal {
    @Id
    @GeneratedValue
    private long ID;

    @OneToOne
    @JoinColumn(referencedColumnName = "ID", name = "ID_UVA")
    private TipoUva ID_UVA;

    private int PORCENTAJE;

    @Override
    public String toString() {
        return "Varietal{" +
                "ID=" + ID +
                ", UVA=" + ID_UVA.getNOMBRE() +
                ", PORCENTAJE=" + PORCENTAJE +
                '}';
    }

    public Varietal(TipoUva ID_UVA, int PORCENTAJE) {
        this.ID_UVA = ID_UVA;
        this.PORCENTAJE = PORCENTAJE;
    }

    public Varietal() {

    }
    public TipoUva conocerTipoDeUva() {
        return this.ID_UVA;
    }

    public boolean esDeTipoUva(String tipoUva) {
        return this.ID_UVA.getNOMBRE().equalsIgnoreCase(tipoUva);
    }



    public String mostrarPorcentaje() {
        return this.PORCENTAJE + "%";
    }
}
