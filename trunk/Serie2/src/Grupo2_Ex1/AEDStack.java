package Grupo2_Ex1;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Queue;

public class AEDStack<E> implements  Queue<E>{
	
	private static class Node<E>{
		public Node<E> next;
		public E data;
	}
	
	private Node<E> head, tail;
	int count;
	
	public AEDStack(){
		head = null;
		tail = null;
		count = 0;
	}
	
	public boolean add(E elem) {
		Node<E> add = new Node<E>();
		add.data = elem;
		add.next = head;
		head = add;
		if(head == null){
			tail = add;
		}
		++count;
		return true;
	}

	public E element() {
		if(head!=null)
			return null;
		return head.data;
	}

	public boolean offer(E elem) {
		add(elem);
		return false;
	}

	public E peek() {
		return element();
	}

	public E poll() {
		if(head == null)
			return null;
		E ret = head.data;
		head = head.next;
		
		return ret;
	}

	public E remove() {
		return poll();
	}

	public boolean addAll(Collection<? extends E> col) {
		for(E elem: col)
			add(elem);
		return true;
	}

	public void clear() {
		head = tail = null;
	}

	//nao deve ser para implementar
	public boolean contains(Object obj) {
		return false;
	}

	//nao deve ser para implementar
	public boolean containsAll(Collection<?> arg0) {
		return false;
	}

	public boolean isEmpty() {
		if(head == null)
			return true;
		return false;
	}

	public Iterator<E> iterator() {
		if(head == null)
			return null;
		
		return new Iterator <E> (){
			
			Node <E> aux = head;
			
			public boolean hasNext() {
//				if(aux.next == null)
//				return false;
//			
//			return true;
				return aux!=null;
			}

			public E next() {
				if(aux == null)
					throw new NoSuchElementException();
				E ret = aux.data;
				aux = aux.next;
				return ret;
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}
	
	//nao deve ser implementado
	public boolean remove(Object arg0) {
		return false;
	}

	//nao deve ser implementado
	public boolean removeAll(Collection<?> arg0) {
		return false;
	}

	//nao deve ser implementado
	public boolean retainAll(Collection<?> arg0) {
		return false;
	}

	public int size() {
		return count;
	}

	public Object[] toArray() {
		if(head == null)
		return null;
		int i=0;
		
		Object [] ret = new Object [count];
		Iterator <E> it = this.iterator();
		
		while(it.hasNext())
			ret[i++]=it.next();
		return ret;
	}

	//nao é preciso implementar
	public <T> T[] toArray(T[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
}
