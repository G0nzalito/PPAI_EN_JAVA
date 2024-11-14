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

    // OBJETOS DE LA BD CARGADOS EN MEMORIA

    private List<Bodega> bodegas;
    private List<Enofilo> enofilos;
    private List<Maridaje> maridajes;
    private List<Reseña> reseñas;
    private List<Siguiendo> seguidos;
    private List<TipoUva> tiposUvas;
    private List<Usuario> usuarios;
    private List<Varietal> varietales;
    private List<Vino> vinos;

    // OBJETOS DE LA BD CARGADOS EN MEMORIA
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
        recuperarObjetos();
    }

    private void recuperarObjetos(){
        this.maridajes = interfazBD.getMaridajes();
        this.tiposUvas = interfazBD.getTiposUva();
        this.usuarios = interfazBD.getUsuarios();
        this.bodegas = interfazBD.getBodegas();
        this.varietales = interfazBD.getVarietales(tiposUvas);
        this.enofilos = interfazBD.getEnofilos(usuarios);
        this.seguidos = interfazBD.getSiguiendos(enofilos, bodegas);
        this.vinos = interfazBD.getVinos(maridajes, bodegas, varietales, enofilos, reseñas);
        this.reseñas = interfazBD.getReseñas(vinos, enofilos);
        interfazBD.reconstruirVinosDeEnofilo(enofilos, vinos);
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
        List<VinoRemoto> vinosAActualizar = obtenerActualizacionesVinos(idBodega);
        List<Vino> vinosDeBodega = vinos.stream().filter(v -> v.esDeBodega(bodegaAActualzar.getNombre())).toList();
        this.bodegaAActualzar.actualizarVinos(vinosAActualizar, vinosDeBodega, maridajes);
    }

    private List<VinoRemoto> obtenerActualizacionesVinos(Long idBodega){
        List<VinoRemoto> vinosAActualizar = interfazSistemaBodega.obtenerActualizacionesVinos(idBodega);
        return vinosAActualizar;
    }

}
