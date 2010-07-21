import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class HuffmanCode {

	public static PriorityQueue<NodeHuffman> buildPriorityQueue(BitSource in) {
		PriorityQueue<NodeHuffman> pq = new PriorityQueue<NodeHuffman>(7);
		// TODO falta fazer a adaptaçao do BitSource
		String s = "olaaa";
		int i = 0;
		while (i < s.length()) {
			try {
				CountBytes.freqTable(s.charAt(i++));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int[] freqT = CountBytes.getArray();

		for (i = 0; i < freqT.length; ++i) {
			if (freqT[i] != 0) {
				NodeHuffman nd = new NodeHuffman();
				nd.character = (char) i;
				nd.freq = freqT[i];
				nd.isLeaf = true;
				pq.insert(nd);
				++i;
			}
		}
		return pq;
	}

	public static NodeHuffman huffman(PriorityQueue<NodeHuffman> C) {
		// int n = C.size();
		PriorityQueue<NodeHuffman> Q = C;

		for (int i = 0; i < /* n-1 */C.size(); ++i) {
			NodeHuffman z = new NodeHuffman();
			NodeHuffman nl = new NodeHuffman();
			NodeHuffman nr = new NodeHuffman();
			nl = Q.extractMin();
			nr = Q.extractMin();
			z.left = nl;
			z.right = nr;
			z.freq = nl.freq + nr.freq;
			Q.insert(z);
		}
		return Q.extractMin();
	}

	public static CodHuffman[] getListOfLeafs(NodeHuffman t) {
		CodHuffman[] a = new CodHuffman[256];
		NodeHuffman aux = new NodeHuffman();
		getListOfLeafs(t, aux, 0, 0);

		while (aux.right != null) {
			char value = 0;
			if (aux.character < 0 && aux.character > -129) {
				value = (char) (aux.character * (-1) + 127);
				a[value] = aux.cod;
			}else{
				a[aux.character]=aux.cod;
			}
				aux = aux.right;
		}
		return a;
	}

	private static NodeHuffman getListOfLeafs(NodeHuffman t, NodeHuffman list,
			int bits, int cod) {
		int bitsLeft = bits;
		int bitsRight = bits;
		int codLeft = cod;
		int codRight = cod;
		if (t == null)
			return list;

		codLeft = (codLeft | 0) << 1;
		list = getListOfLeafs(t.left, list, ++bitsLeft, codLeft);
		codRight = (codRight | 1) << 1;
		list = getListOfLeafs(t.right, list, ++bitsRight, codRight);
		if (t.left == null && t.right == null) {
			list.character = t.character;
			list.freq = t.freq;
			cod = cod >> 1;
			CodHuffman cd = new CodHuffman(cod, bits);
			list.cod = cd;
		} else
			return list;
		list.right = new NodeHuffman();

		return list.right;
	}

	/* testar se é arvore de Huffman */
	public static boolean checkSiblingProp(NodeHuffman nh) {
		NodeHuffman previous = null;
		ArrayList<NodeHuffman> binList = new ArrayList<NodeHuffman>();
		binList.add(nh);
		while (binList.size() > 0) {
			NodeHuffman node = binList.remove(0);
			if (node.left != null) {
				if (node.left.freq >= node.right.freq) {
					binList.add(node.left);
					binList.add(node.right);
				} else {
					binList.add(node.right);
					binList.add(node.left);
				}
			}
			if (previous != null)
				if (previous.freq < node.freq)
					return false;

			previous = node;
		}
		return true;
	}

	public static CodHuffman inOrder(NodeHuffman n, char s, int bits, int cod,
			CodHuffman ret) {
		int bitsLeft = bits;
		int bitsRight = bits;
		int codLeft = cod;
		int codRight = cod;

		if (n == null)
			return ret;
		codLeft = (codLeft | 0) << 1;
		ret = inOrder(n.left, s, ++bitsLeft, codLeft, ret);
		codRight = (codRight | 1) << 1;
		ret = inOrder(n.right, s, ++bitsRight, codRight, ret);

		if (n.left == null && n.right == null && n.character == s) {
			cod = cod >> 1;
			return new CodHuffman(cod, bits);
		}
		return ret;
	}

	public static CodHuffman getCode(NodeHuffman n, char s) {
		CodHuffman cH = new CodHuffman(0, -1);
		cH = inOrder(n, 'o', 0, 0, cH);
		return cH;
	}

	public static char getSimbol (NodeHuffman n, CodHuffman cod){
		int c = cod.code;
		int bits = cod.bits;
		while(bits>0){
			int bit = c&0x1;
			if(bit == 0)
				n=n.left;
			else 
				n = n.right;
			--bits;
		}
		return n.character;
	}
	
	public static void PrintinOrder(NodeHuffman root){
		if(root==null)
			return;
		
		PrintinOrder(root.left);
		if(root.freq!=0){
			//System.out.print(root.character + " -> ");
			//System.out.println(root.freq);
			System.out.println(root.freq);
		}
		PrintinOrder(root.right);
	}
	
	public static void main(String[] args) {
		PriorityQueue<NodeHuffman> pq = new PriorityQueue<NodeHuffman>(7);
		String s = "olaaa";
		int i = 0;
		while (i < s.length()) {
			try {
				CountBytes.freqTable(s.charAt(i++));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int[] freqT = CountBytes.getArray();

		for (i = 0; i < freqT.length; ++i) {
			if (freqT[i] != 0) {
				NodeHuffman nd = new NodeHuffman();
				nd.character = (char) i;
				nd.freq = freqT[i];
				pq.insert(nd);
				++i;
			}
		}

		NodeHuffman node = huffman(pq);
		//PrintinOrder(node);
		//System.out.println(checkSiblingProp(node));
		
		CodHuffman[] cd = getListOfLeafs(node);
		for (int j = 0; j < cd.length; ++j) {
		if(cd[j]!=null)
			System.out.println((char)j + " have:" + cd[j].bits + "bits and code:" + cd[j].code);
	}
		System.out.println("bits: "+ getCode(node, 'a').bits);
		System.out.println("codigo: "+getCode(node, 'a').code);
		
		CodHuffman cod = new CodHuffman();
		cod.bits = 1;
		cod.code = 1;
		System.out.println(getSimbol (node, cod));

		//NodeHuffman n = getListOfLeafs(node);

		


//

		/*
		 * while(n.right!=null){ System.out.println(n.character +" , "+ n.freq +
		 * " " + " , " + n.cod.bits + " , " + n.cod.code); n = n.right; }
		 */
	}
}
