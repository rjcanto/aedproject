import java.util.Random;

public class Exercicio4 {
	public static long medianOf3(int left, int right) {
		int[] data = new int[3];
		
		for(int i=0; i<data.length; ++i)
			data[i]= left + (int)(Math.random()+(right-left));
		
		int center = (left + right) / 2;
		// order left & center
		if (data[0] > data[center])
			ArrayTools.swap(data, left, center);
		// order left & right
		if (data[0] > data[2])
			ArrayTools.swap(data, left, right);
		// order center & right
		if (data[center] > data[2])
			ArrayTools.swap(data, center, right);

		ArrayTools.swap(data, center, right - 1); // put pivot on right
		return data[right - 1]; // return median value
	}
	
	
	public static void main(String[] args){
		System.out.println(medianOf3(0, 2));
	}
}
