package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Repositories.VinoRemotoRepositroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public class InterfazSistemaBodega {

    private VinoRemotoRepositroy vinoRemotoRepositroy;

    public InterfazSistemaBodega(VinoRemotoRepositroy vinoRemotoRepositroy) {
        this.vinoRemotoRepositroy = vinoRemotoRepositroy;
    }

    public List<VinoRemoto> obtenerActualizacionesVinos(long idBodega){
        String idBodegaStr = String.valueOf(idBodega);
        List<VinoRemoto> vinosAActualizar = vinoRemotoRepositroy.findVinoByBodega(idBodegaStr);
        return vinosAActualizar;
    }
}
