package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	
	double x,y,alto,ancho,velocidad,angulo;

	Entorno e;
	
	public Proyectil(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.imagen = Herramientas.cargarImagen("bala.png");
		this.alto = imagen.getHeight(null);
		this.ancho = imagen.getWidth(null);
		this.e = e;
	}








	Image imagen;
	
	
	
	
	
	
	
	
	public void mover(double hor,double ver) {
		this.x += (hor * this.velocidad);
		this.y += (ver * this.velocidad);	
	}
	

}
