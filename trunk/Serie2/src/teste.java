
public class teste {
	public static int partition(int[]a, int l, int r){
		int pivot = a[r];
		int i=l-1;
		int j=l;
		
		while(j<r){
			if(a[j]<=pivot){
				ArrayTools.swap(a,i+1,j);
				++i;
			}
			++j;
		}
		ArrayTools.swap(a, i+1, r);
		return i+1;
	}
	
//	public static int partition( int[] v, int l, int r){
//		int pivot = v[r];
//		int i = l-1;
//		for ( ; v[i+1] < pivot && (i+1) < r; ++i);
//		// 	Invariantes do ciclo
//		// 	  v[l .. i] <= pivot
//		//    v[i+1 .. j] > pivot
//		//    v[j .. r-1] em análise
//		for (int j = i+1 ; j < r ; ++j)
//			if ( v[j] <= pivot ) 
//				ArrayTools.swap( v, ++i, j ); 
//		// Coloca o pivot entre os menores e os maiores
//		ArrayTools.swap(v, ++i, r); 
//		return i;
//	}
	
	
	public static void main(String[] args) {
		int[] a = {30, 20, 25, 13, 10, 20, 12};
		System.out.println(partition(a, 0, a.length-1));
		for(int i=0; i<a.length; ++i)
			System.out.println(a[i]);

	}

}
