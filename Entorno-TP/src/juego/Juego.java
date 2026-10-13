package juego;

import entorno.Entorno;
import entorno.InterfaceJuego;

public class Juego extends InterfaceJuego
{
	// El objeto Entorno que controla el tiempo y otros
	private Entorno entorno;
	// Instancia del personaje principal del juego
	public Personaje personaje;

	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);

		// Inicializamos el personaje en el centro de la pantalla (400, 300) con dimensiones 30x30
		this.personaje = new Personaje(400, 300, 30, 30, this.entorno);
		
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
		// Control del movimiento del personaje mediante las teclas WASD
		if (this.entorno.estaPresionada('w') || this.entorno.estaPresionada('W')) {
			this.personaje.girarArriba();
			this.personaje.mover();
		} else if (this.entorno.estaPresionada('s') || this.entorno.estaPresionada('S')) {
			this.personaje.girarAbajo();
			this.personaje.mover();
		} else if (this.entorno.estaPresionada('a') || this.entorno.estaPresionada('A')) {
			this.personaje.girarIzquierda();
			this.personaje.mover();
		} else if (this.entorno.estaPresionada('d') || this.entorno.estaPresionada('D')) {
			this.personaje.girarDerecha();
			this.personaje.mover();
		}

		// Dibuja al personaje en el entorno
		this.personaje.mostrar(this.entorno);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
}