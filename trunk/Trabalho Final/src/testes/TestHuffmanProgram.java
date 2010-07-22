package testes;

import java.io.ByteArrayInputStream;

import main.HuffmanCompress;
import main.HuffmanUncompress;
import main.NodeHuffman;

public class TestHuffmanProgram {
	public static void PrintinOrder(NodeHuffman root){
		if(root==null)
			return;
		
		PrintinOrder(root.getLeft());
		if(root.getCharacter()!=0){
			System.out.println(root.getCharacter());
		}
		PrintinOrder(root.getRight());
	}
	
	public static void main(String[] args) {
		byte[] a = new byte[10];
		String s = "ola mundo!";
		
		for(int i=0; i<s.length(); ++i){
			a[i] = (byte) s.charAt(i);
		}
		
		/***********Compressor*************/
		ByteArrayInputStream in = new ByteArrayInputStream(a);
		HuffmanCompress hc = new HuffmanCompress(in);
		a = hc.getCompressFile().toByteArray();
		
		/***********Descompressor*************/
		in = new ByteArrayInputStream(a);
		HuffmanUncompress hu = new HuffmanUncompress(in);
		a = hu.getUncompressFile().toByteArray();

		for(int i=0; i<a.length; ++i)
			System.out.print((char)a[i]);
	}
}
