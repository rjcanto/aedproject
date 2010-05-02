
public class Exercicio4 {
	public static int medianOf3(int[] v, int left, int right) {
		int[] data = new int[3];
		for(int i=0; i<data.length; ++i){
			data[i]=(int)(left + Math.random()*right);
			//System.out.println(data[i]);	
		}
		
		// order left & center
		if (data[0] > data[1])
			ArrayTools.swap(data, 0, 1);
		// order left & right
		if (data[0] > data[2])
			ArrayTools.swap(data, 0, 2);
		// order center & right
		if (data[1] > data[2])
			ArrayTools.swap(data, 1, 2);

		ArrayTools.swap(v, data[1], right); // put medianOf3pivot on right
		//return data[right - 1]; // return median value
		return ArrayTools.partition(v, left, right);
	}
	
	public static int medianOf5(int[] v, int left, int right) {
		int[] data = new int[5];
		
		for(int i=0; i<data.length; ++i){
			data[i]= left + (int)(Math.random()*(right-left));
		}
		int aux[]= new int [5];
		countingSort(data,aux, 5);

		//return aux[right - 2]; // return median value
		return ArrayTools.partition(v, left, right);
		
	}
	
		public static void countingSort(int[] a, int[] b, int k) {
			int[] c= new int[k+1];
			for (int i= 0; i < a.length; ++i)
				++c[a[i]];
			for (int i= 1; i < c.length; ++i)
				c[i]+= c[i-1];
			for (int i= a.length-1; i >=0; --i) 
				b[ --c[ a[i] ] ] = a[i];
		}
		
		public static void quickSort1(int[]a, int l, int r){
			if(l>=r) return;
			int p = medianOf3(a, l, r);
			quickSort1(a, l, p-1);
			quickSort1(a, p+1, r);
		}
		
		public static void quickSort2(int[]a, int l, int r){
			if(l>=r) return;
			int p = medianOf5(a, l, r);
			quickSort2(a, l, p-1);
			quickSort2(a, p+1, r);
		}

	public static void main(String[] args){
		//System.out.println(medianOf3(0, 2));
		//System.out.println(medianOf5(0, 5));
//		for(int i=0; i<7; ++i)
//			System.out.println((int)(0+Math.random()*7));
		
		int[] a = {30, 20, 25, 13, 10, 20, 12};
		//quickSort1(a, 0, a.length-1);
		quickSort2(a, 0, a.length-1);
		for(int i=0; i<a.length; ++i)
			System.out.println(a[i]);
	}
}
