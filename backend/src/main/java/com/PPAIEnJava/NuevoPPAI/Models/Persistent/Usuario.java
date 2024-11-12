package com.PPAIEnJava.NuevoPPAI.Models.Persistent;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    @Column(name = "CONTRASEÑA")
    private String contraseña;
    @Column(name = "NOMBRE")
    private String nombre;
    @Column(name = "PREMIUM")
    private boolean premium;
    @Column(name = "COBRO_PREMIUM")
    private String cobroPremium;

    public Usuario(String contra, String nom, boolean prem, String cobroPrem) {
        this.contraseña = contra;
        this.nombre = nom;
        this.premium = prem;
        this.cobroPremium = cobroPrem;
    }

    public Usuario() {

    }

    public boolean esTuUsuario(Usuario usuario) {
        return this.nombre.equals(usuario.getNombre());
    }
}
