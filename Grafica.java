//Proyecto final EDD 
//Calculadora graficadora de funciones
//Juan Diego Valenzuela - A00825349

import java.awt.Graphics;
import java.util.ArrayList;

public class Grafica {
	public static int abajo = Pantalla.height/2 +60,
					  arriba = 60,
					  izquierda = Pantalla.width/2 - 150,
					  derecha = Pantalla.width/2 + 150,
					  centroAncho = (izquierda+derecha)/2,
					  centroAlto = (abajo+arriba)/2;
	
					
	
	public static int espacioRayitas = 15,
							radioPunto = 3;
	
	private int incremento;
	private ArrayList<PuntoGraficado> puntos = new ArrayList<>();
	
	public Grafica(int incremento) {
		this.incremento = incremento;
	}
	
	public void agregarPunto(double x, double y) {
		if(!containsPunto(x,y)) {
			puntos.add(new PuntoGraficado(x, y));
		}
	}
	
	//Validar que exista un punto especifico en el arraylist
	public boolean containsPunto(double x, double y) {
		for(PuntoGraficado p : puntos) {
			if(p.x == x && p.y == y) {
				return true;
			}
		}
		return false;
	}
	
	//Getter de arraylist
	public ArrayList<PuntoGraficado> getPuntos() {
		return puntos;
	}
	
	//Setter de arraylist
	public void setPuntos(ArrayList<PuntoGraficado> puntos) {
		this.puntos = puntos;
	}
	
	public void tick() {
		
	}
	
	public void dibujar(Graphics g) {
		//Eje x
		g.drawLine(izquierda, centroAlto, derecha, centroAlto);
		//Eje y
		g.drawLine(centroAncho, abajo, centroAncho, arriba);
		
		
		int num = 1;
		//Dibujar rayitas del eje x y sus respectivos valores
		for(int x=0; x<(derecha-izquierda)/espacioRayitas+1; x++) {
			g.drawLine(izquierda + x*espacioRayitas, centroAlto+7, izquierda + x*espacioRayitas, centroAlto-7);
			//g.drawString(""+incremento*num, izquierda + x*espacioRayitas - 5, abajo+20);
			//num++;
		}
		
		for(int y=0; y<(abajo-arriba)/espacioRayitas+1; y++) {
			g.drawLine(centroAncho-7, arriba + y*espacioRayitas, centroAncho+7, arriba + y*espacioRayitas);
			//g.drawString(""+incremento*num, izquierda + x*espacioRayitas - 5, abajo+20);
			//num++;
		}
		/*
		num = 1;
		//Dibujar rayitas del eje y
		for(int y=1; y<280/espacioRayitas; y++) {
			g.drawLine(izquierda, abajo - y*espacioRayitas, izquierda + 10, abajo - y*espacioRayitas);
			g.drawString(""+incremento*num, izquierda - 20, abajo - y*espacioRayitas + 5);
			num++;
		}
		//Dibujamos el 0
		g.drawString("0", izquierda-15, abajo+15);
		*/
		
		PuntoGraficado p0, p1;
		for (int i = 1; i < puntos.size(); i++) {
			p0 = puntos.get(i-1);
			p1 = puntos.get(i);
			
			//double dx = izquierda + (p.x*(espacioRayitas/incremento));
			//double dy = abajo - (p.y*(espacioRayitas/incremento));
			g.drawLine((int)(centroAncho + (p0.x*(espacioRayitas/incremento))), (int)(centroAlto - (p0.y*(espacioRayitas/incremento))), (int)(centroAncho + (p1.x*(espacioRayitas/incremento))), (int)(centroAlto - (p1.y*(espacioRayitas/incremento)))); 
		}
		
	}

	public int getIncremento() {
		// TODO Auto-generated method stub
		return this.incremento;
	}
	
	public static void setEspacioRayitas(int espacioRayitas) {
		Grafica.espacioRayitas = espacioRayitas;
	}
}
