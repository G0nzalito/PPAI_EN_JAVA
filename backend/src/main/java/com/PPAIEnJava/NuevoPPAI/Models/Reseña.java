package com.PPAIEnJava.NuevoPPAI.Models;

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


    private LocalDateTime FECHA_RESEÑA;

    private int PUNTAJE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "ID")
    private Vino ID_VINO;

    public Reseña(long ID, String COMENTARIO, Boolean ES_PREMIUM, LocalDateTime FECHA_RESEÑA, int PUNTAJE, Vino ID_VINO) {
        this.ID = ID;
        this.COMENTARIO = COMENTARIO;
        this.ES_PREMIUM = ES_PREMIUM;
        this.FECHA_RESEÑA = FECHA_RESEÑA;
        this.PUNTAJE = PUNTAJE;
        this.ID_VINO = ID_VINO;
    }

    public Reseña() {

    }

    @Override
    public String toString() {
        return "Reseña{" +
                "id=" + ID +
                ", COMENTARIO='" + COMENTARIO + '\'' +
                ", ES_PREMIUM=" + ES_PREMIUM +
                ", FECHA_RESEÑA=" + FECHA_RESEÑA +
                ", PUNTAJE=" + PUNTAJE +
                ", ID_VINO=" + ID_VINO +
                '}';
    }
}
