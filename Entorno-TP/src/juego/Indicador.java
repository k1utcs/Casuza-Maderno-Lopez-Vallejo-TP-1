package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Indicador {
	
	double x,y,ancho,alto,angulo,direccion;
	
	Entorno e;
	
	Image imagen;
	
	boolean activo;

	public Indicador(double x, double y, double angulo, Entorno e) {
		super();
		this.x = x;
		this.y = y;
		this.imagen = Herramientas.cargarImagen("");
		this.ancho = imagen.getWidth(null);
		this.alto = imagen.getHeight(null);
		this.angulo = angulo;
		this.e = e;
		this.activo = false;
		
	}
	
}
