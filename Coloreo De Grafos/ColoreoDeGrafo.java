import java.util.*;


public class ColoreoDeGrafo{
	public static void main(String[] args) {
		Integer colour = new Integer(3); //8 colores	
		//pasar un grafo a una linkedlist de nodos.
		Graph grafo = new Graph();
		//vertices o nodos (Vertex)
		grafo.insertarVertice("a");
		grafo.insertarVertice("b");
		grafo.insertarVertice("c");
		grafo.insertarVertice("d");
		grafo.insertarVertice("e");
		grafo.insertarVertice("f");
		//relaciones o arcos. (Edge)
		grafo.insertarArco("a","b",1);
		grafo.insertarArco("a","c",1);
		grafo.insertarArco("b","a",1);
		grafo.insertarArco("b","d",1);
		grafo.insertarArco("c","a",1);
		grafo.insertarArco("c","d",1);
		grafo.insertarArco("d","c",1);
		grafo.insertarArco("d","b",1);
		grafo.insertarArco("d","e",1);
		grafo.insertarArco("d","f",1);
		grafo.insertarArco("e","d",1);
		grafo.insertarArco("e","f",1);
		grafo.insertarArco("f","d",1);
		grafo.insertarArco("f","e",1);
		//Grafo completo 
		//OBTENER TODO EL GRAFO EN UNA LINKEDLIST
		grafo.dfsR("a");
		Set<Nodo> visit = new HashSet<Nodo>();
		visit=grafo.getVisitados();


		Iterator<Nodo> it = visit.iterator();
		while (it.hasNext()){
			System.out.println(  it.next().getValor() );
		}

		//LinkedList<Nodo> paraColorear = grafo.toLinkedList("a");
		//String s= "";
		//for (int i=0 ; i < paraColorear.size();i++){
		//	s+=" "+paraColorear.poll();
		//}
		//System.out.println(s);

	}
	//Metodo coloreo de grafos : (Nodo,color) 
	//return la lista de pares (Nodo,color)
	public static LinkedList<Pair<Nodo,Integer>> coloringGraph (LinkedList<Nodo> colorear, LinkedList<Integer> colores){
		Pair<Nodo,Integer> result = new Pair<Nodo,Integer> ();//creo par de resultado
		LinkedList<Pair<Nodo,Integer>> listPintados = new LinkedList<Pair<Nodo,Integer>>();//lista de pares 
		while (!colorear.isEmpty() && !colores.isEmpty() ){
			Nodo current =  colorear.poll();//saco y guardo el primer nodo de la lista
			Integer color =  colores.poll();// saco y guardo el primer color.
			result.setPair(current,color); //agrego el par nodo,color
			listPintados.add(result);//agrego el par a la lista de pares.
			Iterator it = colorear.iterator();
			while( it.hasNext() ){
				Nodo noPintado = (Nodo) it.next();
				//si la lista no es vacia y si algun adyacente a el no esta pintado de color c current.
				if ( ! adjNoPintadoDeC(listPintados,noPintado,color) ){
					//entonces pintar noPintado con color c current.
					result.setPair(noPintado,color);
					listPintados.add(result);
					//sacar de la lista a colorear
					colorear.remove(noPintado);	
				}	
			}
		}
		if (colorear.isEmpty() && colores.isEmpty() ){
			return listPintados;	
		//todo OK, se pinto todo y se utilizaron todos los colores. Ok!
		}
		if (colorear.isEmpty()){ //la lista de nodos para colorear se termino, Ok!
			return listPintados;
		}
		if (colores.isEmpty()){//Si los colores se terminaron, faltan nodos por pintar, error!
			throw new IllegalArgumentException("Los colores son insuficientes para pintar todos los vertices");
		}
		return listPintados;
	}
	//Dado un nodo, se fija si sus adyacentes,alguno tiene ese color 
	//entonces no se puede pintar al nodo del color corriente
	private static boolean adjNoPintadoDeC (LinkedList<Pair<Nodo,Integer>> listPintados, Nodo current, Integer color){
		boolean ctrl = false;
		LinkedList<Arco> listAdj = current.adjacents();
										//Nodo adjacent
		//agarrar cada elemento adyacente y ver si pertenece a la lista de pintados y tiene el color parametro
		while (!listAdj.isEmpty()  ){
			 ctrl = ctrl || pertenece (listPintados, (listAdj.poll()).destino , color);
		}
		return ctrl;

	}
	//Dado un adyacente y un color, verificar si pertenece a la lista de pintados.

	private static boolean pertenece (LinkedList<Pair<Nodo,Integer>> listPintados, Nodo adjacent,Integer color){
		boolean pert = false ;
		Pair<Nodo,Integer> current = new Pair<Nodo,Integer>();
		int i=0;
		while ( i < listPintados.size() ) {
								//Nodo                     , color
			current.setPair( listPintados.get(i).getLeft(), listPintados.get(i).getRight()  );
			if ( ( (current).getLeft() ).equals(adjacent)  ){ //si hay algun elemento de la lista de pintados igual al adyacente
				if ( (current.getRight()).equals(color)  ){	//si ademas, el adyacente tiene ese color
					pert= pert || true;
				}
			}
			i++;
		}
		return pert;
	}
	
}