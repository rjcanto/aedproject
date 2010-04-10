package Serie1;

import java.util.*;

public class Exercicio4 {
	static double timeInit;
	static double timeFin;

	public static int[] geraArray(int len) {
		int[] a = new int[len];
		Random r = new Random();

		for (int i = 0; i < len; ++i) {
			a[i] = r.nextInt();
		}

		Arrays.sort(a);
		return a;
	}

	public static int[] criaArray(int len) {
		return new int[len];
	}

	public static void main(String[] args) {

		ex41();
		ex42();
		ex43();
	}

	public static void ex41() {
		int[] a;
		int[] array1 = { 16, 32, 48, 64, 128, 256, 512, 1024, 1536, 2048, 4096,
				8192, 16384, 32768, 49152, 65536 };
		double[] times = new double[array1.length];

		for (int i = 0; i < array1.length; ++i) {
			a = geraArray(array1[i]);
			Arrays.sort(a);
			timeInit = System.nanoTime();
			Exercicio1.findElementsThatSumTo(a, 13);
			timeFin = System.nanoTime();
			times[i] = timeFin - timeInit;
			System.out.println("Read: "+Exercicio1.read+ ", Write: "+Exercicio1.write);
		}

		for (int i = 0; i < times.length; ++i) {
			System.out.println(times[i]);
		}
	}

	public static void ex42() {
		int[] v1 = { 128, 256, 384, 512, 768, 1024 };
		int[] v2 = { 64, 128, 192, 256, 512, 768 };
		int[] a1;
		int[] a2;

		double[] times2 = new double[v1.length * v2.length];
		int nTimes2 = 0;

		for (int i = 0; i < v1.length; ++i) {
			a1 = geraArray(v1[i]);
			for (int j = 0; j < v2.length; ++j) {
				a2 = geraArray(v2[j]);
				Arrays.sort(a1);
				Arrays.sort(a2);
				timeInit = System.nanoTime();
				Exercicio2.numberOfDistinctElementsOccuringOnBothSortedArrays(
						a1, a2);
				timeFin = System.nanoTime();
				times2[nTimes2++] = timeFin - timeInit;
				System.out.println("Read: "+Exercicio2.read+ ", Write: "+Exercicio2.write);
			}
		}

		for (int i = 0; i < times2.length; ++i) {
			System.out.println(times2[i]);
		}
	}

	public static void ex43() {
		int[] v = { 32, 64, 96, 128, 256, 512, 1024, 2048, 3072, 4096 };
		int[] r = { 4, 8, 12, 16, 20, 24 };
		int[] a3;
		int[] a4;

		double[] times3 = new double[v.length * r.length];
		int nTimes3 = 0;
		for (int i = 0; i < v.length; ++i) {
			a3 = geraArray(v[i]);
			for (int j = 0; j < r.length; ++j) {
				a4 = criaArray(r[j]);
				timeInit = System.nanoTime();
				Exercicio3.nearestValues(a3, 4, a4);
				timeFin = System.nanoTime();
				times3[nTimes3++] = timeFin - timeInit;
				System.out.println("Read: "+Exercicio3.read+ ", Write: "+Exercicio3.write);
			}
		}

		for (int i = 0; i < times3.length; ++i) {
			System.out.println(times3[i]);
		}
	}
}