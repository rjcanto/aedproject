package Testes;
public class MinHeap<E extends Comparable<E>>{
	
//	public MinHeap(E [] a){
//		buildMinHeap(a, a.length);
//	}
	
	public void heapIncrease (E[]a, int i, E key){
		i = i-1;
		//if(a[i] != null && key.compareTo(a[i])> 0)
			//return;
		
		a[i]=key;
		
		/*if(i==1){
			if(a[i].compareTo(a[i-1])>0){
				E aux = a[i];
				a[i] = a[i-1];
				a[i-1] = aux;
			}
			return;
		}*/
		
		while(i>0 && a[((i-1)/2)].compareTo(a[i])>0){
			E aux = a[i];
			a[i] = a[((i-1)/2)];
			a[((i-1)/2)] = aux;
			i = ((i-1)/2)-1;
		}
	}
	
	@SuppressWarnings("hiding")
	private <E> void swap(E[] a, int idx1, int idx2) {
		E aux = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = aux;
	}

	@SuppressWarnings("hiding")
	public <E extends Comparable<E>> void MinHeapify(E[] a, int i, int count) {
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

	@SuppressWarnings("hiding")
	public <E extends Comparable<E>> void buildMinHeap(E[] a, int count) {
		for (int i = count / 2; i >= 0; --i)
			MinHeapify(a, i, count);
	}
	
	public void HeapSort (E[] a){
		buildMinHeap(a, a.length);
		
		for(int i=a.length-1; i>0; --i){
			swap(a, i, 0);
			MinHeapify(a, 0, i);
		}
		
		for(int left=0, right = a.length-1; left<=right; ++left, --right){
			E aux = a[left];
			a[left] = a[right];
			a[right] = aux;
		}
			
	}
	
//	public static void main (String [] args){
//		Integer[] a = {2, 4, 7, 13, 15, 12, 17};
//	//	Integer [] a = {17, 12, 20, 9, 7 , 6, 5};
//	//	MinHeap<Integer> min = new MinHeap<Integer>(a);
//		MinHeap<Integer> min = new MinHeap<Integer>();
//		min.HeapSort(a);
//		for(int i=0; i<a.length; ++i)
//			System.out.println(a[i]);
//	}
}
