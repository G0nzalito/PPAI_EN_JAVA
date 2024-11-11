package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Bodega;
import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Reseña;
import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Vino;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class Enofilo {
    private String apellido;
    private String imagenPerfil;
    private String nombre;
    private Usuario usuario;
    private List<Siguiendo> seguido;
    private List<Reseña> reseña;
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
