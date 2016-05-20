//---------------------------------------------------------------------------------------------
/*
				Copyright : Mauricio Delle Vedove.
*/
//---------------------------------------------------------------------------------------------
/*
Clase grafos

• Crear un grafo vacio.
• Decir si un grafo es vacıo.
• Dar el numero de vertices de un grafo.
• Dar el numero de arcos de un grafo.
• Determinar si hay un arco entre dos nodos dados.
• Insertar un nodo en un grafo.
• Insertar un arco entre dos nodos.
• Borrar un vertice del grafo.
• Borrar un arco del grafo.
• Decir si un nodo pertenece al grafo.

*/

//---------------------------------------------------------------------------------------------
import java.util.PriorityQueue;
import java.util.Collection;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;
import java.util.Stack;
import java.util.LinkedList;

public class Graph<E extends Comparable<E>>{	
//---------------------------------------------------------------------------------------------		
//atributos------------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------	
	private Map<E,Nodo> grafo;
	private Stack<Nodo> miPila;
	private Set<Nodo> visitados;
	private LinkedList<Nodo> nodos;
//---------------------------------------------------------------------------------------------			
//constructor----------------------------------------------------------------------------------
//---------------------------------------------------------------------------------------------	
	public Graph(){
	
		grafo = new HashMap<E,Nodo>();		
		miPila = new Stack<Nodo>();	
		visitados = new HashSet<Nodo>();
		nodos= new LinkedList<Nodo>();
	}
//---------------------------------------------------------------------------------------------	
//Metodos--------------------------------------------------------------------------------------	
//---------------------------------------------------------------------------------------------	
	// Vacia el grafo 
	
	public void vaciarGrafo(){
		
		grafo.clear();		
	}
//---------------------------------------------------------------------------------------------	
	// Devuelve si un grafo es o no vacio
	
	public boolean esVacio(){
				
		return grafo.isEmpty();
	}
//---------------------------------------------------------------------------------------------	
	// Devuelve el numero de vertices de un grafo
	
	public int numVertices(){
		
		return grafo.size();
	}
//---------------------------------------------------------------------------------------------	
	// Devuelve el numero de arcos de un grafo	
	
	public int numArcos(){
		
		int cant = 0;					
		Iterator<Nodo> st = grafo.values().iterator();		
		while (st.hasNext()){
			cant = cant + st.next().contarArcos();	
		}	
		return cant;
	} 
//---------------------------------------------------------------------------------------------
	// Devuelve si hay o no un arco entre 2 nodos dados 
	
	public boolean hayArco(E k1, E k2){
		
		boolean t 	= ((grafo.containsKey(k1)) && (grafo.containsKey(k2))); //comprobamos que existan los nodos
		if (t) { //si existen los nodos buscamos el arco
			Nodo a = grafo.get(k1);
			Nodo b = grafo.get(k2);
			t = a.hayArco(b);
		}
		return t;
	} 
//---------------------------------------------------------------------------------------------	
	// Inserta un nodo en el grafo 
	
	public void insertarVertice(E k){		//k es la clave, y valor es el valor dentro del nodo
		
		Nodo v = grafo.get(k);
		if( v == null )
		{
			v = new Nodo(k);
			grafo.put(k,v);
		}		
}	
//---------------------------------------------------------------------------------------------	
	// inserta un arco entre 2 nodos 
	
	public void insertarArco(E k1, E k2, int cost){

		boolean t 	= ((grafo.containsKey(k1)) && (grafo.containsKey(k2))); //comprobamos que existan los nodos
		if (!t){
			System.out.println("Nodo inexistente");
		}else{	
			Nodo a = grafo.get(k1);
			Nodo b = grafo.get(k2);
			a.insertarArco(b,cost);			
		}			
	} 
//---------------------------------------------------------------------------------------------	
	// Borrar un nodo del grafo
	
	public void borrarVertice(E k){
		
		boolean t = grafo.containsKey(k);	//el grafo contiene a k ?
		if (!t){
			System.out.println("El nodo no existe");
		}
		else {
			Nodo<E> nodo = grafo.get(k);
			grafo.remove(k);				
			Iterator<Nodo> st = grafo.values().iterator();			
			while (st.hasNext()){					
				st.next().borrarArco(nodo);		
			}
		}
	}	
//---------------------------------------------------------------------------------------------	
	// Borrar un arco del grafo
	
	public void borrarArco(E k1, E k2){
		
		boolean t 	= ((grafo.containsKey(k1)) && (grafo.containsKey(k2))); //comprobamos que existan los nodos
		if (!t){
			System.out.println("Nodo inexistente");
		}
		else{	
			Nodo a = grafo.get(k1);
			Nodo b = grafo.get(k2);	
			a.borrarArco(b);			
		}		
	} 
//---------------------------------------------------------------------------------------------	
	//Dice si un nodo pertenece a un grafo
	
	public boolean hayNodo(E k){
					
		return grafo.containsKey(k);
	}		
//---------------------------------------------------------------------------------------------	
	// Devuelve el Nodo con clave k 
		
	public Nodo getNode(Comparable k){
		
		return grafo.get(k);
	}
//---------------------------------------------------------------------------------------------
	//Devuelve el set de visitados.
	public Set<Nodo> getVisitados(){
		return visitados;
	}	

//---------------------------------------------------------------------------------------------	
	// Dfs recursivo 

	public void dfsR(E v){

		visitados.clear();
		Nodo nodo = grafo.get(v);
		dfs_recursivo(nodo);
	}
	public void dfs_recursivo(Nodo<E> v){
		System.out.println(v.getValor());	//proccess
		visitados.add(v);					//mark
		for (Arco arco: v.adj){
			if (!(visitados.contains(arco.destino))){
				dfs_recursivo(arco.destino);	//Lamada recursiva con el nodo adyacente.
			}
		}
	}
//---------------------------------------------------------------------------------------------
	//Pasar todos los elementos del grafo a una linkedList de Nodos.	

	public LinkedList<Nodo<E>> toLinkedList (E v){
		
		visitados.clear();
		Nodo nodo = grafo.get(v);
		LinkedList<Nodo<E>> list = new LinkedList<Nodo<E>>();
		return toLinkedListRecursive(list,nodo);
	}
	private LinkedList<Nodo<E>> toLinkedListRecursive(LinkedList<Nodo<E>> listResult, Nodo<E> nodo){
		//procesa
		listResult.add(nodo);
		visitados.add(nodo); //marca
		for (Arco arco: nodo.adj){
			if (!(visitados.contains(arco.destino))){
				toLinkedListRecursive(listResult,nodo);
			}
		}
		return listResult;
	}


//------------------------------------------------------------------------------------------------
	// Dfs iterativo 

	public void dfsI(E v){

		visitados.clear();
		Nodo nodo = grafo.get(v);
		dfs_iterativo(nodo);
	}

	public void dfs_iterativo(Nodo<E> v){
		miPila.push(v);
		while(!miPila.isEmpty()){
			Nodo<E> nAux = miPila.pop();
			if(!(visitados.contains(nAux))){
				visitados.add(nAux);				// Marcado
				System.out.println(nAux.getValor());
				Stack<Nodo> pilaAux = new Stack<Nodo>(); //Pila auxiliar para los nodos adyacentes
				for(Arco arco : nAux.adj){
					if(!(visitados.contains(arco.destino))){
						pilaAux.push(arco.destino);
					}
				}
				while(!pilaAux.isEmpty()){
					miPila.push(pilaAux.pop());
				}
			}
		}
	}
//---------------------------------------------------------------------------------------------	
/* algoritmo de prims */
//O (|E|+|V|)

	public void prims (E k, Map path){

		Nodo<E> v = grafo.get(k);
		for (Nodo nodo: grafo.values()){
			nodo.setClave(Integer.MAX_VALUE);			// Seteamos loss campos claves con "infinito"
		}
		v.setClave(0);									// Al nodo inicial, en el campo clave, se le da 0
		Heap<E> heap = new Heap<>();
		for(Nodo nodo: grafo.values()){
			heap.insertar(nodo);						//Cargamos el Heap con los nodos modificados
		}
		path.put(v.getValor(),null);
		while (!(heap.esVacio())){	
			Nodo<E> actual = heap.minimo();			//Obtengo el primer nodo a tratar
			heap.eliminar();
			for(Arco arco: actual.adj){
				Nodo<E> w = arco.destino;			//Recorro lo nodos adyacentes.
				if (heap.pertenece(w)){	
					if(w.getClave()>arco.costo){
						w.setClave(arco.costo);
						path.put(w.getValor(),actual);
						heap.actualizar(w);					//Actualizo el nodo en el heap.
					}
				}
			}
		}
	}
	//Algoritmo de Kruskal
/*
public Set<Edge> kruskal(Graph g){
	Set<Edge> es = new Set<Edge>(); // crea un conjunto de arcos vacios
	PriorityQueue h = new PriorityQueue(g.getEdges()); // creamos una cola de prioridad de
	arcos ordenados por sus costos
	DisjointSet s = new DisjointSet(g.getVertices()); // creamos un disjoint set con todos
	los vertices
	while (!h.isEmpty()){
		Edge e = h.delMin(); // se saca el arco con menor costo
		Vertex u = h.getSource(); // se obtiene su origen
		Vertex v = h.getTarget(); // se obtiene su destino
		if (s.find(u) != s.find(v)){ // si estan en clases de equiv. distintas
			es.add(e); // se agrega el arco
			s.union(u,v); // se hace la union de las clases
		}
	}
	return es;
}

*/
//public Set<Nodo> kruskal (Graph g){
//	Set<Nodo> set = new HashSet<Nodo>();
//	PriorityQueue<Nodo> queue = new PriorityQueue<Nodo>(g.values());
//	DisjointSets s = new DisjointSets( g.numVertices() );
//	while (!queue.isEmpty()){
//		Arco<E> = queue.poll();

//	}
//	return set;
//}




}
