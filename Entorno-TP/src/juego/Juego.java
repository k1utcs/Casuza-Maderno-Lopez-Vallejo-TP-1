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
		

		// Inicializamos el personaje en el centro de la pantalla (400, 300) con dimensiones 30x30
		this.rappi = new Personaje(300,400, entorno);
		
		this.fondo = new Fondo();

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
}