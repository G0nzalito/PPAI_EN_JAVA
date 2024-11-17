package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.*;
import com.PPAIEnJava.NuevoPPAI.Repositories.*;
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

    private LocalDateTime fechaActual;
    private List<Bodega> bodegasParaActualizar;
    private Bodega bodegaAActualizar;
    private InterfazSistemaBodega interfazSistemaBodega;
    private InterfazBD interfazBD;
    private InterfazNotificacionesPush notificacionesPush;

    public Gestor() {
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
        // Buscamos el singleton de la interfaz de la BD
        this.interfazBD = InterfazBD.getInstancia();
        //Creamos la interfaz del sistema de bodegas para luego poder "acceder a los vinos de esa bodega"
        this.interfazSistemaBodega = new InterfazSistemaBodega(interfazBD);
        // cargamos todos los objetos de la BD a la memoria
        recuperarObjetos();

        //Empieza la ejecución de la logica de negocio
        this.fechaActual = getFechaActual();
        return buscarBodegasConActualizacion();
    }

    private LocalDateTime getFechaActual() {
        return LocalDateTime.now();
    }

    private List<Bodega> buscarBodegasConActualizacion(){
        List<Bodega> bodegasParaActualizacion = new ArrayList<>();
        for (Bodega bodega : bodegas) {
            if (bodega.esTiempoDeActualizar(fechaActual)){
                bodegasParaActualizacion.add(bodega);
            }
        }
        return bodegasParaActualizacion;
    }

    public List<VinoActualizado> tomarSeleccionDeBodega(String nombreBodega){
        this.bodegaAActualizar = bodegas.stream().filter(bodega -> bodega.getNombre().equals(nombreBodega)).findFirst().get();
        long idBodega = bodegaAActualizar.getID();
        List<VinoRemoto> vinosAActualizar = obtenerActualizacionesVinos(idBodega);

        List<VinoActualizado> vinosActualizados = actualizarVinosDeBodega(vinosAActualizar);

        this.bodegaAActualizar.setFECHA_ULTIMA_ACTUALIZACION(fechaActual);
        interfazBD.persistirBodega(bodegaAActualizar);

        notificarEnofilosSuscriptos();

        persistirObjetosNuevos();

        return vinosActualizados;
    }

    private List<VinoRemoto> obtenerActualizacionesVinos(Long idBodega){
        return interfazSistemaBodega.obtenerActualizacionesVinos(idBodega);
    }

    private List<VinoActualizado> actualizarVinosDeBodega(List<VinoRemoto> vinosAActualizar){
        return this.bodegaAActualizar.actualizarVinos(vinosAActualizar, vinos, maridajes, varietales);
    }

    private void notificarEnofilosSuscriptos(){
        List<String> enofilosANotificar = new ArrayList<>();
        for (Enofilo enofilo : enofilos) {
            if(enofilo.estaSuscriptoABodega(bodegaAActualizar)){
                enofilosANotificar.add(enofilo.obtenerNombreUsuario());
            }
        }
        String notificacion = generarNotificacion();
        InterfazNotificacionesPush interfazNotificacionesPush = new InterfazNotificacionesPush(enofilosANotificar, notificacion);
        interfazNotificacionesPush.notificarActualizacionBodega();

    }

    private String generarNotificacion(){
        StringBuilder notificacion = new StringBuilder();
        notificacion.append("Hay novedades en la bodega ").append(bodegaAActualizar.getNombre()).append(" disponibles en la app");
        return notificacion.toString();
    }

    private void persistirObjetosNuevos(){

        interfazBD.persistirVinos(vinos);
    }

}
