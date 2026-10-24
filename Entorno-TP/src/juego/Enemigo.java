package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Enemigo {

    // Constantes de dirección (igual que en Personaje)
    public static final int ARRIBA = 0;
    public static final int DERECHA = 1;
    public static final int ABAJO = 2;
    public static final int IZQUIERDA = 3;

    double x;
    double y;
    double velocidad;
    double ancho;
    double alto;
    double bLeft;
    double bRight;
    double bSup;
    double bInf;
    double escala;

    // Dirección actual de movimiento (0=ARRIBA, 1=DERECHA, 2=ABAJO, 3=IZQUIERDA)
    int direccion;


    Image imagen;
    Entorno entorno;

    public Enemigo(double x, double y, Entorno entorno) {
        this.x = x;
        this.y = y;
        this.entorno = entorno;
        this.velocidad = 2;
        this.escala = 0.03;
        this.imagen = Herramientas.cargarImagen("enemigo.png");
        this.ancho = this.imagen.getWidth(null) * this.escala;
        this.alto = this.imagen.getHeight(null) * this.escala;

        // Dirección inicial aleatoria entre las 4 posibles
        this.direccion = (int) (Math.random() * 4);

        actualizarBordes();
    }

    public void mover() {
        // Desplazar al enemigo según su dirección actual
        if (this.direccion == ARRIBA) {
            this.y -= this.velocidad;
        } else if (this.direccion == DERECHA) {
            this.x += this.velocidad;
        } else if (this.direccion == ABAJO) {
            this.y += this.velocidad;
        } else if (this.direccion == IZQUIERDA) {
            this.x -= this.velocidad;
        }

        // Control de bordes: si sale de la pantalla, cambiar dirección de inmediato
        if (this.x <= 10 || this.x >= 790 || this.y <= 10 || this.y >= 590) {
            // Elegir nueva dirección aleatoria y reiniciar el contador
            this.direccion = (int) (Math.random() * 4);

            // Corregir posición para que no quede fuera de pantalla
            if (this.x < 10)  this.x = 10;
            if (this.x > 790) this.x = 790;
            if (this.y < 10)  this.y = 10;
            if (this.y > 590) this.y = 590;
        }

        actualizarBordes();
    }

    public void actualizarBordes() {
        this.bLeft  = this.x - this.ancho / 2;
        this.bRight = this.x + this.ancho / 2;
        this.bSup   = this.y - this.alto / 2;
        this.bInf   = this.y + this.alto / 2;
    }

    public void mostrar() {
        entorno.dibujarImagen(this.imagen, this.x, this.y, this.direccion * Math.PI / 2, this.escala);
    }
}
