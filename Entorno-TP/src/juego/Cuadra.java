package juego;

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
	
	// Angulo en radianes
	double angulo;
	
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
		this.bLeft = (this.x - this.ancho / 2);
		this.bRight = (this.x + this.ancho / 2);
		this.bSup = (this.y - this.alto / 2);
		this.bInf = (this.y + this.alto / 2);

		// Inicializar las 4 casas en las esquinas
		this.inicializarCasas();
	}

	public Cuadra(double x, double y, double ancho, double alto) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.imagen = Herramientas.cargarImagen("vegetacion_vereda.png");
		this.bLeft = (this.x - this.ancho / 2);
		this.bRight = (this.x + this.ancho / 2);
		this.bSup = (this.y - this.alto / 2);
		this.bInf = (this.y + this.alto / 2);

		// Inicializar las 4 casas en las esquinas
		this.inicializarCasas();
	}

	/**
	 * Calcula las 4 posiciones preestablecidas para las casas (una en cada esquina)
	 * respetando un margen de 4 píxeles entre el borde de la casa y el de la cuadra.
	 */
	public void inicializarCasas() {
		// Dimensiones escaladas de la casa (imagen de 1536x1024 con escala 0.01)
		double anchoCasa = 1536 * 0.055;
		double altoCasa = 1024 * 0.055;
		double margenX = -5;
		double margenY = -2;

		// Desplazamiento desde el centro de la cuadra hacia cada esquina
		double posX = (this.ancho / 2) - margenX - (anchoCasa / 2);
		double posY = (this.alto / 2) - margenY -(altoCasa / 2);

		// Inicialización del array de 4 casas (siguiendo la sintaxis del PDF)
		this.casas = new Casa[4];

		// Desplazamientos para las 4 esquinas:
		// 0: Noroeste (Arriba-Izquierda), 1: Noreste (Arriba-Derecha)
		// 2: Sudoeste (Abajo-Izquierda),  3: Sudeste (Abajo-Derecha)
		//Prestablecidos X e Y
		double[] preX = { -posX,  posX, -posX,  posX };
		double[] preY = { -posY, -posY,  posY,  posY };

		for (int i = 0; i < this.casas.length; i++) {
			this.casas[i] = new Casa(this.x + preX[i], this.y + preY[i]);
			this.casas[i].pos = i;
		}
	}

	/**
	 * Dibuja la cuadra y sus casas en la pantalla utilizando el entorno.
	 * 
	 * @param e Instancia del Entorno donde se dibuja.
	 */
	public void mostrar(Entorno e) {
		if (this.imagen != null) {
			e.dibujarImagen(this.imagen, this.x, this.y, 0, 1.0);
		}

		// Dibujar las casas de esta cuadra
			for (int i = 0; i < this.casas.length; i++) {
				this.casas[i].mostrar(e);
				}
			}
}
