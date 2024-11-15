package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import com.PPAIEnJava.NuevoPPAI.Models.NoPersistent.VinoActualizado;
import com.PPAIEnJava.NuevoPPAI.Models.NoPersistent.VinoRemoto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.BinaryOperator;

@Entity
@Table(name = "Bodegas")
@Getter
@Setter
public class Bodega implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long ID;

    private String HISTORIA;

    private String NOMBRE;

    private String DESCRIPCION;


    private Integer PERIODO_ACTUALIZACION;

    private LocalDateTime FECHA_ULTIMA_ACTUALIZACION;

    private long COORDENADAS;

    @Transient
    private String region;
    @Transient
    private String novedad;

    public Bodega(String HISTORIA, String NOMBRE, String DESCRIPCION, Integer PERIDO_ACTUALIZACION, LocalDateTime FECHA_ULTIMA_ACTUALIZACION, int COORDENADAS) {
        this.HISTORIA = HISTORIA;
        this.NOMBRE = NOMBRE;
        this.DESCRIPCION = DESCRIPCION;
        this.PERIODO_ACTUALIZACION = PERIDO_ACTUALIZACION;
        this.FECHA_ULTIMA_ACTUALIZACION = FECHA_ULTIMA_ACTUALIZACION;
        this.COORDENADAS = COORDENADAS;
    }

    public Bodega(){

    }

    @Override
    public String toString() {
        return "Bodega{" +
                "ID=" + ID +
                ", HISTORIA='" + HISTORIA + '\'' +
                ", NOMBRE='" + NOMBRE + '\'' +
                ", DESCRIPCION='" + DESCRIPCION + '\'' +
                ", PERIDO_ACTUALIZACION=" + PERIODO_ACTUALIZACION +
                ", FECHA_ULTIMA_ACTUALIZACION=" + FECHA_ULTIMA_ACTUALIZACION +
                ", COORDENADAS=" + COORDENADAS +
                '}';
    }


    public void contarReseñas() {
        // Método a implementar
    }

    public List<Vino> mostrarTodosVinos(List<Vino> vinosGlobales) {
        List<Vino> vinosBodega = new ArrayList<>();
        for (Vino vino : vinosGlobales) {
            if (vino.getBODEGA().getNombre().equals(this.NOMBRE)) {
                vinosBodega.add(vino);
            }
        }
        return vinosBodega;
    }

    public Vino crearVino(VinoRemoto vinoACrear, List<Maridaje> dataMaridajes, List<Varietal> varietalesBD) {
        List<Maridaje> maridaje = this.buscarMaridaje(vinoACrear, dataMaridajes);
        List<Varietal> varietales = this.buscarVarietales(vinoACrear, varietalesBD);

        return new Vino(
                vinoACrear.getNOMBRE(),
                this,
                vinoACrear.getAÑADA(),
                LocalDateTime.now(),
                vinoACrear.getIMAGEN_ETIQUETA(),
                vinoACrear.getNOTA_CATA(),
                vinoACrear.getPRECIOARS(),
                maridaje,
                varietales,
                null
        );
    }

    public List<Maridaje> buscarMaridaje(VinoRemoto vinoACrear, List<Maridaje> dataMaridajes) {
        List<Maridaje> maridajesADevolver = new ArrayList<>();
        List<String> maridajesABuscar = Arrays.stream(vinoACrear.getMaridajes().split(",")).toList();
        for (String maridajeAAsignar : maridajesABuscar) {
            for (Maridaje maridajeEnBd : dataMaridajes) {
                if (maridajeEnBd.sosMaridaje(maridajeAAsignar)) {
                    maridajesADevolver.add(maridajeEnBd);
                    break;
                }
            }
        }
        return maridajesADevolver;
    }

    public List<Varietal> buscarVarietales(VinoRemoto vinoACrear, List<Varietal> varietales) {
        List<Varietal> varietalesADevolver = new ArrayList<>();
        List<String> varitalesABuscar = Arrays.stream((vinoACrear.getVarietales().split(","))).toList();
        Iterator<String> ItVarietales = varitalesABuscar.iterator();
        while (ItVarietales.hasNext()) {
            String nombreVarietal = ItVarietales.next();
            String porcentajeVarietal = ItVarietales.next().trim();
            for (Varietal varietal : varietales) {
                if (varietal.esDeTipoUva(nombreVarietal) && varietal.getPORCENTAJE() == Integer.parseInt(porcentajeVarietal)) {
                    varietalesADevolver.add(varietal);
                }
            }

        }
        return varietalesADevolver;
    }

    public List<VinoActualizado> actualizarVinos(List<VinoRemoto> vinosAActualizar, List<Vino> dataVinoEnBD, List<Maridaje> dataMaridajes, List<Varietal> varietalesBD) {

        List<Vino> vinosDeBodega = dataVinoEnBD.stream().filter(v -> v.esDeBodega(this.getNombre())).toList();

        List<VinoActualizado> vinosActualizados = new ArrayList<>();

        for (VinoRemoto vino : vinosAActualizar) {
            Iterator<Vino> itVinosBd = vinosDeBodega.iterator();
            boolean existia = false;
            while (itVinosBd.hasNext()) {
                Vino vinoBD = itVinosBd.next();
                System.out.println(vinoBD);
                if (vinoBD.sosVinoAActualizar(vino.getNOMBRE())){
                    System.out.println("entré");
                    vinoBD.setPRECIOARS(vino.getPRECIOARS());
                    vinoBD.setIMAGEN_ETIQUETA(vino.getIMAGEN_ETIQUETA());
                    vinoBD.setFECHA_ACTUALIZACION(vino.getFECHA_ACTUALIZACION());
                    vinoBD.setNOTA_CATA(vino.getNOTA_CATA());

                    List<String> varietalAMostrar = new ArrayList<>();
                    for (Varietal varietal : vinoBD.getVarietalesVino()) {
                        String nombreTipoUva = varietal.conocerTipoDeUva().getNOMBRE();
                        int porcentaje = varietal.getPORCENTAJE();
                        varietalAMostrar.add(nombreTipoUva + ": " + porcentaje + "%");
                    }
                    vinosActualizados.add(new VinoActualizado(
                            vinoBD,
                            "Actualizado",
                            varietalAMostrar

                    ));
                    existia = true;
                    break;
                }
            }
            if (!existia){
                Vino vinoNuevo = this.crearVino(vino, dataMaridajes, varietalesBD);
                dataVinoEnBD.add(vinoNuevo);
                List<String> varietalAMostrar = new ArrayList<>();
                for (Varietal varietal : vinoNuevo.getVarietalesVino()) {
                    String nombreTipoUva = varietal.conocerTipoDeUva().getNOMBRE();
                    int porcentaje = varietal.getPORCENTAJE();
                    varietalAMostrar.add(nombreTipoUva + ": " + porcentaje + "%");
                }
                vinosActualizados.add(new VinoActualizado(
                        vinoNuevo,
                        "Creado",
                        varietalAMostrar
                ));
            }
        }
        return vinosActualizados;
    }

    public boolean esTiempoDeActualizar(LocalDateTime fechaActual) {
        LocalDateTime fechaActualizacion = this.FECHA_ULTIMA_ACTUALIZACION;
        LocalDateTime nuevaFecha = fechaActualizacion.plusMonths(this.getPERIODO_ACTUALIZACION());
        return fechaActual.isAfter(nuevaFecha);
    }


    public String getNombre(){
        return this.NOMBRE;
    }
}
