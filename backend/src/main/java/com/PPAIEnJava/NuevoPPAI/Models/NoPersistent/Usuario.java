package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

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

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getCobroPremium() {
        return cobroPremium;
    }

    public void setCobroPremium(String cobroPremium) {
        this.cobroPremium = cobroPremium;
    }

    public boolean esTuUsuario(Usuario usuario) {
        return this.nombre.equals(usuario.getNombre());
    }
}
