package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import java.util.List;

public class VinoActualizado {
    private Vino vinoAMostrar;
    private String estado;
    private List<String> varietalesAMostrar;

    public VinoActualizado(Vino vinoAMostrar, String estado, List<String> varietalesAMostrar) {
        this.vinoAMostrar = vinoAMostrar;
        this.estado = estado;
        this.varietalesAMostrar = varietalesAMostrar;
    }
}
