package Serie1;


public class ArrayUtils {
	
/*	public static void mergeSort1 (int[] v, int l, int r){
		if(l<r){
			int meio = l+(r-l)/2;
			mergeSort1(v, l, meio);
			mergeSort1(v, meio+1, r);
			merge (v,l, meio, r);
		}	
	}
	*/
	private static void merge(int[] v, int l, int meio, int r) {
		int [] aux = new int[v.length];
		int j = l, k=meio+1;
		
		for(int i=0; i<v.length-1; ++i){
			if(v[i] < v[k] && j<= meio){
				aux[i]=v[j];
				++i;
			}
			else{
				if(k < v.length){
					aux[i]=v[k];
					++k;
				}
			}
		}
		
		for(int i=0; i<v.length; ++i){
			v[i]=aux[i];
		}
	}
	
	public static void swap (int[] v, int l, int r){
		int temp = v[l];
		v[l] = v[r];
		v[r]=temp;
	}
	
	public static int CountBinarySearch (int[]v, int value){
		int l =0, r = v.length-1, mid = 0;
		
		while(l<=r){
			mid = l+(r-l)/2;
			
			if(v[mid] == value)
				return 1;
			
			if(v[mid] > value)
				r = mid-1;
			else
				l = mid+1;
		}
		return 0;
	}
	
	public static int BinarySearch (int[]v, int value){
		int l =0, r = v.length-1, mid = 0;
		
		while(l<=r){
			mid = l+(r-l)/2;
			
			if(v[mid] == value)
				return mid;
			
			if(v[mid] > value)
				r = mid-1;
			else
				l = mid+1;
		}
		return -1;
	}
	
	public static int indexBinarySearch(int[]v, int value){
		int l =0, r = v.length-1, mid = 0;
		
		while(l<=r){
			mid = l+(r-l)/2;
			
			if(v[mid] == value)
				return mid;
			
			if(v[mid] > value)
				r = mid-1;
			else
				l = mid+1;
		}
		return l;
	}
	
	public static int lowerBound (int [] v, int value){
		int l=0, r=v.length-1,mid=0;
		while (l<=r){
			mid =l+(r-l)/2;
			if(v[mid]>=value) r=mid-1;
			else l=mid+1;
		}
	return l;
	}
	
	public static void selectionSort(int[] v) {
		for (int i = 0; i < v.length-1; ++i) {
			int menor = SelectMenor(v, i);
			int aux;
			if(v[i]>v[menor]){
				aux = v[i];
				v[i]=v[menor];
				v[menor] = aux;
			}
		}
	}

	private static int SelectMenor(int[] v, int l) {
		int menor = -1;
		for(int i=l; i<v.length-1; ++i){
			if(v[i]<v[i+1])
				menor = i;
			else
				menor = i+1;
		}
		return menor;
	}
	
	public static void mergeSort(int[] list, int start, int end) {
		if (start >= end)
			return; // too many splits (return unsortable array)

		int mid = (start + end) / 2; // middle point

		mergeSort(list, start, mid); // split up left half
		mergeSort(list, mid + 1, end); // split up right half

		// start sorting
		int left_end = mid;
		int right_start = mid + 1;

		while ((start <= left_end) && (right_start <= end)) {
			if (list[start] >= list[right_start]) // left most element of Left
													// side > left most element
													// of Right side
			{
				int temp = list[right_start];

				for (int i = right_start - 1; i >= start; i--) {
					list[i + 1] = list[i]; // shift elements
				}
				list[start] = temp; // insert element where it belongs
				mid++;
				right_start++; // advance counters
			}
			start++; // advance in either case
		}
		return;
	}
	
}
