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

    // INYECCIONES DE LOS REPOSITORIOS DE LOS OBJETOS

    private BodegaRepository bodegaRepository;
    private VinoRemotoRepositroy vinoRemotoRepository;
    private MaridajeRepository maridajeRepository;
    private VinoRepository vinoRepository;
    private TipoUvaRepository tipoUvaRepository;
    private UsuarioRepository usuarioRepository;
    private VarietalRepository varietalRepository;
    private EnofiloRepository enofiloRepository;
    private SiguiendoRepository siguiendoRepository;
    private ReseñaRepository reseñaRepository;
    private VinosDeEnofiloRepository vinosDeEnofiloRepository;

    // INYECCIONES DE LOS REPOSITORIOS DE LOS OBJETOS
    private LocalDateTime fechaActual;
    private List<Bodega> bodegasParaActualizar;
    private Bodega bodegaAActualzar;
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

    public List<VinoActualizado> tomarSeleccionDeBodega(String nombreBodega){
        long idBodega = interfazBD.getBodegaIdByNombre(nombreBodega);
        this.bodegaAActualzar = interfazBD.findBodegaById(idBodega);
        List<VinoRemoto> vinosAActualizar = obtenerActualizacionesVinos(idBodega);

        List<VinoActualizado> vinosActualizados = this.bodegaAActualzar.actualizarVinos(vinosAActualizar, vinos, maridajes, varietales);

        this.bodegaAActualzar.setFECHA_ULTIMA_ACTUALIZACION(fechaActual);

        notificarEnofilosSuscriptos();

        persistirObjetosNuevos();

        return vinosActualizados;
    }

    private List<VinoRemoto> obtenerActualizacionesVinos(Long idBodega){
        return interfazSistemaBodega.obtenerActualizacionesVinos(idBodega);
    }

    private void notificarEnofilosSuscriptos(){
        List<String> enofilosANotificar = new ArrayList<>();
        for (Enofilo enofilo : enofilos) {
            if(enofilo.estaSuscriptoABodega(bodegaAActualzar)){
                enofilosANotificar.add(enofilo.obtenerNombreUsuario());
            }
        }
        String notificacion = generarNotificacion();
        InterfazNotificacionesPush interfazNotificacionesPush = new InterfazNotificacionesPush(enofilosANotificar, notificacion);
        interfazNotificacionesPush.notificarActualizacionBodega();

    }

    private String generarNotificacion(){
        StringBuilder notificacion = new StringBuilder();
        notificacion.append("Hay novedades en la bodega ").append(bodegaAActualzar.getNombre()).append(" disponibles en la app");
        return notificacion.toString();
    }

    private void persistirObjetosNuevos(){
        interfazBD.persistirVinos(vinos);
    }

}
