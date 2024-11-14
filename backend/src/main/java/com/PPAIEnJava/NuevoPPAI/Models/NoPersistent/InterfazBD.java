package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.*;
import com.PPAIEnJava.NuevoPPAI.Repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Service
public class InterfazBD {
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


    @Autowired
    private InterfazBD(BodegaRepository bodegaRepository, VinoRemotoRepositroy vinoRemotoRepository, MaridajeRepository maridajeRepository, VinoRepository vinoRepository, TipoUvaRepository tipoUvaRepository, UsuarioRepository usuarioRepository, VarietalRepository varietalRepository, EnofiloRepository enofiloRepository, SiguiendoRepository siguiendoRepository, ReseñaRepository reseñaRepository, VinosDeEnofiloRepository vinosDeEnofiloRepository, Persistencia persistencia) {
            this.bodegaRepository = bodegaRepository;
            this.vinoRemotoRepository = vinoRemotoRepository;
            this.maridajeRepository = maridajeRepository;
            this.vinoRepository = vinoRepository;
            this.tipoUvaRepository = tipoUvaRepository;
            this.usuarioRepository = usuarioRepository;
            this.varietalRepository = varietalRepository;
            this.enofiloRepository = enofiloRepository;
            this.siguiendoRepository = siguiendoRepository;
            this.reseñaRepository = reseñaRepository;
            this.vinosDeEnofiloRepository = vinosDeEnofiloRepository;
            this.persistencia = persistencia;
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

    public List<Vino> getVinos(List<Maridaje> maridajes, List<Bodega> bodegas, List<Varietal> varietales, List<Enofilo> enofilos, List<Reseña> reseñas){
        List<Object[]> vinosAReconstruir = vinoRepository.getVinos();
        List<Object[]> maridajesVino = maridajeRepository.getMaridajesVino();
        List<Object[]> varietalesVino = varietalRepository.getVarietalesVino();
        return persistencia.reconstruirVinos(vinosAReconstruir, maridajes, bodegas, varietales, enofilos,varietalesVino, maridajesVino);

    }

    public void getVinosOfBodega(long idBodega, List<Maridaje> maridajes, List<Bodega> bodegas, List<Varietal> varietales, List<Enofilo> enofilos, List<Reseña> reseñas){
        String idBodegaStr = String.valueOf(idBodega);
        List<Object[]> vinosParaReconstruir = vinoRepository.findVinoByBodega(idBodegaStr);
        for(Object[] vino : vinosParaReconstruir){
            Vino v = new Vino();
            v.setID((Integer) vino[0]);
            v.setNOMBRE((String) vino[1]);
            v.setAÑADA((Integer) vino[3]);
            v.setFECHA_ACTUALIZACION(((Timestamp) vino[4]).toLocalDateTime());
            v.setIMAGEN_ETIQUETA((String) vino[5]);
            v.setNOTA_CATA((String) vino[6]);
            v.setPRECIOARS((Integer) vino[7]);
        }
    }



}
