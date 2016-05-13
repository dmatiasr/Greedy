import java.util.*;
public class Mochila{
	
	public static void main(String[] args) {
		int capacidad = 60;
	
	}
	//Metodo que llena la mochila de elementos segun su beneficio.
	public static Set mochila (Pair[] items, int capacidad){
		Set solution = new HashSet();
		sortSecond(items); //Ordeno el arreglo por la segunda componente.
		int elems =0;
		for (int i=0; elems<capacidad;i++){
			solution.add(items[i]);
			elems+= (items[i].getSecond()).intValue();
		}
		return solution;
	}


	//Ordena el arreglo por la 2da componente .
	public static void sortSecond(Pair[] items){
		Arrays.sort(items, new Comparator<Pair>() {
			public int compare(Pair p1, Pair p2){	
				return p1.getSecond().compareTo(p2.getSecond());	
			}	
		});
	}
}