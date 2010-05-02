
public class Exercicio2 {
	
	public static void heapChangeValue(int[] v, int len, int ix, int newValue){
		if(v[0]<v[1]){
			v[ix]=newValue;
			
			while(((ix-1) >= 0) && ((v[ix-1]/2) > v[ix]))
				ix=v[ix-1]/2;
			
			
			ArrayTools.MinHeapify(v, ix, len);
		}
		else{
			v[ix]=newValue;
			while(((ix-1)>=0) && (v[ix-1]/2 < v[ix]))
				ix=v[ix-1]/2;
			
			
			ArrayTools.MaxHeapify(v, ix, len);
		}
	}
	
	public static void main(String[] args){
		int[] a = {10, 15, 12, 30, 20, 25, 15};
		heapChangeValue(a, a.length, 0, 27);
		
		for(int i=0; i<a.length; ++i)
			System.out.println(a[i]);
			
	}
}
