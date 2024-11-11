import com.PPAIEnJava.NuevoPPAI.Models.Persistent.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppPrueba {
    public static void main(String[] args) {
        System.out.println("Hola");
        Bodega bodega = new Bodega("Historia", "Nombre", "Descripcion", 4, LocalDateTime.now(), 34);
        TipoUva uva = new TipoUva("Soy una uva", "Soy una descripcion");
        Varietal varietal = new Varietal(uva, 30);
        List<Varietal> Varietales = new ArrayList<>();
        Varietales.add(varietal);

        Maridaje maridaje = new Maridaje("Maridaje", "Soy una descripcion");
        List<Maridaje> Maridajes = new ArrayList<>();
        Maridajes.add(maridaje);
        Reseña reseña = new Reseña("Comento", true, LocalDateTime.now(), 13, null);
        List<Reseña> Reseñas = new ArrayList<>();
        Reseñas.add(reseña);
        Vino vino = new Vino("soy un Vino!", bodega, 2, LocalDateTime.now(), "Imagen de vino", "Soy una nota", 2, Maridajes, Varietales, Reseñas);


    }
}
