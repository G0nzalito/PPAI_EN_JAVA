package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Usuario {
    private String contraseña;
    private String nombre;
    private boolean premium;
    private String cobroPremium;

    public Usuario(String contra, String nom, boolean prem, String cobroPrem) {
        this.contraseña = contra;
        this.nombre = nom;
        this.premium = prem;
        this.cobroPremium = cobroPrem;
    }

    public boolean esTuUsuario(Usuario usuario) {
        return this.nombre.equals(usuario.getNombre());
    }
}
