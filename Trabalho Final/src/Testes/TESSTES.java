package Testes;

import java.io.IOException;

public class TESSTES {
	/*public static CodHuffman inOrder(NodeHuffman n, char s, int bits, int cod){
		CodHuffman cH = new CodHuffman ();
		if(n==null){
			cH.bits = -1;
			cH.code = -1;
			return cH;		
		}
		
		inOrder(n.left, s, bits+1, cod=(cod&0)<<1);
		
		if(n.left==null && n.right == null && n.character == s){
			cH.bits = bits;
			cH.code = cod;
			return cH;
		}
		
		inOrder(n.right, s, bits+1, cod=(cod&1)<<1);
		
		return cH;
	}*/
	
	public static CodHuffman inOrder(NodeHuffman n, char s, int bits, int cod, CodHuffman ret){
		int bitsLeft = bits ; 
		int bitsRight = bits ;
		int codLeft = cod;
		int codRight = cod;
		
		if(n==null)
			return ret;
		codLeft = (codLeft|0)<<1;
		ret = inOrder(n.left, s, ++bitsLeft, codLeft, ret);
		codRight = (codRight|1)<<1;
		ret = inOrder(n.right, s, ++bitsRight, codRight, ret);
		
		if(n.left==null && n.right == null && n.character == s){
			cod = cod>>1;
			return new CodHuffman (cod, bits);
		}
		return ret;
	}
	
	public static void main (String [] args){
		PriorityQueue<NodeHuffman> pq = new PriorityQueue<NodeHuffman>(7);
		String s = "olaaa";
		int i=0;
		while(i<s.length()){
			try {
				CountBytes.freqTable(s.charAt(i++));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		i=0;
		while(i<s.length()){
			NodeHuffman nd = new NodeHuffman();
			nd.character = s.charAt(i);
			nd.freq = CountBytes.getfreq(s.charAt(i));
			pq.insert(nd);
			++i;
		}
		
		NodeHuffman node = HuffmanCode.huffman(pq);
		//inOrder(node);
		//System.out.println(checkSiblingProp (node));
		
		CodHuffman cH = new CodHuffman(0, -1);
		cH = inOrder(node, 'o', 0, 0, cH);
		
		System.out.println(cH.bits);
		System.out.println(cH.code);
	}
	
}
