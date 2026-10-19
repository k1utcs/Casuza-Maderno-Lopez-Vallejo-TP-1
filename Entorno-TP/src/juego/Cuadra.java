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
	
	Image imagen;
	
	

	public Cuadra(double x, double y) {
		this.x = x;
		this.y = y;
		this.imagen = Herramientas.cargarImagen("vegetacion_vereda.png");
		this.ancho = imagen.getWidth(null);
		this.alto = imagen.getHeight(null);
			
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
