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


    @Autowired
    private InterfazBD(BodegaRepository bodegaRepository, VinoRemotoRepositroy vinoRemotoRepository, MaridajeRepository maridajeRepository, VinoRepository vinoRepository, TipoUvaRepository tipoUvaRepository, UsuarioRepository usuarioRepository, VarietalRepository varietalRepository, Persistencia persistencia) {
            this.bodegaRepository = bodegaRepository;
            this.vinoRemotoRepository = vinoRemotoRepository;
            this.maridajeRepository = maridajeRepository;
            this.vinoRepository = vinoRepository;
            this.tipoUvaRepository = tipoUvaRepository;
            this.usuarioRepository = usuarioRepository;
            this.varietalRepository = varietalRepository;
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

    public List<Varietal> getVarietales(List<TipoUva> tiposUva){
        List<Object[]> varietalesAReconstruir = varietalRepository.getVarietales();
        return persistencia.reconstruirVarietal(tiposUva, varietalesAReconstruir);
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
