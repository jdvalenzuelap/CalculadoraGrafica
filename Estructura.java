//Proyecto final EDD 
//Calculadora graficadora de funciones
//Juan Diego Valenzuela - A00825349

import java.util.ArrayList;

public class Estructura {
	
	private ArrayList<PuntoGraficado> datos = new ArrayList<>();
	
	public void calculate(String ecuacion, int limite, Grafica grafica) {
		//Nuestro limite es para que se grafique hasta cierto punto y no vaya hasta infinito
		for(int x = -limite; x <= limite; x++) {
			double y = Double.NaN;
			
			try {
				//y = InfixEvaluation.evaluar(ecuacion, x);
				y =  Ecuacion.calcular(ecuacion, x).doubleValue();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(y == Double.NaN) {
				this.clear();
			}else {
				datos.add(new PuntoGraficado(x, y));
			}
		}
		this.aplicarGrafica(grafica);
	}
	
	public void clear() {
		this.datos = new ArrayList<PuntoGraficado>();
	}
	
	private void aplicarGrafica(Grafica grafica) {
		grafica.setPuntos(datos);
	}
}
