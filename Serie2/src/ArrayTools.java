
public class ArrayTools {
	
	public static int partition(int[]a, int l, int r){
		int pivot = a[r];
		int i=l-1;
		int j=l;
		
		while(j<r){
			if(a[j]<=pivot){
				swap(a,i+1,j);
				++i;
			}
			++j;
		}
		swap(a, i+1, r);
		return i+1;
	}
	
	public static void swap(int[] a, int idx1, int idx2) {
		int aux = a[idx1];
		a[idx1] = a[idx2];
		a[idx2] = aux;
	}

	public static void MinHeapify(int[] a, int i, int count) {
		int left, right, smalest;
		left = i * 2 + 1; // 1º filho da esquerda
		right = i * 2 + 2; // 1º filho da direita

		/*
		 * se o indice da esquerda for menor que o count e o parent for maior
		 * que o filho da esquerda
		 */
		if (left < count && a[i] > a[left])
			smalest = left;
		else
			smalest = i;

		/*
		 * se o indice da direita for menor que o count e o largest for maior
		 * que o filho da direita
		 */
		if (right < count && a[smalest] > a[right])
			smalest = right;

		/*
		 * neste momento o smalest tem um dos 3 indices: -> i -> left -> right
		 */

		/*
		 * caso o smalest seja diferent do pai troca-se o pai com o smalest e
		 * chama-se de novo o heappify apartir do smalest
		 */
		if (smalest != i) {
			ArrayTools.swap(a, i, smalest);
			MinHeapify(a, smalest, count);
		}
	}

	public static void MaxHeapify(int[] a, int i, int count) {
		int left, right, largest;
		left = i * 2 + 1; // 1º filho da esquerda
		right = i * 2 + 2; // 1º filho da direita

		/*
		 * se o indice da esquerda for menor que o count e o parent for menor
		 * que o filho da esquerda
		 */
		if (left < count && a[i] < a[left])
			largest = left;
		else
			largest = i;

		/*
		 * se o indice da direita for menor que o count e o largest for menor
		 * que o filho da direita
		 */
		if (right < count && a[largest] < a[right])
			largest = right;

		/*
		 * neste momento o largest tem um dos 3 indices: -> i -> left -> right
		 */

		/*
		 * caso o largest seja diferent do pai troca-se o pai com o largest e
		 * chama-se de novo o heappify apartir do largest
		 */
		if (largest != i) {
			ArrayTools.swap(a, i, largest);
			MaxHeapify(a, largest, count);
		}
	}

	public static void Mergesort(int[] v, int l, int r) {
		// A range with 0 or 1 elements is always sorted
		if (l >= r)
			return;

		// Find the middle index
		int m = (r + l) / 2;

		// Sort v[l..m]
		Mergesort(v, l, m);
		// Sort v[m+1..r]
		Mergesort(v, m + 1, r);
		// Merge the two sorted ranges into one sorted range
		merge(v, l, m, r);
	}

	public static void merge(int[] v, int l, int m, int r) {
		int l1 = m - l + 1; // dimensão da metade esquerda
		int l2 = r - m; // dimensão da metade direita

		// Array vl contem a copia da metade esquerda
		int[] vl = new int[l1];
		for (int i = 0; i < l1; ++i) {
			vl[i] = v[l + i];
		}

		// Array vr contem a copia da metade direita
		int[] vr = new int[l2];
		for (int i = 0; i < l2; ++i) {
			vr[i] = v[m + i + 1];
		}

		int i = 0, // numero de elementos copiados da metade esquerda
		j = 0, // numero de elementos copiados da metade direita
		k = l;
		// Enquanto nao forem copiados todos os elementos de uma das metades
		while (i < l1 && j < l2) {
			if (vl[i] <= vr[j]) {
				// Se o elemento da metade direita é maior que o elemento da
				// metade esquerda
				// copiar o elemento da metade esquerda
				v[k++] = vl[i++];
			} else {
				// Se o elemento da metade direita é menor que o elemento da
				// metade esquerda
				// copiar o elemento da metade direita
				v[k++] = vr[j++];
			}
		}
		while (i < l1) {
			v[k++] = vl[i++];
		}
		while (j < l2) {
			v[k++] = vr[j++];
		}
	}

	public static void heapSort(int[] a) {
		buildMaxHeap(a, a.length);

		for (int i = 0; i < a.length - 1 && i > 0; ++i) {
			swap(a, i, 0);
			MaxHeapify(a, 0, i);
		}
	}

	public static void buildMaxHeap(int[] a, int count) {
		for (int i = count / 2; i >= 0; --i)
			MaxHeapify(a, i, count);
	}

	public static void maxHeapifyIt(int[] v, int p, int hSize) {
		int l, r, largest;
		for (;;) {
			l = p * 2 + 1;
			largest = (l < hSize && v[l] > v[p]) ? l : p;
			r = p * 2 + 2;
			if (r < hSize && v[r] > v[largest])
				largest = r;
			if (largest == p)
				return;
			swap(v, p, largest);
			p = largest;
		}
	}

	public static void buildMaxHeap1(int[] a, int count) {
		for (int i = count / 2; i >= 0; --i)
			maxHeapifyIt(a, i, count);
	}

	public static void heapSort1(int[] a) {
		buildMaxHeap1(a, a.length);

		for (int i = 0; i < a.length - 1 && i > 0; ++i) {
			swap(a, i, 0);
			maxHeapifyIt(a, 0, i);
		}
	}

	public static void insertionSort(int[] v, int l, int r) {
		for (int i = l + 1; i <= r; ++i) {

			// invariant: v[l..i-1] is sorted
			// it holds on the first iteration since v[l..l] is always sorted

			// insert v[i] into the sorted subarray v[l..i-1]
			int key = v[i];
			int j = i - 1;
			while (j >= l && v[j] > key) {
				// invariant: v[j+1..i] is sorted and is greater or equal than
				// key
				// it holds on the first iteration since v[i..i] is always
				// sorted and equals a key
				v[j + 1] = v[j];
				j = j - 1;
			}
			v[j + 1] = key;

			// invariante: v[l..i] is sorted
		}
		// Since i = r+1, v[l..r] is sorted
	}

	public static void Mergesort1(int[] v, int l, int r) {
		// A range with 0 or 1 elements is always sorted
		if (l >= r)
			return;

		if ((r - l + 1) == 16)
			insertionSort(v, l, r);
		else {
			// Find the middle index
			int m = (r + l) / 2;

			// Sort v[l..m]
			Mergesort(v, l, m);
			// Sort v[m+1..r]
			Mergesort(v, m + 1, r);
			// Merge the two sorted ranges into one sorted range
			merge(v, l, m, r);
		}
	}
}