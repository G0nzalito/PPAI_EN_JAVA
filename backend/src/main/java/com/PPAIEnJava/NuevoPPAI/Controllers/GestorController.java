package com.PPAIEnJava.NuevoPPAI.Controllers;

import com.PPAIEnJava.NuevoPPAI.Models.NoPersistent.Gestor;
import com.PPAIEnJava.NuevoPPAI.Models.NoPersistent.VinoActualizado;
import com.PPAIEnJava.NuevoPPAI.Models.Persistent.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping(path = "/gestor")
@ResponseBody
@CrossOrigin
public class GestorController {

    private final Gestor gestor;

    @Autowired
    public GestorController(Gestor gestor) {
        this.gestor = gestor;
    }

    @GetMapping(path = "/hola")
    public ResponseEntity<String> hola() {
        return ResponseEntity.ok("Hola");
    }

    @GetMapping(path = "/bodegas")
    public List<Bodega> opcionImportarActualizacion(){
       return gestor.opcionImportarActualizaciones();
    }

    @GetMapping(path = "/resultadoTablas")
    public List<VinoActualizado> resultadoTablas(@RequestParam String bodegaSeleccionada){



        List<VinoActualizado> vinoActualizados = new ArrayList<>();
        gestor.tomarSeleccionDeBodega(bodegaSeleccionada);


        Bodega bodega = new Bodega("Historia", "Nombre", "Descripcion", 4, LocalDateTime.now(), 34);
        TipoUva uva = new TipoUva("Soy una uva", "Soy una descripcion");
        Varietal varietal = new Varietal(uva, 30);
        List<Varietal> Varietales = new ArrayList<>();
        Varietales.add(varietal);

        Maridaje maridaje = new Maridaje("Maridaje", "Soy una descripcion");
        List<Maridaje> Maridajes = new ArrayList<>();
        Maridajes.add(maridaje);
        Reseña reseña = new Reseña("Comento", true, LocalDateTime.now(), 13, null);
        List<Reseña> Reseñas = new ArrayList<>();
        Reseñas.add(reseña);
        Vino vino1 = new Vino("soy un Vino!", bodega, 2, LocalDateTime.now(), "Imagen de vino", "Soy una nota", 2, Maridajes, Varietales, Reseñas);

        Vino vino2 = new Vino("soy un Vinaso!", bodega, 2003, LocalDateTime.now(), "Imagen de vino nuevo", "Soy una nota increible", 250, Maridajes, Varietales, Reseñas);

        List<String> varietalAMostrar = getVarietalesAMostrar(Varietales);

        VinoActualizado vinoAMostrar1 = new VinoActualizado(vino1, "Creado", varietalAMostrar);
        VinoActualizado vinoAMostrar2 = new VinoActualizado(vino2, "Actualizado", varietalAMostrar);

        vinoActualizados.add(vinoAMostrar1);
        vinoActualizados.add(vinoAMostrar2);

        return vinoActualizados;

    }

    public List<String> getVarietalesAMostrar(List<Varietal> Varietales) {
        List<String> varietalesAMostrar = new ArrayList<>();
        for (Varietal varietal : Varietales) {
            String nombreTipoUva = varietal.conocerTipoDeUva().getNOMBRE();
            int porcentaje = varietal.getPORCENTAJE();
            varietalesAMostrar.add(nombreTipoUva + ": " + porcentaje + "%");
        }
        return varietalesAMostrar;
    }

}
