package main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class HuffmanUncompress {
	private BitSink bitSnk;
	private BitSource bitSrc;
	private ByteArrayOutputStream baos;
	
	public HuffmanUncompress (InputStream in){
		bitSrc = new BitSource(in);
		baos = new ByteArrayOutputStream();
		bitSnk = new BitSink(baos);
		NodeHuffman nd = SaveHuffManCode.constructTreeHuffman(bitSrc);
		TestCompress.PrintinOrder(nd);
		
		int c;
		NodeHuffman ndAux = nd;
		while((c=bitSrc.read(1))!=-1){
			if(c==0){
				ndAux = ndAux.left;
			}else{
				ndAux = ndAux.right;
			}
			if(ndAux.isLeaf){
				bitSnk.write(8, ndAux.character);
				ndAux = nd;
			}
		}
	}
	public ByteArrayOutputStream getUncompressFile (){
		return baos;
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
