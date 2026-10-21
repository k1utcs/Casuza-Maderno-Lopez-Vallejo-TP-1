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
	public Proyectil bala;
	public Enemigo[] enemigos;

	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);
		

		// Inicializamos el personaje en la pantalla
		this.rappi = new Personaje(400, 300, entorno);
		
		//Inicializamos el fondo
		this.fondo = new Fondo();

		// Inicialización del array de cuadras
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

	    // Dibujar las cuadras recorriendo el array
	    for (int i = 0; i < this.cuadras.length; i++) {
	        this.cuadras[i].mostrar(this.entorno);
	    }

	 // Movimiento del personaje

	    if (this.entorno.estaPresionada('w')) {
	        this.rappi.girar(Personaje.ARRIBA);

	        if (!hayColision(0)) {
	            this.rappi.mover(0, -1);
	        }

	    }
	    if (this.entorno.estaPresionada('s')) {
	        this.rappi.girar(Personaje.ABAJO);

	        if (!hayColision(2)) {
	            this.rappi.mover(0, 1);
	        }

	    }	
	    if (this.entorno.estaPresionada('a')) {
	       this.rappi.girar(Personaje.IZQUIERDA);
	       
	       if (!hayColision(3)) {
	            this.rappi.mover(-1, 0);
	        }
	    } 
	    if (this.entorno.estaPresionada('d')) {
	        this.rappi.girar(Personaje.DERECHA);
	        
	        if (!hayColision(1)) {
	            this.rappi.mover(1, 0);
	        }
	    }
	    
	    if(this.entorno.estaPresionado(entorno.BOTON_IZQUIERDO) && this.bala == null) {
	    	this.bala = new Proyectil(rappi.x, rappi.y, rappi.direccion, entorno);
	    }
	    
	    // Dibujar al personaje por encima del fondo
	    this.rappi.mostrar();
	    
	    
	    if(bala != null) {
	    	this.bala.mostrar();
	    	this.bala.mover();
	    	if(this.bala.x<-50 || this.bala.x > 850 || this.bala.y < -50 || this.bala.y > 650) {
	    		this.bala = null;
	    	}
	    }
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args)
	{
		Juego juego = new Juego();
	}
	
	
	//El método colision limita el movimiento del personaje 
	public static int colision(Personaje pj, Cuadra c) {

	    // Movimiento hacia arriba
	    if (Math.abs(pj.bSup - c.bInf) < 5  && pj.bLeft < c.bRight
	            && pj.bRight > c.bLeft) {
	        return 0;
	    }

	    // Movimiento hacia la derecha
	    if (Math.abs(pj.bRight - c.bLeft) < 5 && pj.bSup < c.bInf
	            && pj.bInf > c.bSup) {
	        return 1;
	    }

	    // Movimiento hacia abajo
	    if (Math.abs(pj.bInf - c.bSup) < 5 && pj.bLeft < c.bRight
	            && pj.bRight > c.bLeft) {
	        return 2;
	    }

	    // Movimiento hacia la izquierda
	    if (Math.abs(pj.bLeft - c.bRight) < 5 && pj.bSup < c.bInf
	            && pj.bInf > c.bSup) {
	        return 3;
	    }

	    return 4;
	}
	
	//Sobrecarga del método colision
	public static int colision(Personaje pj, Enemigo c) {

	    // Movimiento hacia arriba
	    if (Math.abs(pj.bSup - c.bInf) < 5  && pj.bLeft < c.bRight
	            && pj.bRight > c.bLeft) {
	        return 0;
	    }

	    // Movimiento hacia la derecha
	    if (Math.abs(pj.bRight - c.bLeft) < 5 && pj.bSup < c.bInf
	            && pj.bInf > c.bSup) {
	        return 1;
	    }

	    // Movimiento hacia abajo
	    if (Math.abs(pj.bInf - c.bSup) < 5 && pj.bLeft < c.bRight
	            && pj.bRight > c.bLeft) {
	        return 2;
	    }

	    // Movimiento hacia la izquierda
	    if (Math.abs(pj.bLeft - c.bRight) < 5 && pj.bSup < c.bInf
	            && pj.bInf > c.bSup) {
	        return 3;
	    }

	    return 4;
	}
	
	
	
	private boolean hayColision(int direccion) {

	    for (Cuadra c : this.cuadras) {
	        int resultado = colision(this.rappi, c);
	        
	        if (resultado == direccion) {
	            return true;
	        }
	    }

	    return false;
	}
		
}