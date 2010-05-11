package Grupo2_Ex2;

import java.util.Iterator;
import java.util.LinkedList;

public class Exercicio2 {
	public static <E, F> Iterable<Pair<E, F>> zip(final Iterable<E> iter1, final Iterable<F> iter2){
		//cria o iterable de retorno
		return new Iterable <Pair<E, F>> (){
			//contendo o iterador deste
			public Iterator<Pair<E, F>> iterator() {
				return new Iterator<Pair<E, F>>(){
					//cria os iteradores para os iterables de entrada
					public Iterator<E> it1 = iter1.iterator();
					public Iterator<F> it2 = iter2.iterator();
					
					public boolean hasNext() {
						while(it1.hasNext() && it2.hasNext())
							return true;
						return false;
					}

					public Pair<E, F> next() {
						return new Pair<E, F>(it1.next(), it2.next());
					}
					
					public void remove(){
						throw new UnsupportedOperationException();
					}
				};
			};
		};
	}
	
	public static void main(String[] args) {
		LinkedList <String> s = new LinkedList<String>();
		LinkedList <Integer> i = new LinkedList<Integer>();
		s.add("ana");
		s.add("rIcardo");
		i.add(1);
		i.add(2);
		
		
		Iterable <Pair<String , Integer>> ar1 = zip(s , i);
		Iterator <Pair<String , Integer>> it = ar1.iterator();
		
		while(it.hasNext())
			System.out.println(it.next().toString());
	}
}
