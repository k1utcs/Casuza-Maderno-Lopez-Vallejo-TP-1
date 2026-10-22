package juego;

import java.awt.Image;
import javax.swing.ImageIcon;

import entorno.Entorno;
import entorno.Herramientas;

public class Enemigo {

    double x;
    double y;
    double velocidad;
    double limiteIzquierdo;
    double limiteDerecho;
    double ancho;
    double alto;
    double bLeft;
    double bRight;
    double bSup;
    double bInf;

    int direccion;

    Image imagen;
    Entorno entorno;

    public Enemigo(double x, double y, Entorno entorno) {

        this.x = x;
        this.y = y;
        this.limiteIzquierdo = 10;
        this.limiteDerecho = 770;
        this.entorno = entorno;

        this.velocidad = 1.2;
        this.direccion = 1;

        this.imagen = Herramientas.cargarImagen("enemigo.png");

        this.ancho = this.imagen.getWidth(null) * 0.04;
        this.alto = this.imagen.getHeight(null) * 0.04;

        actualizarBordes();
    }

    public void mover() {

        this.x = this.x + this.velocidad * this.direccion;

        if (this.x >= this.limiteDerecho) {
            this.direccion = -1;
        } else if (this.x <= this.limiteIzquierdo) {
            this.direccion = 1;
        }

        actualizarBordes();
    }

    public void actualizarBordes() {

        this.bLeft = this.x - this.ancho / 2;
        this.bRight = this.x + this.ancho / 2;
        this.bSup = this.y - this.alto / 2;
        this.bInf = this.y + this.alto / 2;
    }

    public void mostrar() {
        entorno.dibujarImagen(this.imagen, this.x, this.y, this.direccion*Math.PI/2, 0.04);
    }
}



