package Exercicio2;

public class testes {
	public static class Node<E> {
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

	public static void createTree(int[] elems, int l, int r, Node<Integer> root) {
		if (l>=r)
			return;

		int meio = (l+(r-l)/2);
		root.value = elems[meio];
		root.left = new Node<Integer>();
		root.right = new Node<Integer>();
		createTree(elems, l, meio-1, root.left);
		createTree(elems,meio+1, r, root.right);
	}
	
	public static void main(String[]args){
		int [] a = {20, 30, 40, 45, 50, 60, 70, 73, 75};
		Node<Integer> root = new Node<Integer>();
		createTree(a, 0, a.length, root);
		inOrder(root);
	}
}
