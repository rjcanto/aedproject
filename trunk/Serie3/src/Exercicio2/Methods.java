package Exercicio2;

public class Methods {
	static private class Node <E>{
		E value;
		Node<E> left;
		Node<E> right;
	}
	
	/*que retorna true se e so se a arvore binaria, referida por h, 
	 *for uma arvore binaria de pesquisa.
	 * */
	public <E extends Comparable<E>> boolean isBST(Node<E> h){
		if(h==null)
			return false;
		
		if(h.value.compareTo(h.left.value)<0)
			return false;
		if(h.value.compareTo(h.right.value)>0)
			return false;
		
		isBST(h.left);
		isBST(h.right);
		
		return true;
	}
	
	public static void main(String[] args) {
		
		
	}
}
