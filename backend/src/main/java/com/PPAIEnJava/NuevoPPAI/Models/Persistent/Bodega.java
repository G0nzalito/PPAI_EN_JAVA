package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Bodegas")
@Getter
@Setter
public class Bodega {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long ID;

    private String HISTORIA;

    private String NOMBRE;

    private String DESCRIPCION;

    private Integer PERIDO_ACTUALIZACION;

    private LocalDateTime FECHA_ULTIMA_ACTUALIZACION;

    private int COORDENADAS;

    private String region;

    private String novedad;

    public Bodega(String HISTORIA, String NOMBRE, String DESCRIPCION, Integer PERIDO_ACTUALIZACION, LocalDateTime FECHA_ULTIMA_ACTUALIZACION, int COORDENADAS) {
        this.ID = ID;
        this.HISTORIA = HISTORIA;
        this.NOMBRE = NOMBRE;
        this.DESCRIPCION = DESCRIPCION;
        this.PERIDO_ACTUALIZACION = PERIDO_ACTUALIZACION;
        this.FECHA_ULTIMA_ACTUALIZACION = FECHA_ULTIMA_ACTUALIZACION;
        this.COORDENADAS = COORDENADAS;
    }

    @Override
    public String toString() {
        return "Bodega{" +
                "ID=" + ID +
                ", HISTORIA='" + HISTORIA + '\'' +
                ", NOMBRE='" + NOMBRE + '\'' +
                ", DESCRIPCION='" + DESCRIPCION + '\'' +
                ", PERIDO_ACTUALIZACION=" + PERIDO_ACTUALIZACION +
                ", FECHA_ULTIMA_ACTUALIZACION=" + FECHA_ULTIMA_ACTUALIZACION +
                ", COORDENADAS=" + COORDENADAS +
                '}';
    }

    public Bodega() {

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

    public Vino crearVino(Vino vinoACrear, List<Maridaje> dataMaridajes) {
        List<Maridaje> maridaje = this.buscarMaridaje(vinoACrear, dataMaridajes);
        List<Varietal> varietales = this.buscarVarietales(vinoACrear);

        return new Vino(
                vinoACrear.getNOMBRE(),
                this,
                vinoACrear.getAÑADA(),
                LocalDateTime.now(),
                vinoACrear.getIMAGEN_ETIQUETA(),
                vinoACrear.getNOTA_CATA(),
                vinoACrear.getPRECIOARS(),
                maridaje,
                null,
                vinoACrear.getReseñas()
        );
    }

    public List<Maridaje> buscarMaridaje(Vino vinoACrear, List<Maridaje> dataMaridajes) {
        List<Maridaje> maridajesADevolver = new ArrayList<>();
        for (Maridaje maridajeAAsignar : vinoACrear.getMaridaje()) {
            for (Maridaje maridajeEnBd : dataMaridajes) {
                if (maridajeEnBd.sosMaridaje(maridajeAAsignar.getNOMBRE())) {
                    maridajesADevolver.add(maridajeEnBd);
                    break;
                }
            }
        }
        return maridajesADevolver;
    }

    public List<Varietal> buscarVarietales(Vino vinoACrear) {
        List<Varietal> Varietales = new ArrayList<>();
        for (Varietal varietal : vinoACrear.getVarietal()) {
            Varietales.add(new Varietal(varietal.getID_UVA(), varietal.getPORCENTAJE()));
        }
        return Varietales;
    }

    public List<VinoActualizado> actualizarVinos(List<Vino> vinosAActualizar, List<Vino> dataVinoEnBD) {
        List<VinoActualizado> vinosActualizados = new ArrayList<>();

        for (Vino vino : vinosAActualizar) {
            if (vino.sosVinoAActualizar(dataVinoEnBD)) {
                Vino vinoAActualizar = dataVinoEnBD.stream()
                        .filter(v -> v.esTuNombre(vino.getNOMBRE()))
                        .findFirst()
                        .orElse(null);

                if (vinoAActualizar != null) {
                    List<String> varietalAMostrar = vino.getVarietalesAMostrar();

                    vinosActualizados.add(new VinoActualizado(
                            vino,
                            "Actualizado",
                            varietalAMostrar
                    ));

                    vino.setPRECIOARS(vinoAActualizar.getPRECIOARS());
                    vino.setIMAGEN_ETIQUETA(vinoAActualizar.getIMAGEN_ETIQUETA());
                    vino.setFECHA_ACTUALIZACION(vinoAActualizar.getFECHA_ACTUALIZACION());
                    vino.setNOTA_CATA(vinoAActualizar.getNOTA_CATA());
                }
            } else {
                this.crearVino(vino, null);

                List<String> varietalAMostrar = new ArrayList<>();
                for (Varietal varietal : vino.getVarietal()) {
                    String nombreTipoUva = varietal.conocerTipoDeUva().getNOMBRE();
                    int porcentaje = varietal.getPORCENTAJE();
                    varietalAMostrar.add(nombreTipoUva + ": " + porcentaje + "%");
                }

                vinosActualizados.add(new VinoActualizado(
                        vino,
                        "Creado",
                        varietalAMostrar
                ));

                dataVinoEnBD.add(vino);
            }
        }

        return vinosActualizados;
    }

    public boolean esTiempoDeActualizar(LocalDateTime fechaActual) {
        LocalDateTime fechaActualizacion = this.FECHA_ULTIMA_ACTUALIZACION;
        LocalDateTime nuevaFecha = fechaActualizacion.plusMonths(this.getPERIDO_ACTUALIZACION());
        return fechaActual.isAfter(fechaActualizacion);
    }


    public String getNombre(){
        return this.NOMBRE;
    }
}
