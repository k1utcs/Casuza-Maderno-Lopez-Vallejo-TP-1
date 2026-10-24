
package juego;

import java.awt.Image;
import java.util.Random;

import entorno.Entorno;
import entorno.Herramientas;

public class Casa {

    double x, y, escala, angulo, ancho, alto;

    Entorno e;
    Image imagen;
    String clase;
    Indicador flecha;
    int pos;

    // Arreglo con los dos tipos de casas y los dos tipos de negocios
    private static final Image[] EDIFICIOS = {
        Herramientas.cargarImagen("casa1.png"),
        Herramientas.cargarImagen("casa2.png"),
        Herramientas.cargarImagen("negocio1.png"),
        Herramientas.cargarImagen("negocio2.png")
    };

    private static final Random RANDOM = new Random();

    public Casa(double x, double y) {
        this(x, y, null);
    }

    public Casa(double x, double y, Entorno e) {
        this.x = x;
        this.y = y;
        this.e = e;
        this.escala = 0.2;
        this.angulo = 0;

        generarTipo();
    }

    public void generarTipo() {
        // Elegir una imagen al azar
        int indice = RANDOM.nextInt(EDIFICIOS.length);
        this.imagen = EDIFICIOS[indice];

        // Actualizar las dimensiones de la imagen seleccionada
        this.ancho = this.imagen.getWidth(null) * this.escala;
        this.alto = this.imagen.getHeight(null) * this.escala;

        // Identificar el tipo de edificio
        if (indice < 2) {
            this.clase = "Casa";
        } else {
            this.clase = "Negocio";
        }
    }

    public void mostrar(Entorno e) {
        e.dibujarImagen(
            this.imagen, this.x, this.y, this.angulo, this.escala
        );
    }
}

