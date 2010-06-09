package Exercicio2;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Stack;


public class Methods <E> {
	LinkedList<Node<E>> stack = new LinkedList<Node<E>>();
	
	static private class Node<E> {
		E value;
		Node<E> left;
		Node<E> right;
	}

	public static <E> void inOrder(Node<E> root){
		if(root==null)
			return;
		
		inOrder(root.left);
		if(root.value!=null)
			System.out.println(root.value);
		inOrder(root.right);
	}
	
	/*
	 * que retorna true se e so se a arvore binaria, referida por h,for uma
	 * arvore binaria de pesquisa.
	 */
	public static <E extends Comparable<E>> boolean isBST(Node<E> h) {
		if (h == null)
			return false;

		if (h.left != null && h.value.compareTo(h.left.value) < 0)
			return false;
		if (h.right != null && h.value.compareTo(h.right.value) > 0)
			return false;

		isBST(h.left);
		isBST(h.right);

		return true;
	}

	/*
	 * que retira da arvore binaria de pesquisa, referenciada por root,todos os
	 * elementos nao pertencentes ao intervalo [min, max].Note que o elementos
	 * min e max podem nao estar presentes na arvore.O metodo retorna a raiz da
	 * arvore, apos a remoçao dos elementos.
	 */
	public static <E extends Comparable<E>> Node<E> removeAllNotInInterval(	
			Node<E> root, E min, E max){

		Node<E> curr = root;
		
		// verificar o min
		if(min.compareTo(root.value)>0){
			while (curr.right != null && curr.right.value.compareTo(min) <= 0) {
				curr = curr.right;
			}
			root = curr;
		}
		else{
			while (curr.left != null && (curr.left.value.compareTo(min) >= 0)) {
				curr = curr.left;
			}
		}
		//elimina os menores
		if (curr.left != null)
			curr.left = null;

		// verificar o max
		curr = root;
		
		if(max.compareTo(root.value)<0){
			curr = root.left;
			while (curr.right != null && curr.right.value.compareTo(max) <= 0) {
				curr = curr.right;
			}
			root = curr;
		}
		else{
			while (curr.right != null && curr.right.value.compareTo(max) <= 0) {
				curr = curr.right;
			}
		}
		if (curr.right != null)
			curr.right = null;

		return root;
	}

	/*
	 * que, dada a arvore binaria de pesquisa referenciada por t, retorna uma
	 * lista simplesmente ligada com os valores presentes nos nos folha de t. A
	 * lista retornada deve estar ordenada e a arvore deve permanecer
	 * inalterada
	 */
	public static <E> Node<E> getListOfLeafs(Node<E> t) {
		Node<E> aux = new Node<E>();
		getListOfLeafs(t, aux);
		return aux;
	}
	
	private static <E> Node<E> getListOfLeafs(Node<E> t, Node<E> list) {
		if(t == null) return list;
		list = getListOfLeafs(t.left, list);
		list = getListOfLeafs(t.right, list);
		if(t.left==null && t.right == null) list.value = t.value;
		else return list;
		list.right = new Node<E>(); 
		return list.right;
	}

	private Node<E> min (Node<E> root){
		if(root == null)
			return null;
		
		Node<E> curr = root;
		
		while(curr.left!=null){
			stack.addLast (curr);
			curr = curr.left;
		}
		return curr;
	}
	
	private Node<E> after (Node<E> n){
		if(n.right!=null){
			return min(n);
		}
		else{
			return stack.peekLast();
		}
	}
	
	public Iterable<E> inOrderIterator(final Node<E> h){
		return new Iterable<E>(){

			public Iterator<E> iterator() {
				return new Iterator<E>(){
					Node<E> curr = min(h);
					E elem = curr.value;
					
					public boolean hasNext() {
						if(elem != null)
							return true;
						
						curr = after(curr);
						
						if(curr!=null)
							elem = curr.value;
						
						return curr!=null;
					}

					public E next() {
						if(!hasNext()){
							throw new NoSuchElementException();
						}
						E next = elem;
						elem = null;
						return next;
					}

					public void remove() {
						throw new UnsupportedOperationException();
					}
				};
			}
		};
	}
	
	public static void main(String[] args) {
		Node<Integer> root = new Node<Integer>();
		root.value = 100;
		root.left = new Node<Integer>();
		root.left.value = 90;
		root.left.left = new Node<Integer>();
		root.left.left.value = 80; 
		root.left.right = new Node<Integer>();
		root.left.right.value = 95;
		root.left.left.right = new Node<Integer>();
		root.left.left.right.value = 85; 
		root.left.right.left = new Node<Integer>();
		root.left.right.left.value = 93; 
		root.right = new Node<Integer>();
		root.right.value = 120;
		root.right.left = new Node<Integer>();
		root.right.left.value = 110;
		root.right.left.right = new Node<Integer>();
		root.right.left.right.value = 115;
		root.right.right = new Node<Integer>();
		root.right.right.value = 130;
		root.right.right.right = new Node<Integer>();
		root.right.right.right.value = 135;
		root.right.right.right.left = new Node<Integer>();
		root.right.right.right.left.value = 132;
		

		//System.out.print(isBST(root));
		Node<Integer> aux;
		/*aux = getListOfLeafs(root);
		while(aux.right != null) {
			System.out.println(aux.value);
			aux = aux.right;
		}
		*/
		aux = removeAllNotInInterval(root, 80, 120);
		inOrder(aux);
	}
}
