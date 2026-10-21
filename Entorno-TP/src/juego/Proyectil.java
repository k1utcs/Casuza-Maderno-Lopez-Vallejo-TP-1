package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Proyectil {
	
	double x,y,alto,ancho,velocidad,angulo,direccion,escala;
	
	Image imagen;
	
	Entorno e;
	
	public Proyectil(double x, double y,double direccion, Entorno e) {
		this.x = x;
		this.y = y;
		this.escala = 0.06;
		this.imagen = Herramientas.cargarImagen("bala.png");
		this.alto = imagen.getHeight(null) * escala;
		this.ancho = imagen.getWidth(null) * escala;
		this.direccion = direccion;
		this.e = e;	
		this.velocidad = 3;
		
	}
	
	public void mover() {
		if(this.direccion == 0){
			this.y = this.y - 1 * this.velocidad;
		}
		if(this.direccion == 1){
			this.x = this.x + 1 * this.velocidad;
		}
		if(this.direccion == 2){
			this.y = this.y + 1 * this.velocidad;
		}
		if(this.direccion == 3){
			this.x = this.x - 1 * this.velocidad;
		}
	} 
	
	public void mostrar() {
		e.dibujarImagen(imagen, x, y, this.direccion*Math.PI/2, this.escala );
	}
}

	

