package juego;

import java.awt.Color;
import entorno.Entorno;

public class Cuadra {
	// Posición horizontal (X) del centro de la cuadra
	double x;

	// Posición vertical (Y) del centro de la cuadra
	double y;

	// Ancho de la cuadra en píxeles
	double ancho;

	// Alto de la cuadra en píxeles
	double alto;

	public Cuadra(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
	}

	/**
	 * Dibuja la cuadra en la pantalla utilizando el entorno.
	 * 
	 * @param e Instancia del Entorno donde se dibuja.
	 */
	public void mostrar(Entorno e) {
		e.dibujarRectangulo(this.x, this.y, this.ancho, this.alto, 0, Color.GRAY);
	}

	// Getters para consultar las propiedades de la cuadra si fuera necesario
	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}
}
