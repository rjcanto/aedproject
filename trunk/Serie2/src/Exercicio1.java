
public class Exercicio1 {
	/**
	 * 1º trocar o indice que se quer remover com a ultima folha da direita
	 * seguidamente chamar o heapify a partir do indice ix
	 * **/
	public static void removeFromMaxHeap(int[] v, int count, int ix){
		ArrayTools.swap(v, ix, count);
		ArrayTools.MaxHeapify(v, ix, --count);
	} 
	
	public static void main (String[] args){
		int[] a = {30, 20, 25, 13, 10, 20, 12};
		
		removeFromMaxHeap(a, a.length-1, 1);
		
		/*vai ate length-1 porque o ultimo */
		for(int i=0; i<a.length-1; ++i){
			System.out.println(a[i]);
		}
	}
}