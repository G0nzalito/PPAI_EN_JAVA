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
}
