package Grupo2;

public class Exercicio2 {
	private LinkedList<Pair<E, F>> ; 
	
	public static <E, F> Iterable<Pair<E, F>> zip(Iterable<E> iter1, Iterable<F> iter2){
		Iterable<Pair<E,F>> iterable = new Iterable<Pair<E,F>>() {
			public Iterator iterator<Pair<E,F>>() {
				return new Iterator<Pair<E,F>>() {
					public Pair<E,F> current;
					
				}
			}
		}
		return iterable;

	}
	public static void main(String[] args) {
		
	}
}
