package juego;

import java.awt.Image;

import entorno.Entorno;
import entorno.Herramientas;

public class Casa {
	
	String clase;
	
	//int pos;
	//Determina la posición prestablecida que se le va a asignar
	
	double ancho,alto,x,y;
	//Determinan la posición de la casa
	Image imagen;
	
	Entorno e;


	public Casa(double x, double y, String clase, Entorno e) {
		//this.pos = pos;
		this.x = x;
		this.y = y;
		this.imagen = Herramientas.cargarImagen("vegetacion_parque.png");
		this.alto = imagen.getHeight(null);
		this.ancho = imagen.getWidth(null);
		this.clase = clase;
		
		
		
	}
	
}

