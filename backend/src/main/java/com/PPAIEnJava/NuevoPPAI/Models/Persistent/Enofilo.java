package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@Entity
@Table(name = "Enofilo")
public class Enofilo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "APELLIDO")
    private String apellido;
    @Column(name = "IMAGEN_PERFIL")
    private String imagenPerfil;
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToOne
    private Usuario usuario;

    @OneToMany(mappedBy = "ENOFILO_PROPIETARIO")
    private List<Siguiendo> seguido;

    @OneToMany(mappedBy = "ENOFILO_PROPIETARIO")
    private List<Reseña> reseña;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "Vinos_de_enofilo",
            joinColumns = @JoinColumn(name = "ID_VINO"),
            inverseJoinColumns = @JoinColumn(name = "ID_ENOFILO")
    )
    private List<Vino> favorito;

    public Enofilo(String apellido, String imagenPerfil, String nombre, Usuario usuario,
                   List<Siguiendo> seguido, List<Reseña> reseña, List<Vino> favorito) {
        this.apellido = apellido;
        this.imagenPerfil = imagenPerfil;
        this.nombre = nombre;
        this.usuario = usuario;
        this.seguido = seguido != null ? seguido : List.of();
        this.reseña = reseña != null ? reseña : List.of();
        this.favorito = favorito != null ? favorito : List.of();
    }

    public Enofilo() {

    }

    public boolean estaSuscriptoABodega(Bodega bodega) {
        if (seguido == null) return false;

        for (Siguiendo seguidoItem : seguido) {
            if (seguidoItem.sosDeBodega(bodega)) return true;
        }
        return false;
    }

    public String obtenerNombreUsuario() {
        return usuario.getNombre();
    }

    public void mostrarAmigosSeguidos() {
        // Implementación pendiente
    }

    public void mostrarColeccionFavoritos() {
        // Implementación pendiente
    }

    public void enviarNotificacion() {
        // Implementación pendiente
    }

}
