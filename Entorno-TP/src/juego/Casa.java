package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Casa {
	
	double x, y, escala, angulo, ancho, alto;
	
	Entorno e;
	
	Image imagen;
	
	String clase;
	
	// Identificador de la posición preestablecida (0 a 3)
	int pos;

	public Casa(double x, double y) {
		this(x, y, null);
	}

	public Casa(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.e = e;
		this.imagen = Herramientas.cargarImagen("casa1.png");
		this.clase = "Casa";
		this.escala = 0.06;
		this.angulo = 0;
		// Dimensiones escaladas reales en pantalla
		this.ancho = this.imagen.getWidth(null) * this.escala;
		this.alto = this.imagen.getHeight(null) * this.escala;
	}

	/**
	 * Dibuja la casa en la pantalla mediante el entorno.
	 * 
	 * @param entorno Instancia de Entorno donde se dibuja.
	 */
	public void mostrar(Entorno e) {
		if (e != null && this.imagen != null) {
			e.dibujarImagen(this.imagen, this.x, this.y, this.angulo, this.escala);
		}
	}
}

