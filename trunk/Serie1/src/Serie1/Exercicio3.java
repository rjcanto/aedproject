package Serie1;

public class Exercicio3 {
	static int read=0;
	static int write=0;
	
	public static void nearestValues(int[] v, int x, int[] rt){
		int i=1;
		int ret =0;
		int [] aux = new int[v.length];
		
		for(int j=0; j<v.length; ++j){
			++read;
			++write;
			aux[j]=v[j];
		}
		ArrayUtils.mergeSort(aux, 0, aux.length-1);
		if((ret=ArrayUtils.indexBinarySearch(aux, x))== x){
			++write;
			rt[0]=x;
		}
		else{
			++read;
			++write;
			rt[0]= aux[ret];
		}
		
		int l = ret-1;
		int r = ret+1;
		
		while( i<rt.length && l>=0 && r<aux.length){
			read+=2;
			int difLef = Math.abs(x-aux[l]);
			int difRig = Math.abs(x-aux[r]); 
			
			if(difLef<=difRig){
				++read;
				++write;
				rt[i]=aux[l];
				--l;
			}
			else{
				read+=2;
				if(rt[i-1]!= aux[r]){
					++read;
					++write;
					rt[i]=aux[r];
				}
			}
			++i;
			++r;
		}
		
		if(l<0 && r<rt.length){
			while(i<rt.length && r<rt.length)
				++read;
				++write;
				rt[i++]=v[r++];
		}
		else if(r>=rt.length && l>0){
			while(i<rt.length && l>=0){
				++read;
				++write;
				rt[i++]=v[l--];
			}
		}
	}
	
	
	public static int nearest (int[] v,int x, int[] r){
		if(r.length > v.length){
			for(int i=0; i<v.length; ++i){
				++read;
				++write;
				r[i]=v[i];
			}
			read+=ArrayUtils.read;
			System.out.println("leituras: " + read+ ", escritas: "+write);
			return v.length;
		}
		else{
			nearestValues(v, x, r);
			read+=ArrayUtils.read;
			System.out.println("leituras: " + read+ ", escritas: "+write);
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
