package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	// Instancia del personaje principal del juego
	public Personaje rappi;
	public Cuadra[] cuadras;
	public Fondo fondo; //creacion de fondo


	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		

		// Inicializamos el personaje en la pantalla
		this.rappi = new Personaje(300, 400, entorno);
		
		this.fondo = new Fondo();

		// Inicialización del array de cuadras (siguiendo la sintaxis del PDF de Arrays de objetos)
		this.cuadras = new Cuadra[16];

		// Centros (X e Y) calculados para los espacios vacíos del mapa
		double[] posx = { 113, 304, 495, 683 };
		double[] posy = { 53.0, 201.0, 348.0, 496.0 };
		

		int k = 0;
		for (int f = 0; f < posy.length; f++) {
			for (int c = 0; c < posx.length; c++) {
				this.cuadras[k++] = new Cuadra(posx[c], posy[f]);
			}
		}

		// Inicia el juego!
		this.entorno.iniciar();
	}

	/**
	 * Durante el juego, el método tick() será ejecutado en cada instante y 
	 * por lo tanto es el método más importante de esta clase. Aquí se debe 
	 * actualizar el estado interno del juego para simular el paso del tiempo 
	 * (ver el enunciado del TP para mayor detalle).
	 */
	
	public void tick()
	{
	    // Dibujar primero el fondo
	    this.fondo.mostrar(this.entorno);

	    // Dibujar las cuadras recorriendo el array (sintaxis del PDF)
	    for (int i = 0; i < this.cuadras.length; i++) {
	        this.cuadras[i].mostrar(this.entorno);
	    }

	    // Movimiento del personaje
	    if (this.entorno.estaPresionada('w')) {
	        this.rappi.direccion = Personaje.ARRIBA;
	        this.rappi.mover(0, -1);
	    } else if (this.entorno.estaPresionada('s')) {
	        this.rappi.direccion = Personaje.ABAJO;
	        this.rappi.mover(0, 1);
	    } else if (this.entorno.estaPresionada('a')) {
	        this.rappi.direccion = Personaje.IZQUIERDA;
	        this.rappi.mover(-1, 0);
	    } else if (this.entorno.estaPresionada('d')) {
	        this.rappi.direccion = Personaje.DERECHA;
	        this.rappi.mover(1, 0);
	    }

	    // Dibujar al personaje por encima del fondo
	    this.rappi.mostrar(this.entorno);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
	public static int colision(Personaje pj, Cuadra c) {
		//Agregrar Math.abs 
		if( Math.abs(pj.bLeft - c.bRight ) < 5 
				&& (pj.bSup < c.bInf) && (pj.bInf > c.bSup)) {
			return 3;
		}
		if( Math.abs(pj.bRight - c.bLeft ) < 5 
				&& (pj.bSup < c.bInf) && (pj.bInf > c.bSup)) {
			return 3;
		}
		if( Math.abs(pj.bSup - c.bInf ) < 5 
				&& (pj.bLeft < c.bRight) && (pj.bRight > c.bLeft)) {
			return 0;
		}
		if( Math.abs(pj.bInf - c.bSup ) < 5 
				&& (pj.bLeft < c.bRight) && (pj.bRight > c.bLeft)){
			return 2;
		}
		return 4;
	}
		
}