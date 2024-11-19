package com.PPAIEnJava.NuevoPPAI.Models.NoPersistent;

import java.util.List;

public class InterfazNotificacionesPush {
    private List<String> usuariosANotificar;
    private String notificacion;

    public InterfazNotificacionesPush(List<String> usuariosANotificar, String notificacion) {
        this.usuariosANotificar = usuariosANotificar;
        this.notificacion = notificacion;
    }

    public void notificarActualizacionBodega(){
        for (String usuario : usuariosANotificar) {
            StringBuilder str = new StringBuilder();
            str.append("El usuario ").append(usuario).append(" fue notificado con exito de que ").append(notificacion);
            System.out.println(str);
        }
    }
}
