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
        return gestor.tomarSeleccionDeBodega(bodegaSeleccionada);

    }

}
