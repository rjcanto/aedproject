package Grupo2_Ex2;

public class Pair<T1, T2> {
	private T1 elem1;
	private T2 elem2;
	
	public Pair(T1 elem1, T2 elem2){
		this.elem1 = elem1;
		this.elem2 = elem2;
	}
	
	public String toString(){
		return elem1+","+elem2;
	}
}
