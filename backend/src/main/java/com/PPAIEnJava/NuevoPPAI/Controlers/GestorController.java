package com.PPAIEnJava.NuevoPPAI.Controlers;

import com.PPAIEnJava.NuevoPPAI.Models.NoPersistent.Gestor;
import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Bodega;
import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Vino;
import com.PPAIEnJava.NuevoPPAI.Models.Persistent.VinoActualizado;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController()
@RequestMapping(path = "/gestor")
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
        List<Bodega> bodegas = new ArrayList<>();
        Bodega b1 = new Bodega("hola", "brenda", "prueba", 5, LocalDateTime.now(), 3000);
        Bodega b2 = new Bodega("hola", "alan", "prueba 2", 7, LocalDateTime.now(), 4000);
        bodegas.add(b1);
        bodegas.add(b2);
        return bodegas;
    }

    @GetMapping(path = "resultadoTablas")
    public List<VinoActualizado> resultadoTablas(){
        List<VinoActualizado> vinoActualizados = new ArrayList<>();
        Vino v1 = new Vino()
    }

}
