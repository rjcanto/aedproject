package Serie1;

public class testes {

	public static void mergeSort(int[] v, int l, int r) {
		if (l < r) {
			int meio = l + (r - l) / 2;
			mergeSort(v, l, meio);
			mergeSort(v, meio + 1, r);
			merge(v, l, meio, r);
		}
	}

	private static void merge(int[] v, int l, int meio, int r) {
		int[] left = new int[meio - l + 1];
		int[] rigth = new int[r - meio];

		for (int i = 0; i < left.length; ++i)
			left[i] = v[l + 1];

		for (int i = 0; i < rigth.length; ++i)
			rigth[i] = v[meio + 1 + i];

		int i = 0, j = 0, w = l;

		while (i < left.length && j < rigth.length) {
			if (left[i] < rigth[i]) {
				v[w] = left[i];
				++i;
			} else {
				v[w] = rigth[i];
				++j;
			}
			++w;
		}

		while (i < left.length)
			v[w++] = left[i++];

		while (i < rigth.length)
			v[w++] = rigth[i++];
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

	public static void main(String[] args) {
		int[] a = { -2, -4, 1, -5 };
		selectionSort(a);

		for (int i = 0; i < a.length; ++i)
			System.out.println(a[i]);
	}

}
