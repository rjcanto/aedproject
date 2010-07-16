     

public class PriorityQueue <E extends Comparable<E>>{
	private E [] PQ;
	private MinHeap<E> minH;
	private int dim;
	
	@SuppressWarnings("unchecked")
	public PriorityQueue(int size){
		PQ =  (E[]) new Comparable [size];
		minH = new MinHeap();
		dim = 0; 
	}

	public boolean insert(E elem){
		/*if(dim < PQ.length){
			PQ[dim++]=elem;
			minH.HeapSort(PQ);
			return true;
		}*/
		
		if(dim <PQ.length){
			++dim;
			minH.heapIncrease(PQ, dim, elem);
			return true;
		}
		return false;
	}
	
	public E element (){
		if(dim>PQ.length)
			return null;
		return PQ[0];
	}
	
	public boolean remove (E o){
		if(dim<0)
			return false;
		
		PQ[0] = PQ[dim-1];
		dim = dim-1;
		minH.MinHeapify(PQ, 0, dim);
		
		return true;
	}
	
	public E extractMin(){
		if(dim <0)
			return null;
		
		E min = PQ[0];
		PQ[0] = PQ[dim-1];
		dim = dim-1;
		minH.MinHeapify(PQ, 0, dim);
		return min;
	}
	
	int size(){
		return dim;
	}
	
	public E[] getArray(){
		return PQ;
	}
	
	public static void main(String[] args) {
		PriorityQueue<Integer> PQ = new PriorityQueue<Integer>(5);
		PQ.insert(6);
		PQ.insert(1);
		PQ.insert(0);
		PQ.insert(4);
		PQ.insert(2);
		PQ.insert(5);
		System.out.println(PQ.extractMin());
		System.out.println(PQ.extractMin());
		System.out.println(PQ.extractMin());
		System.out.println(PQ.extractMin());
		System.out.println(PQ.extractMin());
		System.out.println("---------------------");
		PQ.insert(0);
		PQ.insert(4);
		PQ.insert(2);
		System.out.println(PQ.extractMin());
		System.out.println(PQ.extractMin());
	}

}