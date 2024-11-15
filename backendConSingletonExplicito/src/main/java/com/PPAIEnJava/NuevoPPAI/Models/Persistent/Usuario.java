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

    @Column(name = "CONTRASENIA")
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

    @Override
    public String toString() {
        return "Usuario{" +
                "ID=" + ID +
                ", contraseña='" + contraseña + '\'' +
                ", nombre='" + nombre + '\'' +
                ", premium=" + premium +
                ", cobroPremium='" + cobroPremium + '\'' +
                '}';
    }

    public boolean esTuUsuario(Usuario usuario) {
        return this.nombre.equals(usuario.getNombre());
    }
}
