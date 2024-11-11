package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Vinos")
@Getter
@Setter
public class Vino {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int IO;

    private String NOMBRE;

    @OneToOne
    @JoinColumn(referencedColumnName = "ID")
    private Bodega BODEGA;

    private int AÑADA;

    private LocalDateTime FECHA_ACTUALIZACION;

    private String IMAGEN_ETIQUETA;

    private String NOTA_CATA;

    private Integer PRECIOARS;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Maridaje_de_vino",
            joinColumns = @JoinColumn(name = "ID_VINO"),
            inverseJoinColumns = @JoinColumn(name = "ID_MARIDAJE")
    )
    private List<Maridaje> MaridajesVino;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "Varietal_de_vino",
            joinColumns = @JoinColumn(name = "ID_VINO"),
            inverseJoinColumns = @JoinColumn(name = "ID_VARIETAL")
    )
    private List<Varietal> VarietalesVino;

    @OneToMany(mappedBy = "ID_VINO")
    private List<Reseña> reseñas;

    public Vino() {

    }


    public boolean sosVinoAActualizar(List<Vino> dataVinoEnBD) {
        return dataVinoEnBD.stream().anyMatch(v -> v.NOMBRE.equals(this.NOMBRE));
    }

    public Vino(String NOMBRE, Bodega BODEGA, int AÑADA, LocalDateTime FECHA_ACTUALIZACION, String IMAGEN_ETIQUETA, String NOTA_CATA, Integer PRECIOARS, List<Maridaje> maridajesVino, List<Varietal> varietalesVino, List<Reseña> reseñas) {
        this.NOMBRE = NOMBRE;
        this.BODEGA = BODEGA;
        this.AÑADA = AÑADA;
        this.FECHA_ACTUALIZACION = FECHA_ACTUALIZACION;
        this.IMAGEN_ETIQUETA = IMAGEN_ETIQUETA;
        this.NOTA_CATA = NOTA_CATA;
        this.PRECIOARS = PRECIOARS;
        MaridajesVino = maridajesVino;
        VarietalesVino = varietalesVino;
        this.reseñas = reseñas;
    }

    public int calcularRanking() {
        int contador = 0;
        int suma = 0;

        for (Reseña element : reseñas) {
            contador++;
            int puntaje = element.getPUNTAJE();
            suma += puntaje;
        }

        return contador > 0 ? Math.round((float) suma / contador) : 0;
    }

    public List<String> getVarietalesAMostrar() {
        List<String> varietalesAMostrar = new ArrayList<>();
        for (Varietal varietal : getVarietalesVino()) {
            String nombreTipoUva = varietal.conocerTipoDeUva().getNOMBRE();
            int porcentaje = varietal.getPORCENTAJE();
            varietalesAMostrar.add(nombreTipoUva + ": " + porcentaje + "%");
        }
        return varietalesAMostrar;
    }

    public boolean esDeBodega(String nombreBodega) {
        return this.BODEGA != null && this.BODEGA.getNOMBRE().equals(nombreBodega);
    }

    public boolean esTuNombre(String nombre) {
        return this.NOMBRE.equals(nombre);
    }

}
