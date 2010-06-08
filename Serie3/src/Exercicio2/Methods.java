package Exercicio2;

import java.util.LinkedList;

public class Methods {
	static private class Node<E> {
		E value;
		Node<E> left;
		Node<E> right;
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
			Node<E> root, E min, E max) {
		Node<E> curr = root;
		// verificar o min
		while (curr.left != null && (curr.left.value.compareTo(min) >= 0)) {
			curr = curr.left;
		}

		if (curr.left != null)
			curr.left = null;

		// verificar o max
		curr = root;
		while (curr.right != null && curr.right.value.compareTo(max) <= 0) {
			curr = curr.right;
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
	public <E> Node<E> getListOfLeafs(Node<E> t) {
		LinkedList<Node<E>> stack = new LinkedList<Node<E>>();
		if(t.left!=null) {
			Node<E> curr = t ;
			while (curr.left != null) {
				stack.add(curr);
				curr = curr.left ;
			}
			
		}
		return t;
	}

	public static void main(String[] args) {
		Node<Integer> root = new Node<Integer>();
		root.value = 50;
		root.left = new Node<Integer>();
		root.left.value = 70;
		root.right = new Node<Integer>();
		root.right.value = 70;

		System.out.print(isBST(root));
	}
}
