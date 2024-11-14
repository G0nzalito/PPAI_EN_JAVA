package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.*;
import com.PPAIEnJava.NuevoPPAI.Repositories.BodegaRepository;
import com.PPAIEnJava.NuevoPPAI.Repositories.VinoRemotoRepositroy;
import com.PPAIEnJava.NuevoPPAI.Repositories.VinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class Gestor {

    private BodegaRepository bodegaRepository;
    private LocalDateTime fechaActual;
    private List<Bodega> bodegasParaActualizar;
    private Bodega bodegaAActualzar;
    private InterfazSistemaBodega interfazSistemaBodega;
    private InterfazBD interfazBD;

    @Autowired
    public Gestor(BodegaRepository bodegaRepository, VinoRemotoRepositroy vinoRemotoRepository, InterfazBD interfazBD) {
        this.bodegaRepository = bodegaRepository;
        this.interfazBD = interfazBD;
        this.interfazSistemaBodega = new InterfazSistemaBodega(interfazBD);
    }

    //List<Bodega>
    public List<Bodega> opcionImportarActualizaciones(){
        this.fechaActual = LocalDateTime.now();
        return buscarBodegasConActualizacion();
    }

    private LocalDateTime getFechaActual() {
        return LocalDateTime.now();
    }

    private List<Bodega> buscarBodegasConActualizacion(){
        List<Bodega> bodegas = interfazBD.getBodegas();
        List<Bodega> bodegasParaActualizacion = new ArrayList<>();
        for (Bodega bodega : bodegas) {
            if (bodega.esTiempoDeActualizar(fechaActual)){
                bodegasParaActualizacion.add(bodega);
            }
        }
        return bodegasParaActualizacion;
    }

    public void tomarSeleccionDeBodega(String nombreBodega){
        long idBodega = bodegaRepository.recoverIdByNombre(nombreBodega);
        this.bodegaAActualzar = bodegaRepository.findById(idBodega).get();
        List<VinoRemoto> vinoAActualizar = obtenerActualizacionesVinos(idBodega);
        List<Maridaje> maridajes = interfazBD.getMaridajes();
//        interfazBD.getVinosOfBodega(idBodega);
//        this.bodegaAActualzar.actualizarVinos();
    }

    private List<VinoRemoto> obtenerActualizacionesVinos(Long idBodega){
        List<VinoRemoto> vinosAActualizar = interfazSistemaBodega.obtenerActualizacionesVinos(idBodega);
        return vinosAActualizar;
    }

}
