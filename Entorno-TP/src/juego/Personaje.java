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

	// Referencia a la ventana de juego (objeto Entorno) para dibujar y chequear límites
	Entorno e;

	// Sprite gráfico del personaje (puede ser null mientras no se cargue un archivo)
	Image imagen;

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
	public Personaje(double x, double y, double ancho, double alto, Image imagen, Entorno e) {
		this.x = x;
		this.y = y;
		this.ancho = ancho;
		this.alto = alto;
		this.imagen = imagen; // Guarda la imagen recibida (o null)
		this.e = e;
		this.direccion = DERECHA; // Dirección inicial: mirando a la derecha
		this.angulo = 0; // Ángulo inicial en radianes (0 rad = mirando a la derecha)
		this.escala = 0.2; // Escala por defecto
		this.velocidad = 2.0; // Velocidad de movimiento inicial (2 píxeles por tick)
	}

	/**
	 * Constructor alternativo para cuando aún no se dispone de una imagen gráfica.
	 */
	public Personaje(double x, double y, double ancho, double alto, Entorno e) {
		this(x, y, ancho, alto, null, e);
	}

	/**
	 * Gira el personaje hacia una de las 4 direcciones (ARRIBA, DERECHA, ABAJO, IZQUIERDA)
	 * y actualiza el ángulo en radianes para rotar el gráfico acordemente.
	 * 
	 * @param nuevaDireccion Entero que representa la nueva dirección (0, 1, 2 o 3).
	 */
	public void girar(int nuevaDireccion) {
		this.direccion = nuevaDireccion;

		// En Entorno el eje Y crece hacia abajo:
		// 0 rad = Derecha, PI/2 rad = Abajo, PI rad = Izquierda, 3*PI/2 rad (o -PI/2) = Arriba
		if (nuevaDireccion == ARRIBA) {
			this.angulo = 3 * Math.PI / 2; // Apunta hacia arriba (270°)
		} else if (nuevaDireccion == DERECHA) {
			this.angulo = 0;               // Apunta hacia la derecha (0°)
		} else if (nuevaDireccion == ABAJO) {
			this.angulo = Math.PI / 2;     // Apunta hacia abajo (90°)
		} else if (nuevaDireccion == IZQUIERDA) {
			this.angulo = Math.PI;         // Apunta hacia la izquierda (180°)
		}
	}

	// Métodos auxiliares específicos para girar cómodamente en cada dirección
	public void girarArriba() {
		girar(ARRIBA);
	}

	public void girarDerecha() {
		girar(DERECHA);
	}

	public void girarAbajo() {
		girar(ABAJO);
	}

	public void girarIzquierda() {
		girar(IZQUIERDA);
	}

	/**
	 * Desplaza al personaje un paso según su dirección actual y su velocidad.
	 * Además, evita que el personaje se escape de los límites de la pantalla del juego.
	 */
	public void mover() {
		// Movimiento en los ejes según la dirección actual
		if (this.direccion == ARRIBA) {
			this.y -= this.velocidad;
		} else if (this.direccion == ABAJO) {
			this.y += this.velocidad;
		} else if (this.direccion == DERECHA) {
			this.x += this.velocidad;
		} else if (this.direccion == IZQUIERDA) {
			this.x -= this.velocidad;
		}

		// Control de bordes: restringe la posición para que el personaje no salga de la pantalla
		if (this.e != null) {
			// Borde izquierdo
			if (this.x - this.ancho / 2 < 0) {
				this.x = this.ancho / 2;
			}
			// Borde derecho
			if (this.x + this.ancho / 2 > this.e.ancho()) {
				this.x = this.e.ancho() - this.ancho / 2;
			}
			// Borde superior
			if (this.y - this.alto / 2 < 0) {
				this.y = this.alto / 2;
			}
			// Borde inferior
			if (this.y + this.alto / 2 > this.e.alto()) {
				this.y = this.e.alto() - this.alto / 2;
			}
		}
	}

	/**
	 * Sobrecarga que permite cambiar la orientación y mover en un solo paso.
	 * 
	 * @param direccion Dirección hacia la cual orientarse y desplazarse.
	 */
	public void mover(int direccion) {
		this.girar(direccion);
		this.mover();
	}

	/**
	 * Verifica si el personaje colisiona con una Cuadra.
	 * Utiliza la detección de superposición de cajas alineadas a los ejes (AABB),
	 * calculando la distancia entre los centros respecto a la suma de sus semianchos y semialtos.
	 * 
	 * @param c Objeto Cuadra con el cual se evalúa la colisión.
	 * @return true si hay colisión (superposición), false en caso contrario.
	 */
	public boolean colision(Cuadra c) {
		if (c == null) {
			return false;
		}

		// Distancia absoluta entre los centros en los ejes X e Y
		double distanciaX = Math.abs(this.x - c.x);
		double distanciaY = Math.abs(this.y - c.y);

		// Distancia mínima requerida para que no se superpongan
		double sumaMediosAnchos = (this.ancho / 2) + (c.ancho / 2);
		double sumaMediosAltos = (this.alto / 2) + (c.alto / 2);

		// Hay colisión si se superponen en ambos ejes simultáneamente
		return (distanciaX < sumaMediosAnchos) && (distanciaY < sumaMediosAltos);
	}

	/**
	 * Dibuja la imagen del personaje en el entorno (si ya hay una imagen cargada).
	 * 
	 * @param entorno Instancia del Entorno donde se debe dibujar la imagen.
	 */
	public void mostrar(Entorno entorno) {
		entorno.dibujarImagen(this.imagen, this.x, this.y, this.angulo, this.escala);
		
	}

	/**
	 * Sobrecarga de mostrar sin parámetros que utiliza la referencia de Entorno
	 * almacenada en el atributo 'this.e' del personaje.
	 */
	public void mostrar() {
		this.mostrar(this.e);
	}

	/**
	 * Permite cargar y asignar una imagen al personaje a través de la clase Herramientas.
	 * 
	 * @param archivo Nombre o ruta del archivo de imagen (ejemplo: "PJ.jpg").
	 */
	public void cargarImagen(String archivo) {
		this.imagen = Herramientas.cargarImagen(archivo);
	}

	// --- GETTERS Y SETTERS ---
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getAncho() {
		return ancho;
	}

	public double getAlto() {
		return alto;
	}

	public double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(double velocidad) {
		this.velocidad = velocidad;
	}

	public int getDireccion() {
		return direccion;
	}

	public double getAngulo() {
		return angulo;
	}

	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
}
