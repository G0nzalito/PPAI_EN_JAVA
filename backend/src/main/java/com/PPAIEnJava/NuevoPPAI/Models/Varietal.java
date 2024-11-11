package com.PPAIEnJava.NuevoPPAI.Models;

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
    @JoinColumn(referencedColumnName = "ID")
    private TipoUva ID_UVA;

    private int PORCENTAJE;

    @ManyToMany(mappedBy = "VarietalesVino")
    private List<Vino> Vinos;

    @Override
    public String toString() {
        return "Varietal{" +
                "ID=" + ID +
                ", ID_UVA=" + ID_UVA +
                ", PORCENTAJE=" + PORCENTAJE +
                ", Vinos=" + Vinos +
                '}';
    }

    public Varietal(long ID, TipoUva ID_UVA, int PORCENTAJE, List<Vino> vinos) {
        this.ID = ID;
        this.ID_UVA = ID_UVA;
        this.PORCENTAJE = PORCENTAJE;
        Vinos = vinos;
    }

    public Varietal() {

    }
}
