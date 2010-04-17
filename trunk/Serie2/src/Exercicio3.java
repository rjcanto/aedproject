import java.util.Random;

public class Exercicio3 {
	public static void alinea1(){
		int[] a = null;
		double time1, time2;
		for (int i = 16; i < 8192; i += 16) {
			a = new int[i];

			Random r = new Random();

			for (int j = 0; j < a.length; ++j)
				a[j] = r.nextInt();

			time1 = System.nanoTime();
			ArrayTools.Mergesort(a, 0, a.length - 1);
			time1 = System.nanoTime() - time1;

			time2 = System.nanoTime();
			ArrayTools.heapSort(a);
			time2 = System.nanoTime() - time2;

			System.out.println(a.length + " - " + time1);
			System.out.println(a.length + " - " + time2);
		}
	}
	
	public static void alinea2(){
		int[] a = null;
		double time1;
		for (int i = 16; i < 8192; i += 16) {
			a = new int[i];

			Random r = new Random();

			for (int j = 0; j < a.length; ++j)
				a[j] = r.nextInt();

			time1 = System.nanoTime();
			ArrayTools.heapSort1(a);
			time1 = System.nanoTime() - time1;

			System.out.println(a.length + " - " + time1);
		}
	}
	
	public static void alinea3(){
		int[] a = null;
		double time1;
		for (int i = 16; i < 8192; i += 16) {
			a = new int[i];

			Random r = new Random();

			for (int j = 0; j < a.length; ++j)
				a[j] = r.nextInt();

			time1 = System.nanoTime();
			ArrayTools.Mergesort1(a, 0, a.length-1);
			time1 = System.nanoTime() - time1;

			System.out.println(a.length + " - " + time1);
		}
	}
	
	
	
	public static void main(String[] args) {
		//alinea1();
		//alinea2();
		//alinea3();
		
	}

}
