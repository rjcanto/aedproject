public class MinHeap<E extends Comparable<E>>{
	
	public MinHeap(E [] a){
		buildMinHeap(a, a.length);
	}
	
	private <E> void swap(E[] a, int idx1, int idx2) {
		E aux = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = aux;
	}

	private <E extends Comparable<E>> void MinHeapify(E[] a, int i, int count) {
		int left, right, smalest;
		left = i * 2 + 1; // 1º filho da esquerda
		right = i * 2 + 2; // 1º filho da direita

		/*
		 * se o indice da esquerda for menor que o count e o parent for maior
		 * que o filho da esquerda
		 */
		if (left < count && a[i].compareTo(a[left])>0)
			smalest = left;
		else
			smalest = i;

		/*
		 * se o indice da direita for menor que o count e o largest for maior
		 * que o filho da direita
		 */
		if (right < count && a[smalest].compareTo(a[right])>0)
			smalest = right;

		/*
		 * neste momento o smalest tem um dos 3 indices: -> i -> left -> right
		 */

		/*
		 * caso o smalest seja diferent do pai troca-se o pai com o smalest e
		 * chama-se de novo o heappify apartir do smalest
		 */
		if (smalest != i) {
			swap(a, i, smalest);
			MinHeapify(a, smalest, count);
		}
	}

	private <E extends Comparable<E>> void buildMinHeap(E[] a, int count) {
		for (int i = count / 2; i >= 0; --i)
			MinHeapify(a, i, count);
	}
	
	public static void main (String [] args){
		//Integer[] a = {2, 4, 7, 13, 15, 12, 17};
		Integer [] a = {17, 12, 20, 9, 7 , 6, 5};
		MinHeap<Integer> min = new MinHeap<Integer>(a);
		
		for(int i=0; i<a.length; ++i)
			System.out.println(a[i]);
	}
}
