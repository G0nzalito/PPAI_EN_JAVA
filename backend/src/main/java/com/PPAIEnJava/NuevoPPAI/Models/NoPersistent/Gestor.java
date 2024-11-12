package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Bodega;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class Gestor {

    private LocalDateTime fechaActual;

    //List<Bodega>
    public void opcionImportarActualizaciones(){
        this.fechaActual = LocalDateTime.now();

    }

    private LocalDateTime getFechaActual() {
        return LocalDateTime.now();
    }

//    private List<Bodega> buscarBodegasConActualizacion(){
//
//    }

}
