package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private TipoUva Uva;

    private int PORCENTAJE;

    @Override
    public String toString() {
        return "Varietal{" +
                "ID=" + ID +
                ", UVA=" + Uva.getNOMBRE() +
                ", PORCENTAJE=" + PORCENTAJE +
                '}';
    }

    public Varietal(TipoUva ID_UVA, int PORCENTAJE) {
        this.Uva = ID_UVA;
        this.PORCENTAJE = PORCENTAJE;
    }

    public Varietal() {

    }
    public TipoUva conocerTipoDeUva() {
        return this.Uva;
    }

    public boolean esDeTipoUva(String tipoUva) {
        return this.Uva.getNOMBRE().equalsIgnoreCase(tipoUva);
    }

    public String getNombreUva(){
        return this.Uva.getNOMBRE();
    }


    public String mostrarPorcentaje() {
        return this.PORCENTAJE + "%";
    }

    public Boolean esTuPorcentaje(Integer porcentaje) {
        return this.PORCENTAJE == porcentaje;
    }
}
