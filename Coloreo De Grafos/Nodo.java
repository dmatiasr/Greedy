//---------------------------------------------------------------------------------------------
/*
				Integrantes: Eric Irusta, Mauricio Delle Vedove.
*/
//---------------------------------------------------------------------------------------------
/*
				Clase Nodo
*/	
//---------------------------------------------------------------------------------------------
import java.util.LinkedList;
import java.util.Iterator;


public class Nodo<E extends Comparable<E>>{
//---------------------------------------------------------------------------------------------		
//atributos------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------	
	E valor;
	int clave; //prims
	LinkedList<Arco> adj;
//---------------------------------------------------------------------------------------------			
//constructor----------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------	
	public Nodo(){
		
		valor = null;
		clave = 0;
		adj   = new LinkedList<>();
	}
//---------------------------------------------------------------------------------------------	
//Metodos--------------------------------------------------------------------------------------	
//---------------------------------------------------------------------------------------------	
	public Nodo(E v){
		
		valor = v;
		clave = 0;
		adj = new LinkedList<>();
	}
//---------------------------------------------------------------------------

	/*Retorna la lista de nodos adjacentes a this*/
	public LinkedList<Arco> adjacents (){
		return this.adj;
	}


//---------------------------------------------------------------------------------------------	
	public int compareTo(E x){
			
			return valor.compareTo(x);
		}
//---------------------------------------------------------------------------------------------	
	/* Cuenta los arcos adyacentes de un nodo */

	public int contarArcos(){
		
		
		return adj.size();
	}

	public void setValor(E x){
		valor = x;
	}
//---------------------------------------------------------------------------------------------	
	/* Busca si hay un arco hacia un nodo dado */

	public boolean hayArco(Nodo b){
								
		Arco<E> new_arco = new Arco<>(b,0);
		
		return adj.contains(new_arco);	
	}
//---------------------------------------------------------------------------------------------
	/* Inserta un arco dado 2 nodos */

	public void insertarArco(Nodo b, int c){
		
		if (hayArco(b)){
			System.out.println("el arco ya existe");
		}else{		
			Arco<E> new_arco = new Arco(b,c);
			adj.add(new_arco);
		}
	}
//---------------------------------------------------------------------------------------------
	/* Borra un arco dado 2 nodos */

	public void borrarArco(Nodo b){
		
		if (!(hayArco(b)))	System.out.println("el arco no existe");
		else{
		
			Arco<E> arco = new Arco(b,0);
			adj.remove(arco);

		}
	}
//----------------------------------------------------------------------------------------------
	public void setClave(int c){        

		clave = c;
	}
//----------------------------------------------------------------------------------------------
	public int getClave(){

		return clave;
	}

//----------------------------------------------------------------------------------------------
	public boolean equals(Object objeto){

		if (this == objeto) return true;
		if (objeto == null) return false;
		if (objeto.getClass() != this.getClass() ) return false;

		Nodo<E> nodo = (Nodo<E>) objeto;

		if (this.valor != nodo.valor) return false;
		if (!(this.adj.equals(nodo.adj))) return false;
		return true;
	}

//----------------------------------------------------------------------------------------------
	public E getValor(){
		return valor;
	}
}
