//Proyecto final EDD 
//Calculadora graficadora de funciones
//Juan Diego Valenzuela - A00825349

import java.util.NoSuchElementException;

public class ListaEnlazada<E>{
	private NodoLE<E> inicio,
					  fin;
	private int size;
	
	//Constructors
	public ListaEnlazada() {
		this.inicio=this.fin=null;
		this.size = 0;
	}
	
	public ListaEnlazada(E[] valores) {
		this();
		for(int i=0; i<valores.length; i++) {
			this.insertarFin(valores[i]);
		}
	}
	
	//Metodos para borrar
	/*
	public E borrarInicio() {
		try {
			NodoLE<E> temp = this.inicio;
			this.inicio = temp.next;
			temp.next = null;
			return temp.valor;
		}catch(NoSuchElementException e) {
			throw new NoSuchElementException("La lista esta vacia");
		}
	}
	*/
	
	public E borrarInicio() {
		//Borra el pimer elemento d ela lista y si esta vacia debe arrojar un NoSuchElement Exception y regresa el valor que se elimino. 
		//Nullpointer exception es para listas que no hemos inicializado
		try {
			E valorBorrado=this.inicio();
			if(this.size==1) {
				this.inicio=null;
				this.fin=null;
			}else {
				this.inicio.setNext(this.inicio.getNext());
				this.inicio=this.inicio.getNext();
			}
			this.size--;
			return valorBorrado;
		}catch (NullPointerException e ) {throw new NoSuchElementException("Debido a que no hay elementos, es imposible borrar el inicio");}
	}
	public E borrarFin() {
		try {
			E valor;
			if (this.size==1) {
				valor = inicio();
				this.inicio=this.fin=null;
				this.size=0;
				return valor;
			}
			NodoLE<E> current = this.inicio;
			for (int i = 0; i<size-2;i++) {
				current = current.next;
			}
			valor= current.next.valor;
			current.next = null;
			this.size--;
			this.fin = current;
			return valor;
		} catch(NullPointerException e) {
			throw new NoSuchElementException("No se puede borra el inicio de una lista vacia");
		}
	}
	
	//Getters de inicio y fin
	public E inicio() {
		try {
			return this.inicio.valor;
		}catch(NullPointerException e) {
			throw new NoSuchElementException("No se puede regresar un valor de una lista vacia");
		}
	}
	
	public E fin() {
		try {
			return this.fin.valor;
		}catch(NullPointerException e) {
			throw new NoSuchElementException("No se puede regresar un valor de una lista vacia");
		}
	}
	
	public E getAt(int pos) {
		NodoLE<E> actual = this.inicio;
		int current = 0;
		for(int i=0; i<this.size; i++) {
			if(current == pos) {
				return actual.valor;
			}
			current++;
			actual = actual.next;
		}
		System.out.println("Index out of range!");
		return null;
	}
	
	//Metodos utiles
	public int size() {
		return this.size;
	}
	
	public boolean isEmpty() {
		return this.size==0;
	}
	
	//Metodos para insertar
	public void insertarInicio(E valor) {
		this.inicio = new NodoLE<>(valor, this.inicio);
		if(this.fin == null) {
			this.fin = this.inicio;
		}
		this.size++;
	}
	
	public void insertarFin(E valor) {
		if(this.isEmpty()) {
			this.insertarInicio(valor);
		}else {
			NodoLE<E> temp = new NodoLE<>(valor);
			this.fin.next = temp;
			this.fin = temp;
			this.size++;
		}
	}
	
	public void insertarEn(E valor, int pos) {
		if(this.size > pos && pos >= 0) {
			NodoLE<E> temp = new NodoLE<>(valor),
					  actual = this.inicio;
			
			//Iteramos para llegar al nodo indicado
			for(int i=0; i < pos-1; i++) {
				actual = actual.next; 
			}
			
			//Asignamos el nuevo orden de la lista
			temp.next = actual.next;
			actual.next = temp;
			this.size++;
		}else {
			throw new IndexOutOfBoundsException("No es correcto el index dado");
		}
		
	}
	
	//Metodos para modificar
	
	public void setAt(E valor, int pos) {
		//Remplaza el valor que esta en la posicion por el nuevo valor
	
		if(this.size > pos && pos >= 0) {
			NodoLE<E> actual = this.inicio;
			
			//Iteramos para llegar al nodo indicado
			for(int i=0; i < pos; i++) {
				actual = actual.next; 
			}
			
			actual.valor = valor;
			
		}else {
			throw new IndexOutOfBoundsException("No es correcto el index dado");
		}
	}
	
	public String toString() {
		String res = "";
		NodoLE<E> actual = this.inicio;
		
		for(int i=0; i<this.size; i++) {
			res+= actual.toString()+", ";
			actual = actual.next;
		}
		return res;
	}
	
	
	public static void main(String[] args) {
		ListaEnlazada<Integer> lista = new ListaEnlazada<>(new Integer[]{2,3,4,5,6,7});
		System.out.println(lista.toString());
		lista.borrarFin();
		System.out.println(lista);
		lista.setAt(16, 2);
		System.out.println(lista);
		lista.insertarEn(22, 2);
		System.out.println(lista);
	}

}

class NodoLE<E>{
	E valor;
	NodoLE<E> next;
	
	//Constructors
	public NodoLE(E valor) {
		this(valor, null);
	}
	
	public NodoLE(E valor, NodoLE<E> next) {
		super();
		this.valor = valor;
		this.next = next;
	}
	
	
	//Getters y setters
	public E getValor() {
		return valor;
	}
	public void setValor(E valor) {
		this.valor = valor;
	}
	public NodoLE<E> getNext() {
		return next;
	}
	public void setNext(NodoLE<E> next) {
		this.next = next;
	}
	
	//toString
	public String toString() {
		return this.valor.toString();
	}	
}
