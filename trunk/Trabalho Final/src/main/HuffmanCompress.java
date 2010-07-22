package main;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HuffmanCompress {
	private BitSink bitSnk;
	private BitSource bitSrc;
	private PriorityQueue<NodeHuffman> pq;
	private CodHuffman[] code;
	private ByteArrayOutputStream baos;
	
	public HuffmanCompress (InputStream in){
		bitSrc = new BitSource (in);
		bitSrc.close();
		
		pq = HuffmanCode.buildPriorityQueue(bitSrc);
		
		//volta ao inicio do stream
		try {
			in.reset();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//cria uma nova instancia de bitSource
		bitSrc = new BitSource(in);
		
		//cria�ao da �rvore de codifica�ao
		NodeHuffman node = HuffmanCode.huffman(pq);
		
		//guardar a arvore no ficheiro
		baos = SaveHuffManCode.saveCode(node);
		
		bitSnk = new BitSink(baos);
		
		//guardar os caracteres
		code = HuffmanCode.getListOfLeafs(node);

		int c;
		while((c = bitSrc.read(8))!=-1){
			CodHuffman cd = null;
			cd = code[c];
			bitSnk.write(cd.bits, cd.code);
		}
		bitSnk.close();
	}
	
	public ByteArrayOutputStream getCompressFile (){
		return baos;
	}
}
