package Testes;

import java.io.IOException;

public class TESSTES {
	public static CodHuffman inOrder(NodeHuffman n, char s, int bits, int cod){
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
	}
	
	public static void main (String [] args){
		PriorityQueue<NodeHuffman> pq = new PriorityQueue<NodeHuffman>(7);
		String s = "ola";
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
		
		CodHuffman cH = new CodHuffman();
		cH = inOrder(node, 'l', 0, 0);
		
		System.out.println(cH.bits);
		System.out.println(cH.code);
	}
	
}
