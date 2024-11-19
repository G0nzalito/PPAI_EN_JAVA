package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.*;
import com.PPAIEnJava.NuevoPPAI.Repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterfazBD {
    private static InterfazBD instancia;

    private BodegaRepository bodegaRepository;
    private VinoRemotoRepositroy vinoRemotoRepository;
    private MaridajeRepository maridajeRepository;
    private VinoRepository vinoRepository;
    private TipoUvaRepository tipoUvaRepository;
    private UsuarioRepository usuarioRepository;
    private VarietalRepository varietalRepository;
    private Persistencia persistencia;
    private EnofiloRepository enofiloRepository;
    private SiguiendoRepository siguiendoRepository;
    private ReseñaRepository reseñaRepository;
    private VinosDeEnofiloRepository vinosDeEnofiloRepository;

    private InterfazBD() {}

    public static synchronized InterfazBD getInstancia() {
        if (instancia == null) {
            instancia = new InterfazBD();
        }
        return instancia;
    }

    public void initialize(BodegaRepository bodegaRepository,
                           VinoRemotoRepositroy vinoRemotoRepository,
                           MaridajeRepository maridajeRepository,
                           VinoRepository vinoRepository,
                           TipoUvaRepository tipoUvaRepository,
                           UsuarioRepository usuarioRepository,
                           VarietalRepository varietalRepository,
                           Persistencia persistencia,
                           EnofiloRepository enofiloRepository,
                           SiguiendoRepository siguiendoRepository,
                           ReseñaRepository reseñaRepository,
                           VinosDeEnofiloRepository vinosDeEnofiloRepository) {

        if (this.bodegaRepository == null) {
            this.bodegaRepository = bodegaRepository;
            this.vinoRemotoRepository = vinoRemotoRepository;
            this.maridajeRepository = maridajeRepository;
            this.vinoRepository = vinoRepository;
            this.tipoUvaRepository = tipoUvaRepository;
            this.usuarioRepository = usuarioRepository;
            this.varietalRepository = varietalRepository;
            this.persistencia = persistencia;
            this.enofiloRepository = enofiloRepository;
            this.siguiendoRepository = siguiendoRepository;
            this.reseñaRepository = reseñaRepository;
            this.vinosDeEnofiloRepository = vinosDeEnofiloRepository;
        }
    }

    public List<VinoRemoto> getVinosEnRemoto(String idBodegaStr){
        return vinoRemotoRepository.findVinoByBodega(idBodegaStr);
    }

    public List<Maridaje> getMaridajes(){
        return maridajeRepository.findAll();
    }

    public List<TipoUva> getTiposUva(){
        return tipoUvaRepository.findAll();
    }
    public List<Usuario> getUsuarios(){
        return usuarioRepository.findAll();
    }
    public List<Bodega> getBodegas(){
        return bodegaRepository.findAll();
    }

    public List<Enofilo> getEnofilos(List<Usuario> usuarios){
        List<Object[]> enofilosAReconstruir = enofiloRepository.getEnofilos();
        return persistencia.reconstruirEnofilos(enofilosAReconstruir, usuarios);
    }

    public List<Varietal> getVarietales(List<TipoUva> tiposUva){
        List<Object[]> varietalesAReconstruir = varietalRepository.getVarietales();
        return persistencia.reconstruirVarietal(tiposUva, varietalesAReconstruir);
    }

    public List<Siguiendo> getSiguiendos(List<Enofilo> enofilos, List<Bodega> bodegas){
        List<Object[]> siguiendosAReconstruir = siguiendoRepository.getSiguiendos();
        return persistencia.reconstruirSiguiendos(siguiendosAReconstruir, enofilos, bodegas);
    }

    public List<Reseña> getReseñas(List<Vino> vinos,List<Enofilo> enofilos){
        List<Object[]> reseñasARecontruir = reseñaRepository.getReseñas();
        return persistencia.reconstruirReseñas(vinos, enofilos, reseñasARecontruir);
    }

    public void reconstruirVinosDeEnofilo(List<Enofilo> enofilos, List<Vino> vinos){
        List<Object[]> vinosDeEnofilo = vinosDeEnofiloRepository.getVinosDeEnofilo();
        persistencia.reconstruirVinosDeEnofilo(enofilos, vinos, vinosDeEnofilo);
    }

    public List<Vino> getVinos(List<Maridaje> maridajes, List<Bodega> bodegas, List<Varietal> varietales, List<Enofilo> enofilos){
        List<Object[]> vinosAReconstruir = vinoRepository.getVinos();
        List<Object[]> maridajesVino = maridajeRepository.getMaridajesVino();
        List<Object[]> varietalesVino = varietalRepository.getVarietalesVino();
        return persistencia.reconstruirVinos(vinosAReconstruir, maridajes, bodegas, varietales,varietalesVino, maridajesVino);

    }

    public void persistirVinos(List<Vino> vinos){
        vinoRepository.saveAll(vinos);
    }

    public void persistirBodega(Bodega bodega){
        bodegaRepository.save(bodega);
    }



}
