package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Fondo {

    private Image imagen;

    public Fondo() {
        imagen = Herramientas.cargarImagen("fondo.png");
    }

    public void mostrar(Entorno entorno) {
        entorno.dibujarImagen(
            imagen,
            entorno.ancho() / 2,
            entorno.alto() / 2,
            0,
            800.0 / imagen.getWidth(null)
        );
    }
}