package Codigo;

import java.io.ByteArrayInputStream;

public class MAIN {
	public static void PrintinOrder(NodeHuffman root){
		if(root==null)
			return;
		
		PrintinOrder(root.left);
		if(root.character!=0){
			System.out.println(root.character);
		}
		PrintinOrder(root.right);
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
