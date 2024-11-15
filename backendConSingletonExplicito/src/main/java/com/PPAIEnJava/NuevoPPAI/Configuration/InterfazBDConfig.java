package com.PPAIEnJava.NuevoPPAI.Configuration;

import com.PPAIEnJava.NuevoPPAI.Models.NoPersistent.InterfazBD;
import com.PPAIEnJava.NuevoPPAI.Models.NoPersistent.Persistencia;
import com.PPAIEnJava.NuevoPPAI.Repositories.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InterfazBDConfig {
    @Autowired
    private BodegaRepository bodegaRepository;
    @Autowired
    private VinoRemotoRepositroy vinoRemotoRepository;
    @Autowired
    private MaridajeRepository maridajeRepository;
    @Autowired
    private VinoRepository vinoRepository;
    @Autowired
    private TipoUvaRepository tipoUvaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private VarietalRepository varietalRepository;
    @Autowired
    private Persistencia persistencia;
    @Autowired
    private EnofiloRepository enofiloRepository;
    @Autowired
    private SiguiendoRepository siguiendoRepository;
    @Autowired
    private ReseñaRepository reseñaRepository;
    @Autowired
    private VinosDeEnofiloRepository vinosDeEnofiloRepository;

    @PostConstruct
    public void initializeSingleton() {
        InterfazBD.getInstancia().initialize(
                bodegaRepository,
                vinoRemotoRepository,
                maridajeRepository,
                vinoRepository,
                tipoUvaRepository,
                usuarioRepository,
                varietalRepository,
                persistencia,
                enofiloRepository,
                siguiendoRepository,
                reseñaRepository,
                vinosDeEnofiloRepository
        );
    }
}
