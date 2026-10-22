package juego;

import java.awt.Image;
import entorno.Entorno;
import entorno.Herramientas;

public class Personaje {
	// --- CONSTANTES DE DIRECCIÓN ---
	// Identificadores numéricos para representar las 4 orientaciones posibles
	public static final int ARRIBA = 0;
	public static final int DERECHA = 1;
	public static final int ABAJO = 2;
	public static final int IZQUIERDA = 3;

	// --- VARIABLES DE INSTANCIA ---
	// Dirección actual hacia donde se orienta el personaje (por defecto DERECHA = 1)
	int direccion;

	// Posición horizontal (X) del centro del personaje en la pantalla
	double x;

	// Posición vertical (Y) del centro del personaje en la pantalla
	double y;

	// Ancho de la caja del personaje para el dibujado y cálculo de colisiones
	double ancho;

	// Alto de la caja del personaje para el dibujado y cálculo de colisiones
	double alto;

	// Factor de escala aplicado a la imagen al momento de dibujarla en pantalla
	double escala;

	// Cantidad de píxeles que avanza el personaje en cada llamada al método mover
	double velocidad;

	// Ángulo de orientación actual en radianes (usado para rotar la imagen o figura)
	double angulo;
	
	int vidas;

	// Referencia a la ventana de juego (objeto Entorno) para dibujar y chequear límites
	Entorno e;
	
	double bLeft, bRight, bSup, bInf;
	/*Bordes de la caja de colisiones del personaje.
	bLeft = borde izquierdo
	bRight = borde derecho
	bSup = borde superior
	bInf = borde inferior
	*/
	
	
	// Sprite gráfico del personaje (puede ser null mientras no se cargue un archivo)
	Image imagen;
	
	
	//Variables auxiliares utilizadas en el método girar
	private double anchoOriginal;
	private double altoOriginal;

	/**
	 * Constructor principal de la clase Personaje.
	 * 
	 * @param x Posición inicial en el eje X.
	 * @param y Posición inicial en el eje Y.
	 * @param ancho Ancho del personaje / hitbox.
	 * @param alto Alto del personaje / hitbox.
	 * @param imagen Imagen cargada previamente (o null si no hay imagen).
	 * @param e Referencia a la instancia de Entorno.
	 */
	public Personaje(double x, double y, Entorno e) {
		this.x = x;
		this.y = y;
		this.escala = 0.045; // Escala por defecto
		
		this.imagen = Herramientas.cargarImagen("pj.png"); // Guarda la imagen recibida (o null)
		
		this.ancho = imagen.getWidth(null) * this.escala;
		this.anchoOriginal = this.ancho;
		
		this.alto = imagen.getHeight(null) * this.escala;
		this.altoOriginal = this.alto;
		
		this.vidas = 5;
		
		this.e = e;
		
		this.direccion = DERECHA; // Dirección inicial: mirando a la derecha
		
		this.angulo = 0; // Ángulo inicial en radianes (0 rad = mirando a la derecha)

		this.velocidad = 2.5; // Velocidad de movimiento inicial (2 píxeles por tick)
		
		this.bLeft = (this.x - this.ancho/2);
		this.bRight = (this.x + this.ancho/2);
		this.bSup = (this.y + this.alto/2);
		this.bInf = (this.y - this.alto/2);
		
		this.actualizarBordes();
	}
	
	
	public void girar(int nuevaDireccion) {
	    this.direccion = nuevaDireccion;

	    if (nuevaDireccion == ARRIBA || nuevaDireccion == ABAJO) {
	        this.ancho = this.altoOriginal * this.escala;
	        this.alto = this.anchoOriginal * this.escala;
	    } else {
	        this.ancho = this.anchoOriginal * this.escala;
	        this.alto = this.altoOriginal * this.escala;
	    }

	    this.actualizarBordes();
	}
	
	
	
	public void mover(double hor,double ver) {
		this.x += (hor * this.velocidad);
		this.y += (ver * this.velocidad);
		
		this.actualizarBordes();
	}

	/**
	 * Dibuja la imagen del personaje en el entorno (si ya hay una imagen cargada).
	 * 
	 * @param entorno Instancia del Entorno donde se debe dibujar la imagen.
	 */
	public void mostrar() {
		e.dibujarImagen(this.imagen, this.x, this.y, this.direccion*Math.PI/2, this.escala);
	}
	
	private void actualizarBordes() {
		this.bLeft = this.x - this.ancho / 2;
		this.bRight = this.x + this.ancho / 2;

		this.bSup = this.y - this.alto / 2;
		this.bInf = this.y + this.alto / 2;
	}
	
	public void perderVida() {
	    if (this.vidas > 0) {
	        this.vidas = this.vidas - 1;
	    }
	}
	
}


