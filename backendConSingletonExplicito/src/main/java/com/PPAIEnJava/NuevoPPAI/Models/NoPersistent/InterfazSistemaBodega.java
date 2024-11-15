package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Repositories.VinoRemotoRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class InterfazSistemaBodega {

    private InterfazBD interfazBD;

    public InterfazSistemaBodega(InterfazBD interfazBD) {
        this.interfazBD = interfazBD;
    }

    public List<VinoRemoto> obtenerActualizacionesVinos(long idBodega){
        String idBodegaStr = String.valueOf(idBodega);
        return interfazBD.getVinosEnRemoto(idBodegaStr);
    }
}
