//---------------------------------------------------------------------------------------------
/*
				Integrantes: Eric Irusta, Mauricio Delle Vedove.
*/
//---------------------------------------------------------------------------------------------
/* 
				implementacion del heap con arreglos 
*/
//---------------------------------------------------------------------------------------------
import java.util.ArrayList;

public class Heap<E extends Comparable<E>>{

//---------------------------------------------------------------------------------------------		
//atributos------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------	

	private ArrayList<Nodo> heapA;
	private int numE;
	
//---------------------------------------------------------------------------------------------			
//constructor----------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------	

	public Heap(){
		heapA = new ArrayList<Nodo>();
		numE = 0;
	}
	
//---------------------------------------------------------------------------------------------	
//Metodos--------------------------------------------------------------------------------------	
//---------------------------------------------------------------------------------------------	
	//retorna el hijo izquierdo

	public int getLeft(int i){  
		return (2*i+1);
	}
//---------------------------------------------------------------------------------------------	
	//retorna el hijo derecho

	public int getRight(int i){ 
		return (2*(i+1));
	}	
//---------------------------------------------------------------------------------------------	
	//retorna el padre

	public int getParent(int i){  
		return ((i-1)/2);
	}
//---------------------------------------------------------------------------------------------	
	//retorna true en caso de que el heap sea vacio

	public boolean esVacio(){    

		return numE == 0;
	}
//---------------------------------------------------------------------------------------------	
	//retorna el elemento minimo del heap

	public Nodo minimo(){   

		return heapA.get(0);
	}
//---------------------------------------------------------------------------------------------	
	//muestra la cantidad de elementos del heap

	public void getCantElements(){    
		System.out.println(numE-1);
	}
//---------------------------------------------------------------------------------------------	
	//mantiene el heap acomodado

	public void heapify(int i){      
		int hi = getLeft(i);
		int hd = getRight(i);

		if (heapA.get(hi).getClave()>heapA.get(hd).getClave()){
			Nodo<E> aux = heapA.get(hd);
			heapA.set(hd,heapA.get(i));
			heapA.set(i,aux);
		}else{
			Nodo<E> aux = heapA.get(hi);
			heapA.set(hi,heapA.get(i));
			heapA.set(i,aux);
		}
	}
//---------------------------------------------------------------------------------------------	
	//inserta un elemento al heap

	public void insertar(Nodo elemento){    
		
		if (numE==0){
			heapA.add(0,elemento);
			numE++;
		}else{	
			heapA.add(numE,elemento);
			int curr = numE;
			int p = getParent(numE);
			while ((p>=0) && (heapA.get(p).getClave()>elemento.getClave())){
				Nodo<E> aux=heapA.get(curr);
				heapA.set(curr,heapA.get(p));
				heapA.set(p,aux);
				curr=p;
				p=getParent(p);
			}
			numE++;
		}
	}
//---------------------------------------------------------------------------------------------	
	//muestra la clave y el valor de cada elemento del heap

	public void mostrarArreglo (){ 
		int i = 0;
		while (i<numE){
			System.out.println(heapA.get(i).getClave()+" ---> "+heapA.get(i).getValor());
			i++;
		}
	}


//---------------------------------------------------------------------------------------------	
	//retorna true si el nodo pertenece en el heap

    public boolean pertenece(Nodo x){

		if (esVacio()) return false;
		else{
			boolean encontrado = false;
			int i = 0;
			while ((i<numE) && (!encontrado)){
				if (x.equals(heapA.get(i))) encontrado = true;			
				i++;
			}	 
		return encontrado;
		}
	}
//---------------------------------------------------------------------------------------------	
	//actualiza el valor de un nodo manteniendo el orden en el heap

	public void actualizar(Nodo x){

		boolean actualizado = false;
		int i = 0;
		while ((i<numE) && (!actualizado)){
			if (x.equals(heapA.get(i))){
				actualizado = true;
				heapA.set(i,x);	
				// Nodo aux = heapA.get(0);
				// heapA.set(0,x);
				// heapA.set(i,aux);
			} 			
			i++;
		}
		if (actualizado){
			int p = getParent(numE-1);
			int hi = getLeft(p);
			int hd = getRight(p);
			while (p>=0){
				if (heapA.get(p).getClave()>heapA.get(hi).getClave()){
					heapify(p);
					p--;
					hi = getLeft(p);
					hd = getRight(p);
				}else if (heapA.get(p).getClave()>heapA.get(hd).getClave()){
					heapify(p);
					p--;
					hi = getLeft(p);
					hd = getRight(p);
				}else{
					p--;
					hi = getLeft(p);
					hd = getRight(p);
				}
			}
		}			
	}
//---------------------------------------------------------------------------------------------	
	 //elimina el primer elemento del heap
	public void eliminar(){
		if (numE==1){ 
			numE--;
		}else{
			Nodo<E> ult=heapA.get(numE-1);
			heapA.set(0,ult);
			numE--;
			
			acomodar();
		}
	}
//---------------------------------------------------------------------------------------------	
	//acomoda el heap cuando se borra un elemento

	public void acomodar(){
		
		int p = getParent(0);
		int hi = getLeft(p);
		int hd = getRight(p);
		while (hd<numE){
			if (heapA.get(p).getClave()>=heapA.get(hi).getClave()){
				heapify(p);
				p = hi;
				hi = getLeft(p);
				hd = getRight(p);
			}else if (heapA.get(p).getClave()>=heapA.get(hd).getClave()){
				heapify(p);
				p = hd;
				hi = getLeft(p);
				hd = getRight(p);
			}else{
				p++;
				hi = getLeft(p);
				hd = getRight(p);
			}
		}
	}
}