package Serie1;

public class Exercicio3 {

	public static void nearestValues(int[] v, int x, int[] rt){
		int i=1;
		int ret =0;
		int [] aux = new int[v.length];
		
		for(int j=0; j<v.length; ++j)
			aux[j]=v[j];
		
		ArrayUtils.mergeSort(v, 0, v.length-1);
		if((ret=ArrayUtils.indexBinarySearch(v, x))== x)
			rt[0]=x;
		else
			rt[0]= v[ret];
		
		int l = ret-1;
		int r = ret+1;
		
		while( i<rt.length && l>=0 && r<v.length){
			int difLef = Math.abs(x-v[l]);
			int difRig = Math.abs(x-v[r]); 
			
			if(difLef<=difRig){
				rt[i]=v[l];
				--l;

			}
			else{
				if(rt[i-1]!= v[r]){
					rt[i]=v[r];
				}
			}
			++i;
			++r;
		}
		
		if(l<0 && r<rt.length){
			while(i<rt.length && r<rt.length)
				rt[i++]=v[r++];
		}
		else if(r>=rt.length && l>0){
			while(i<rt.length && l>=0)
				rt[i++]=v[l--];
		}
	}
	
	
	public static int nearest (int[] v,int x, int[] r){
		if(r.length > v.length){
			for(int i=0; i<v.length; ++i)
				r[i]=v[i];
			return v.length;
		}
		else{
			nearestValues(v, x, r);
			return r.length;
		}		
	}
	
	public static void main(String[] args) {
		int[] a1 = {-2,3,4,5,2};
		int[] r = new int[2];
		System.out.println(nearest(a1, 4, r));
		System.out.println("");
		for(int i=0; i<r.length; ++i)
			System.out.println(r[i]);
	}
}
