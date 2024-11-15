import com.PPAIEnJava.NuevoPPAI.Models.Persistent.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppPrueba {
    public static void main(String[] args) {
        System.out.println("Probando ando: ");
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
        List<Vino>Vinos = new ArrayList<>();
        Vinos.add(vino);
        Vino vino2 = new Vino("soy un Vino2!", bodega, 2, LocalDateTime.now(), "Imagen de vino", "Soy una nota2", 2, Maridajes, Varietales, Reseñas);
        Vinos.add(vino2);



        System.out.println(
        uva.mostrarNombre()
        );
        System.out.println( reseña.getFECHA_RESEÑA());
        System.out.println(reseña.esPremium());
        System.out.println(vino.calcularRanking());
//<        System.out.println(vino.sosVinoAActualizar(Vinos));>
        System.out.println(Vinos);

    }
}
