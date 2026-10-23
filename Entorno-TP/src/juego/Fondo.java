package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {

    private Image imagen;

    public Fondo() {
        imagen = Herramientas.cargarImagen("fondo.png");

        if (imagen == null) {
            System.out.println("No se pudo cargar fondo.png");
        } else {
            System.out.println("Imagen cargada: "
                + imagen.getWidth(null) + " x "
                + imagen.getHeight(null));
        }
    }

    public void mostrar(Entorno entorno) {
        if (imagen == null) {
            System.out.println("ERROR: No se pudo cargar fondo.png");
            return;
        }

        entorno.dibujarImagen(
            imagen,
            entorno.ancho() / 2,
            entorno.alto() / 2,
            0,
            (double) entorno.ancho() / imagen.getWidth(null)
        );
    }
}