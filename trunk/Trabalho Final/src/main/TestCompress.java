package main;

import java.io.ByteArrayInputStream;

public class TestCompress {
	public static void PrintinOrder(NodeHuffman root){
		if(root==null)
			return;
		
		PrintinOrder(root.left);
		if(root.character!=0){
			//System.out.print(root.character + " -> ");
			//System.out.println(root.freq);
			System.out.println(root.character);
		}
		PrintinOrder(root.right);
	}
	
	public static void main (String[]args){
		byte[] a = new byte[10];
		String s = "ola mundo!";
		
		for(int i=0; i<s.length(); ++i){
			a[i] = (byte) s.charAt(i);
		}
		ByteArrayInputStream in = new ByteArrayInputStream(a);
		HuffmanCompress hc = new HuffmanCompress(in);
		
		a = hc.getCompressFile().toByteArray();
		in = new ByteArrayInputStream(a);
		HuffmanUncompress hu = new HuffmanUncompress(in);
		
		a = hu.getUncompressFile().toByteArray();
		for(int i=0; i<a.length; ++i){
			System.out.print((char)a[i] + " ");
		}
		
	}
}
