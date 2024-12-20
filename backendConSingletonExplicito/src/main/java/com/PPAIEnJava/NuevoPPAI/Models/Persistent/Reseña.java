package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Reseñas")
@Getter
@Setter
public class Reseña {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String COMENTARIO;

    private Boolean ES_PREMIUM;

    @Column(name = "FECHA_RESENIA")
    private LocalDateTime FECHA_RESEÑA;

    private int PUNTAJE;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "ID", name = "ID_VINO")
    private Vino Vino;

    @ManyToOne
    @JoinColumn(name = "ENOFILO_PROPIETARIO")
    private Enofilo ENOFILO_PROPIETARIO;

    public Reseña(String COMENTARIO, Boolean ES_PREMIUM, LocalDateTime FECHA_RESEÑA, int PUNTAJE, Vino ID_VINO) {
        this.COMENTARIO = COMENTARIO;
        this.ES_PREMIUM = ES_PREMIUM;
        this.FECHA_RESEÑA = FECHA_RESEÑA;
        this.PUNTAJE = PUNTAJE;
        this.Vino = ID_VINO;
    }

    public Reseña() {

    }

    public Long getVinoId(){
        return this.Vino.getID();
    }

    @Override
    public String toString() {
        return "Reseña{" +
                "id=" + ID +
                ", COMENTARIO='" + COMENTARIO + '\'' +
                ", ES_PREMIUM=" + ES_PREMIUM +
                ", FECHA_RESEÑA=" + FECHA_RESEÑA +
                ", PUNTAJE=" + PUNTAJE +
                ", ID_VINO=" + Vino.getID() +
                '}';
    }

    public boolean esPremium() {
        return false;
    }
    public void sosDeEnofilo() {}
    public void sosDeSomellier() {}

}
