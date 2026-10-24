package juego;

import java.awt.Color;

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
	private boolean tocandoEnemigo;

	
	// Posiciones preestablecidas sobre las calles del mapa.
	// Calles horizontales (Y fijo): 24 (borde sup), 167 (entre fila 1-2), 447 (entre fila 3-4), 584 (borde inf)
	// Calles verticales (intersecciones): X = 208, 400, 591
	private static final double[] SPAWN_X = {207,400,590,207,400,590,207,400,590};
	private static final double[] SPAWN_Y = {166,166,166,305,305,305,446,446,446};

	Juego()
	{
		// Inicializa el objeto entorno
		this.entorno = new Entorno(this, "Proyecto para TP", 800, 600);

		// Centros (X e Y) calculados para los espacios vacíos del mapa
		double[] posx = {113, 303, 495, 686};
		double[] posy = {116, 247, 379, 511};
		
		// Inicializamos el personaje en la pantalla
		this.rappi = new Personaje(400, 300, entorno);

		//Inicializamos el fondo
		this.fondo = new Fondo();

		// Inicialización del array de cuadras
		this.cuadras = new Cuadra[16];

		// Inicializamos el array de 4 enemigos en posiciones sobre las calles
		this.enemigos = new Enemigo[4];
		this.respawnEnemigos();
		

		int k = 0;
		for (double element : posy) {
			for (double element2 : posx) {
				this.cuadras[k++] = new Cuadra(element2, element);
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

	@Override
	public void tick()
	{
		
	    // Dibujar primero el fondo
	    this.fondo.mostrar(this.entorno);

	    // Dibujar las cuadras recorriendo el array
	    for (Cuadra element : this.cuadras) {
	        element.mostrar(this.entorno);
	    }

	 // Movimiento del personaje

	    if (this.entorno.estaPresionada('w')) {
	        this.rappi.girar(Personaje.ARRIBA);

	        if (!hayColision(0) && this.rappi.y > 6) {
	            this.rappi.mover(0, -1);
	        }
	    }
	    if (this.entorno.estaPresionada('s')) {
	        this.rappi.girar(Personaje.ABAJO);

	        if (!hayColision(2) && this.rappi.y < 594) {
	            this.rappi.mover(0, 1);
	        }

	    }
	    if (this.entorno.estaPresionada('a')) {
	       this.rappi.girar(Personaje.IZQUIERDA);

	       if (!hayColision(3) && this.rappi.x > 6) {
	            this.rappi.mover(-1, 0);
	        }
	    }
	    if (this.entorno.estaPresionada('d')) {
	        this.rappi.girar(Personaje.DERECHA);

	        if (!hayColision(1) && this.rappi.x < 794) {
	            this.rappi.mover(1, 0);
	        }
	    }
	    
	    //Dispara proyectiles si no hay uno existente
	    if(this.entorno.estaPresionado(entorno.BOTON_IZQUIERDO) && this.bala == null) {
	    	this.bala = new Proyectil(rappi.x, rappi.y, rappi.direccion, entorno);
	    }
	    
	   
	    // Dibujar al personaje por encima del fondo
	    this.rappi.mostrar();

	    
	    boolean hayContacto = false;
	    
	    for (int i = 0; i < this.enemigos.length; i++) {
	        if (this.enemigos[i] != null) {
	            this.enemigos[i].mover();
	            this.enemigos[i].mostrar();

	            if (colisionEnemigo(this.rappi, this.enemigos[i])) {
	                hayContacto = true;
	            }
	        }
	    }
	    
	    //Se utilizan variables auxiliares para restar vidas al personaje 1 vez por superposición
	    // Y no una vez por tick 
	    if (hayContacto && !this.tocandoEnemigo) {
	    	this.rappi.perderVida();
	    }
	    
	    this.tocandoEnemigo = hayContacto;



	    boolean impacto = false; //Variable bandera para finalizar el ciclo
	    
	    
	    if (this.bala != null) {
	    	this.bala.mostrar();
	    	this.bala.mover();
	    	if(this.bala.x < -20 || this.bala.x > 820 || this.bala.y < -20 || this.bala.y > 620) {
	    		this.bala = null;
	    	}
	        for (int i = 0; i < this.enemigos.length; i++) {
	            if (this.enemigos[i] != null && impacto == false && this.bala != null) {
	                if (colisionEnemigo(this.bala, this.enemigos[i])) {
	                    this.enemigos[i] = null;
	                    this.bala = null;
	                    impacto = true;
	                }
	               	
	            }
	        }
	    }
	    
	    // Reponer inmediatamente los enemigos eliminados para que siempre haya 4
	    this.respawnEnemigos();
	    
	    this.mostrarEstadisticas();
	    
	    this.terminarJuego();
	    
	    
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
	

	public static boolean colisionEnemigo(Personaje pj, Enemigo en) {

		if (pj.bRight > en.bLeft
            && pj.bLeft < en.bRight
            && pj.bInf > en.bSup
            && pj.bSup < en.bInf) {

        return true;

    	}
        return false;
	}
	public static boolean colisionEnemigo(Proyectil bala, Enemigo en) {

		if (bala.x > en.bLeft
            && bala.x < en.bRight
            && bala.y > en.bSup
            && bala.y < en.bInf) {

        return true;

    	}
        return false;
	}
	
// Método que que dibuja información para el jugador 
public void mostrarEstadisticas() {
	
	this.entorno.dibujarRectangulo(400, 20, 800, 40, 0, Color.BLACK);

    // Cambiar la fuente y el color del texto
    this.entorno.cambiarFont("Arial", 18, Color.WHITE);

    // Mostrar las vidas del personaje
    this.entorno.escribirTexto("Vidas: " + this.rappi.vidas, 80, 25);

    // Contar los enemigos restantes
    int cantidadEnemigos = 0;

    for (int i = 0; i < this.enemigos.length; i++) {
        if (this.enemigos[i] != null) {
            cantidadEnemigos++;
        }
    }

    this.entorno.escribirTexto(
        "Enemigos: " + cantidadEnemigos, 250, 25
    );

    // Mostrar el estado del proyectil
    if (this.bala == null) {
        this.entorno.escribirTexto("Proyectil: Disponible", 480, 25);
    } else {
        this.entorno.escribirTexto("Proyectil: En uso", 480, 25);
    }
}
	public void respawnEnemigos() {
		for (int i = 0; i < this.enemigos.length; i++) {
			if (this.enemigos[i] == null) {
				// Elegir un índice aleatorio entre las posiciones preestablecidas
				int pos = (int) (Math.random() * SPAWN_X.length);
				this.enemigos[i] = new Enemigo(SPAWN_X[pos], SPAWN_Y[pos], this.entorno);
			}
		}
		
	}
	public void terminarJuego() {
		if (this.rappi.vidas <= 0) {
			this.entorno.dibujarRectangulo(400, 300, 800, 600, 0, Color.BLACK);
			entorno.cambiarFont("Arial", 40, Color.WHITE);
	        entorno.escribirTexto("GAME OVER", 250, 300);
		}
	}
	public void ganaste() {
		//if (this.pedidos.entregados = 20) {
			this.entorno.dibujarRectangulo(400, 300, 800, 600, 0, Color.BLACK);
			entorno.cambiarFont("Arial", 40, Color.WHITE);
	        entorno.escribirTexto("GANASTE", 290, 300);
		//}
		
	}



}
