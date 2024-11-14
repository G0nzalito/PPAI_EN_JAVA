package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class Persistencia {

    public Persistencia(){}

    public List<Siguiendo> reconstruirSiguiendos(List<Object[]> siguiendoAReconstruir, List<Enofilo> enofilos, List<Bodega> bodegas){

        List<Siguiendo> siguiendos = new ArrayList<>();

        for(Object[] siguiendo : siguiendoAReconstruir){

            Enofilo enofiloPropietario = enofilos.stream().filter(en -> (Integer) siguiendo[6] == en.getID()).findFirst().get();

            Enofilo enofiloSiguiendo = null;
            String SommelierSiguiendo = "";
            Bodega bodegaSiguiendo = null;

            if(siguiendo[3] != null){

                bodegaSiguiendo = bodegas.stream().filter(bod -> (Integer) siguiendo[3] == bod.getID()).findFirst().get();
                SommelierSiguiendo = "";

            } else if (siguiendo[4] != null) {
                enofiloSiguiendo = enofilos.stream().filter(en -> (Integer) siguiendo[4] == en.getID()).findFirst().get();
                SommelierSiguiendo = "";

            }else{
                SommelierSiguiendo = (String) siguiendo[5];
            }

            Siguiendo s = new Siguiendo();
            s.setID((Integer) siguiendo[0]);
            s.setFechaInicio(((Timestamp) siguiendo[1]).toLocalDateTime());
            s.setFechaFin(((Timestamp) siguiendo[2]).toLocalDateTime());
            s.setENOFILO_PROPIETARIO(enofiloPropietario);
            s.setBODEGA(bodegaSiguiendo);
            s.setENOFILO(enofiloSiguiendo);
            s.setSOMMELIER(SommelierSiguiendo);

            siguiendos.add(s);

            if (enofiloPropietario.getSeguido() == null){
                List<Siguiendo> siguiendoEnofilo = new ArrayList<>();
                siguiendoEnofilo.add(s);
                enofiloPropietario.setSeguido(siguiendoEnofilo);
            }else {
                enofiloPropietario.getSeguido().add(s);
            }
        }
        return siguiendos;
    }

    public List<Enofilo> reconstruirEnofilos(List<Object[]> enofilosAReconstruir, List<Usuario> usuarios){
        List<Enofilo> enofilos = new ArrayList<>();

        for (Object[] enofilo : enofilosAReconstruir) {

            Usuario usuarioAAgregar = usuarios.stream().filter(usu -> (Integer) enofilo[4] == usu.getID()).findFirst().get();

            Enofilo en = new Enofilo();
            en.setID((Integer) enofilo[0]);
            en.setApellido((String) enofilo[1]);
            en.setImagenPerfil((String) enofilo[2]);
            en.setNombre((String) enofilo[3]);
            en.setUsuario(usuarioAAgregar);

            enofilos.add(en);
        }
        return enofilos;
    }

    public List<Varietal>reconstruirVarietal(List<TipoUva> tipoUvas, List<Object[]> varietalesAReconstruir){
        List<Varietal> varietales = new ArrayList<>();
        Iterator<TipoUva> iterator = tipoUvas.iterator();

        for (Object[] varietal : varietalesAReconstruir){

            TipoUva tipoUva = tipoUvas.stream().filter(uva -> (Integer) varietal[1] == uva.getID()).findFirst().orElse(null);

            Varietal v = new Varietal();
            v.setID((Integer) varietal[0]);
            v.setID_UVA(tipoUva);
            v.setPORCENTAJE((Integer) varietal[2]);

            varietales.add(v);
        }
        return varietales;
    }

    public List<Vino> reconstruirVinos(List<Object[]> vinosAReconstruir, List<Maridaje> maridajes, List<Bodega> bodegas, List<Varietal> varietales, List<Enofilo> enofilos, List<Reseña> reseñas, List<Long> varietalesDeVino, List<Long> maridajesDeVino){
        List<Vino> vinos = new ArrayList<Vino>();
        List<Maridaje> maridajesAGuardar = new ArrayList<Maridaje>();
        List<Varietal> varietalesAGuardar = new ArrayList<Varietal>();

        Iterator<Long> itMar = maridajesDeVino.iterator();
        Iterator<Long> itVar = varietalesDeVino.iterator();

        for(Object[] vino : vinosAReconstruir){
            Bodega bodegaAGuardar = bodegas.stream().filter(b -> vino[2].equals(b.getID())).findFirst().orElse(null);


            //Asignamos los Maridajes
            while(itMar.hasNext()){
                Long idMaridaje = itMar.next();
                Maridaje maridajeAGuardar = maridajes.stream().filter(m -> idMaridaje.equals(m.getID())).findFirst().orElse(null);
                maridajesAGuardar.add(maridajeAGuardar);
            }

            //Asignamos los Varietales
            while(itVar.hasNext()){
                Long idVarietal = itVar.next();
                Varietal varietalAGuardar = varietales.stream().filter(v -> idVarietal.equals(v.getID())).findFirst().orElse(null);
                varietalesAGuardar.add(varietalAGuardar);
            }

            //Asignamos las reseñas
//            List<Reseña> reseñasAGuardar = reseñas.stream().filter(reseña -> reseña.getVinoId().equals(vino[0])).toList();

            //Creamos un vino vacio
            Vino v = new Vino();

            //Le asignamos los datos de la BD
            v.setID((Long) vino[0]);
            v.setNOMBRE((String) vino[1]);
            v.setBODEGA(bodegaAGuardar);
            v.setAÑADA((Integer) vino[3]);
            v.setFECHA_ACTUALIZACION(((Timestamp) vino[4]).toLocalDateTime());
            v.setIMAGEN_ETIQUETA((String) vino[5]);
            v.setNOTA_CATA((String) vino[6]);
            v.setPRECIOARS((Integer) vino[7]);
            v.setMaridajesVino(maridajesAGuardar);
            v.setVarietalesVino(varietalesAGuardar);
            v.setReseñas(null);

            //Lo agregamos al array
            vinos.add(v);
        }
        return vinos;
    }

    public List<Reseña> reconstruirReseñas(List<Vino> vinos, List<Enofilo> enofilos, List<Object[]> reseñasAReconstruir){
        List<Reseña> reseñas = new ArrayList<>();


        for (Object[] reseña : reseñasAReconstruir){
            Vino vino = vinos.stream().filter(v -> (Integer) reseña[5] == v.getID()).findFirst().orElse(null);

            Enofilo enofilo = enofilos.stream().filter(en -> (Integer) reseña[6] == en.getID()).findFirst().orElse(null);
            Reseña reseña1 = new Reseña();
            reseña1.setID((Integer) reseña[0]);
            reseña1.setCOMENTARIO((String) reseña[1]);
            reseña1.setES_PREMIUM((Boolean) reseña[2]);
            reseña1.setFECHA_RESEÑA(((Timestamp) reseña[3]).toLocalDateTime());
            reseña1.setPUNTAJE((Integer) reseña[4]);
            reseña1.setID_VINO(vino);
            reseña1.setENOFILO_PROPIETARIO(enofilo);

            reseñas.add(reseña1);
            if (vino.getReseñas() == null){
                List<Reseña> reseñasVino = new ArrayList<>();
                reseñasVino.add(reseña1);
                vino.setReseñas(reseñasVino);
            }else {
                vino.getReseñas().add(reseña1);
            }
        }
        return reseñas;
    }
}
