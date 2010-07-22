package testes;

import java.io.ByteArrayInputStream;

import main.BitSource;
import main.CodHuffman;
import main.HuffmanCode;
import main.NodeHuffman;
import main.PriorityQueue;

public class TestHuffmanCode {

	public static void main(String[] args) {
		byte[] a = new byte[10];
		String s = "ola mundo!";
		
		for(int i=0; i<s.length(); ++i){
			a[i] = (byte) s.charAt(i);
		}
		ByteArrayInputStream in = new ByteArrayInputStream(a);
		
		BitSource bitSrc = new BitSource(in);
		PriorityQueue<NodeHuffman> pq = HuffmanCode.buildPriorityQueue(bitSrc);
		
		NodeHuffman node = HuffmanCode.huffman(pq);
		System.out.println(HuffmanCode.checkSiblingProp(node));
		HuffmanCode.PrintinOrder(node);
		
		CodHuffman[] cd = HuffmanCode.getListOfLeafs(node);
		for (int j = 0; j < cd.length; ++j) {
		if(cd[j]!=null)
			System.out.println((char)j + " have:" + cd[j].getBits() 
					+ "bits and code:" + cd[j].getCode());
		}
		
		System.out.println("bits: "+ HuffmanCode.getCode(node, 'o').getBits());
		System.out.println("codigo: "+ HuffmanCode.getCode(node, 'o').getCode());
		
		CodHuffman cod = new CodHuffman();
		cod.setBits(3) ;
		cod.setCode(0x1) ;
		System.out.println(HuffmanCode.getSimbol(node, cod));
	}
}
