package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Bodega;
import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Reseña;
import com.PPAIEnJava.NuevoPPAI.Models.Persistent.Vino;

import java.util.List;

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


    public List<Siguiendo> getSeguido() {
        return seguido;
    }

    public void setSeguido(List<Siguiendo> seguido) {
        this.seguido = seguido;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
