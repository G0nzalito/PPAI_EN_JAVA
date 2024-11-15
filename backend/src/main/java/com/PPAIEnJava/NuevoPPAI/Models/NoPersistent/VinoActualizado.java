package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Vino;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class VinoActualizado {
    private Vino vinoAMostrar;
    private String estado;
    private List<String> varietalesAMostrar;

    public VinoActualizado(Vino vinoAMostrar, String estado, List<String> varietalesAMostrar) {
        this.vinoAMostrar = vinoAMostrar;
        this.estado = estado;
        this.varietalesAMostrar = varietalesAMostrar;
    }

    @Override
    public String toString() {
        return "VinoActualizado{" +
                "vinoAMostrar=" + vinoAMostrar.getPRECIOARS() +
                ", estado='" + estado + '\'' +
                ", varietalesAMostrar=" + varietalesAMostrar +
                '}';
    }
}
