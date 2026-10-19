package juego;

import java.awt.Color;
import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Cuadra {
	// Posición horizontal (X) 
	double x;

	// Posición vertical (Y) 
	double y;

	// Ancho de la cuadra en píxeles
	double ancho;

	// Alto de la cuadra en píxeles
	double alto;
	// Arreglo de casas
	Casa[] casas;
	
	boolean esNegocio;
	
	
	double bLeft, bRight, bSup, bInf;

	/*Bordes de la caja de colisiones del personaje.
	bLeft = borde izquierdo
	bRight = borde derecho
	bSup = borde superior
	bInf = borde inferior */
	
	Image imagen;
	
	

	public Cuadra(double x, double y) {
		this.x = x;
		this.y = y;
		this.imagen = Herramientas.cargarImagen("vegetacion_vereda.png");
		this.ancho = imagen.getWidth(null);
		this.alto = imagen.getHeight(null);
		this.bLeft = (this.x - this.ancho/2);
		this.bRight = (this.x + this.ancho/2);
		this.bSup = (this.y + this.alto/2);
		this.bInf = (this.y - this.alto/2);
			
		}


	/**
	 * Dibuja la cuadra en la pantalla utilizando el entorno.
	 * 
	 * @param e Instancia del Entorno donde se dibuja.
	 */
	public void mostrar(Entorno e) {
		if (this.imagen != null) {
			e.dibujarImagen(this.imagen, this.x, this.y, 0, 1.0);
		}
	}

}
