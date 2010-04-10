package Serie1;

public class Exercicio1 {
	 /*
	 * Dado o array ordenado v e o inteiro s, retorna dois elementos desse array
	 * tal que a sua soma seja igual a s. Esses dois elementos sao retornados
	 * atraves dum array com duas posiçoes. Caso nao existam dois elementos cuja
	 * soma seja s, deve ser retornado null. Serao penalizadas as soluçoes com
	 * custo superior a O(n), onde n é a dimensao de v.
	 */
	static int read = 0;
	static int write = 0;

	public static int retirarMaiores(int[] a, int value) {
		int meio = -1, l = 0, r = a.length - 1;
		
		while (l <= r) {
			meio = l + (r - l) / 2;
			// se o valor do meio foi igual ao value retorna a ultima posiçao
			// valida
			++read;
			if (a[meio] == value)
				return meio - 1;
			// caso o valor do meio seja maior que o value
			++read;
			if (a[meio] > value) {
				// verificar se o anterior é menor
				++read;
				if (meio != 0 && a[meio - 1] < value)
					return meio - 1; // caso seja retorna meio-1
				else
					r = meio - 1; // se nao o right passa a ser meio-1
			} else {
				// se o valor do meio for menor que o meio e o valor seguinte
				// for maior
				++read;
				if ((meio != a.length - 1) && a[meio + 1] > value)
					return meio; // retorna-se o meio
				else
					l = meio + 1; // se nao actualiza-se o left para meio+1;
			}
		}
		return -1;
	}

	public static int[] searchPositions(int[] v, int value, int l, int r) {
		int sub = -1;
		int[] retorno = new int[2];

		while (l <= r) {
			read +=2;
			sub = v[r] + v[l];
			if (sub == value) {
				read +=2;
				write +=2;
				retorno[0] = v[l];
				retorno[1] = v[r];
				return retorno;
			} else {
				if (sub > value)
					--r;
				else
					++l;
			}
		}
		return null;
	}

	public static int[] findElementsThatSumTo(int[] v, int s) {
		++read;
		if (v[0] >= 0) {
			int maiores = retirarMaiores(v, s);
			if (maiores == -1)
				return null;
			return searchPositions(v, s, 0, maiores);
		}
		return searchPositions(v, s, 0, v.length - 1);
	}

	public static void main(String[] args) {
		int[] v1 = { 1, 2, 3, 4, 7, 9, 12, 14, 16, 17 };
		int[] v2 = { 12, 14, 16, 17 };
		int[] v3 = {};
		int[] v4 = { 14, 16, 17 };
		int[] v5 = { 1, 2, 13 };
		int[] v6 = { 1, 2, 3 };
		int[] v7 = { -2, -1, 0, 14 };
		int value = 13;
		int[] ret = findElementsThatSumTo(v7, value);

		if (ret != null)
			System.out.println(ret[0] + " " + ret[1]);
		else
			System.out.println("Nao existem valores.");
		System.out.println("leituras: " + read+ ", escritas: "+write);
		/*
		 * System.out.println(findElementsThatSumTo(v1, value));
		 * System.out.println(findElementsThatSumTo(v2, value));
		 * System.out.println(findElementsThatSumTo(v3, value));
		 * System.out.println(findElementsThatSumTo(v4, value));
		 * System.out.println(findElementsThatSumTo(v5, value));
		 * System.out.println(findElementsThatSumTo(v6, value));`
		 */
	}
}
