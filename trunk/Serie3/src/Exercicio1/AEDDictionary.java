package Exercicio1;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Map.Entry;



/*Realize a classe AEDDictionary<K,V> a qual representa um contentor associativo suportado numa tabela de dispers~ao.
 A func~ao de dispers~ao e a de igualdade s~ao par^ametros de construc~ao. */

public class AEDDictionary<K, V> {
	static class Node<K, V> implements Map.Entry<K, V> {
		K key;
		V value;
		Node<K, V> next;

		public K getKey() {
			return key;
		}

		public V getValue() {
			return value;
		}

		public V setValue(V value) {
			V ret = this.value;
			this.value = value;
			return ret;
		}
	}

	Dispersao<K, V>func ;
	Equality<K> equality;
	LinkedList<Node<K, V>> list;
	private Node<K, V>[] buckets;
	private int elems;
	private int loadFactor;

	@SuppressWarnings("unchecked")
	/* falta adicionar as funçoes de dispersao e igualdade */
	public AEDDictionary(int initSize, int loadFactor, Dispersao<K, V> func, Equality <K> equality) {
		list = new LinkedList<Node<K, V>>();
		buckets = new Node[initSize];
		elems = 0;
		this.loadFactor = loadFactor;
		this.func = func;
		this.equality = equality;
	}

	@SuppressWarnings("unchecked")
	// caso o loadFactor seja excedido entao ele realoca os elementos num array
	// com o dobro do tamanho!
	private void realocate() {
		Node<K, V>[] aux = buckets;
		buckets = new Node[buckets.length * 2];
		elems = 0;

		for (int i = 0; i < aux.length; ++i)
			put(aux[i].key, aux[i].value);

	}

	public V put(K k, V v) {
		if ((elems % buckets.length) >= loadFactor) {
			realocate();
		}

		int idx = func.calc(k) % buckets.length;

		Node<K, V> curr = new Node<K, V>();
		Node<K, V> ret = new Node<K, V>();

		ret.value = null;

		curr.value = v;
		curr.key = k;

		list.add(curr); /* adiciona a lista o valor introduzido sequencialmente */

		if (buckets[idx] == null) {
			buckets[idx] = curr;
			curr.next = null;
		} else {
			ret.value = buckets[idx].value;
			curr.next = buckets[idx];
			buckets[idx] = curr;
		}
		++elems;

		return ret.value;
	}

	public V remove(K k) {
		int idx = func.calc(k) % buckets.length;
		if (buckets[idx] == null)
			return null;

		// remoçao a cabeça
		if (equality.compare(buckets[idx].getKey(),k)==0) {
			list.remove(buckets[idx]); // remove da lista de entradas
			V ret = buckets[idx].value;
			buckets[idx] = buckets[idx].next;
			--elems;
			return ret;
		} else { // caso geral
			Node<K, V> curr = buckets[idx];
			
			while (curr.next != null && equality.compare(buckets[idx].getKey(),k)!=0)
				curr = curr.next;

			if (curr.next != null) {
				list.remove(curr); // remove da lista de entradas
				--elems; // decrementa o numero de elementos
				V ret = curr.value;
				curr.next = curr.next.next;
				return ret;
			}
		}
		return null;
	}

	/*
	 * void removeIf(Predicate<Map.Entry<K, V>> criterion) o qual remove todas
	 * as associac~oes que obedecam ao predicado criteria.
	 */
	public void removeIf(Predicate<Map.Entry<K, V>> criterion) {
		if (buckets.length == 0)
			return;

		for (int i = 0; i < buckets.length; ++i) {
			if (buckets[i] != null) {
				// remoçao a cabeça
				if (criterion.eval(buckets[i])) {
					list.remove(buckets[i]); // remove da lista de entradas
					buckets[i] = buckets[i].next;
					--elems;
					return;
				}
				
				//remoçao dentro da lista
				Node<K, V> curr = buckets[i];
				while (curr.next != null) {
					if (criterion.eval(buckets[i])) {
						list.remove(curr); // remove da lista de entradas
						curr.next = curr.next.next;
						--elems;
						return;
					}
					curr = curr.next;
				}
			}
		}
	}

	/*
	 * Iterable<Map.Entry<K, V>> getPairsInInsertionOrder() o qual retorna a
	 * sequ^encia de associac~oes presentes no contentor, segundo a ordem de
	 * inserc~ao destas. E necessario implementar o metodo remove,
	 * pertencente a interface generica Iterator<E>. A implementac~ao deste
	 * metodo deve minimizar o espaco ocupado pelo iterador.
	 */
	public Iterable<Map.Entry<K, V>> getPairsInInsertionOrder() {
		return new Iterable<Map.Entry<K, V>>() {

			public Iterator<Entry<K, V>> iterator() {
				return new Iterator<Entry<K, V>>() {
					Iterator<Node<K, V>> it = list.iterator();
					Node<K,V> lastNode = null;

					public boolean hasNext() {
						return it.hasNext();
					}

					public Entry<K, V> next() {
						Node<K,V> ret = it.next();
						lastNode = ret;
						return ret;
					}

					public void remove() {
						if(lastNode ==null)
							throw new NoSuchElementException();
						AEDDictionary.this.remove(lastNode.key);
						
					}
				};
			}
		};
	}
	
	public static void main(String[]args){
		int [] a = {20, 30, 40, 45, 50, 60, 70, 73, 75};
		Predicate<Map.Entry<Integer, Integer>> criterion = new Predicate <Map.Entry<Integer, Integer>>(){
			@Override
			public boolean eval(Entry<Integer, Integer> val) {
				if (val.equals(45))return true;
				return false;
			};
		};
		Dispersao <Integer, Integer> func = new Dispersao   <Integer, Integer>(){
			public int calc (Integer k){
				return k.hashCode();
			};
		};
		Equality <Integer> equality = new Equality   <Integer>(){
			public int compare (Integer v1, Integer v2){
				if (v1.equals(v2))return 0;
				return -1;
			};
		};
		//public AEDDictionary(int initSize, int loadFactor, Dispersao<K, V> func)
		AEDDictionary<Integer, Integer> Dic= new AEDDictionary<Integer, Integer>(a.length, 75, func, equality);
	}

		
}
