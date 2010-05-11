package Grupo2_Ex3;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Exercicio3 {
	public static <E> Iterable<E> skipWhile(final Iterable<E> iter, final Predicate<E> pred) {
		return new Iterable <E> (){
			public Iterator<E> iterator() {
				return new Iterator<E>(){
					Iterator<E> it = iter.iterator();
					boolean flag = false;
					E n;
					
					public boolean hasNext() {
						while(it.hasNext()){
							if(flag == true)
								return true;
							n = it.next();
							if(!pred.eval(n)){
								flag = true;
							}
						}
						return false;
					}

					public E next() {
						if(hasNext()!=false){
							if(n!=null){
								E aux = n;
								n = null;
								return aux;
							}
							return it.next();
						}
						else
							throw new NoSuchElementException();
					}
					
					public void remove() {
						throw new UnsupportedOperationException();
					}	
				};
			};	
		};
	}

	public static void main(String[] args) {
		LinkedList <String> s = new LinkedList<String>();
		s.add("ana");
		s.add("rIcardo");
		s.add("rIcardo");
		
		
		Predicate<String> pred = new Predicate<String>(){
			public boolean eval(String elem){
				return elem.equals("ana");
			}
			
		};
		Iterable <String> ar1 = skipWhile(s , pred);
		
		Iterator<String> it = ar1.iterator();
		
		while(it.hasNext())
			System.out.println(it.next().toString());
	}
}
