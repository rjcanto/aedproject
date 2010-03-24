package Serie1;

public class Exercicio2 {
	
	public static int FindCommonElements(int[] a1, int[] a2){
		int count = 0;
		
		if(a1.length == 0 || a2.length == 0)
			return -1;
		
		for(int i=0; i< a1.length-1; ++i){
			if(a1[i] != a1[i+1])
				count += ArrayUtils.CountBinarySearch(a2, a1[i]);
		}
		if(a1[a1.length-1]==a1[a1.length-2])
			count += ArrayUtils.CountBinarySearch(a2, a1[a1.length-1]);
		
		return count;
	}
	
	public static int numberOfDistinctElementsOccuringOnBothSortedArrays(int[] v1, int[] v2){
		//verificar o menor array 
		if(v1.length <= v2.length)
			return FindCommonElements(v1, v2);
		return FindCommonElements(v2, v1);
	}
	
	public static void main(String[] args) {
		int [] a1 = {1,2,3,3,4,4,5,5,6};
		int [] a2 = {0,2,2,2,5,6,6};
		int [] a3 = {0,1,2,3,4,5,6,7,8,9,10};
		int [] a4 = {1,3,3,3,4,7,7};
		int [] a5 = {};
		int [] a6 = {1,1,1,1,1,1,1,1,1};
		
		int retorno = numberOfDistinctElementsOccuringOnBothSortedArrays(a6, a1);
		if( retorno == 0)
			System.out.println("Nao tem elementos em comum.");
		else if(retorno == -1)
			System.out.println("Por favor introduza arrays com valores.");
		else
			System.out.println(retorno);
	}

}
