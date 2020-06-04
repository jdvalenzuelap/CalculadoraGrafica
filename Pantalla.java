//Proyecto final EDD 
//Calculadora graficadora de funciones
//Juan Diego Valenzuela - A00825349

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Pantalla extends JPanel implements Runnable {
	public final static int width = 800,
							height = 600;
	private boolean corriendo = false;
	private Thread thread;
	
	private Canvas areaGrafica;
	private JButton botonGraficar;
	private JTextField editorEcuacion;
	
	private Grafica grafica;
	
	public Pantalla() {
		super();
		
		this.grafica = new Grafica(2);
		System.out.println(Grafica.derecha-Grafica.izquierda);
		this.setMinimumSize(new Dimension(width, height));
		this.setMaximumSize(new Dimension(width, height));
		this.setPreferredSize(new Dimension(width, height));
		this.setLayout(new BorderLayout());
		
		//Canvas para hacer la grafica
		this.areaGrafica = new Canvas();
		this.areaGrafica.setSize(width, height/2 + 150);
		this.add(this.areaGrafica, BorderLayout.NORTH);
		
		//Boton para graficar
		this.botonGraficar = new JButton("Graficar");
		this.botonGraficar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				//Aqui ponemos el codigo para hacer calculos
				Estructura estructura = new Estructura();
				estructura.calculate(editorEcuacion.getText(), Grafica.centroAncho/Grafica.espacioRayitas - 8 , grafica);
			}
		});
		
		this.add(this.botonGraficar, BorderLayout.CENTER);

		//Textfield para ingresar la ecuacion
		this.editorEcuacion = new JTextField();
		this.editorEcuacion.setSize(new Dimension(width, 10));
		this.add(this.editorEcuacion, BorderLayout.SOUTH);
		
		//Hacemos el Frame donde va a estar todo
		//Lo hacemos desde este panel para no tener que crear otra clase que herede un Frame
		JFrame frame = new JFrame("Calculadora grafica");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setVisible(true);
		
		//Iniciamos el thread
		this.empezar();
	}
	
	public void empezar() {
		if (corriendo) {
			return;
		}else if(this.thread == null){
			this.thread = new Thread(this);
			this.thread.start();
		}
		this.corriendo = true;
	}
	
	public void acabar() {
		if (!corriendo) {
			return;
		}
		this.corriendo = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(corriendo) {
			this.dibujar();
		}
		System.exit(0);
	}
	
	private void dibujar() {
		BufferStrategy bs = this.areaGrafica.getBufferStrategy();
		if(bs == null) {
			//Sino hay nada dibujado todavia, definimos la dimension del dibujo
			this.areaGrafica.createBufferStrategy(2);
			return;
		}
		
		//Mandamos a llamar al metodo padre paint para que no haya problemas
		Graphics g = bs.getDrawGraphics();
		this.areaGrafica.paint(g);
		
		grafica.dibujar(g);

		//Lo hacemos para que libere memoria, vamos a dibujar mucho entonces es bueno mandarlo a llamar automaticamente
		g.dispose();
		bs.show();
		
	}
	
	
	public static void main(String[] args) {
		Pantalla pantalla = new Pantalla();
	}
	
}
