package Serie1;

public class Recursivo1 {
	static int c8, c1,c2,c3,c4,c5,c6,c7;
	static double res;
	public static int method (int a,int  n){
		c1++;
		if(n==0){
			c2++;
			return 1;
		}
		c3++;
		if(n==1){
			c4++;
			return a;
		}
		c5++;
		if((n%2)==0){
			c6++;
			res=method(a,n/2);
			return (int) (res*res);
		}
		c7++;
			res =method(a,(n-1)/2);
			return (int) (res*res*a);
	}
	public static void main(String[] args) {
		System.out.println(method (799,2*2*2*2*2*2));
		System.out.println("chamou "+ c6);
		System.out.println("chamou "+ c7);
		System.out.println(res);
	}
}
