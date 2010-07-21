import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class HuffmanCompress {
	private BitSink bitSnk;
	private BitSource bitSrc;
	private PriorityQueue<NodeHuffman> pq;
	private CodHuffman[] code;
	
	public HuffmanCompress (InputStream in){
		InputStream aux = in;
		bitSrc = new BitSource (in);
		bitSrc.close();
		pq = HuffmanCode.buildPriorityQueue(bitSrc);
		
		//criaçao da árvore de codificaçao
		NodeHuffman node = HuffmanCode.huffman(pq);
		
		//guardar a arvore no ficheiro
		bitSnk = SaveHuffManCode.saveCode(node);
		
		//guardar os caracteres
		code = HuffmanCode.getListOfLeafs(node);
		
		int c;
		do{
			CodHuffman cd = null;
			c = bitSrc.read(8);
			if(c!=-1)
				cd = code[c];
				bitSnk.write(cd.bits, cd.code);
		}while(c!=-1);
		bitSrc.close();
		bitSnk.close();
	}
	
	public static void main (String[]args){
		byte[] a = new byte[10];
		String s = "ola mundo!";
		
		for(int i=0; i<s.length(); ++i){
			a[i] = (byte) s.charAt(i);
		}
		ByteArrayInputStream in = new ByteArrayInputStream(a);
		HuffmanCompress hc = new HuffmanCompress(in);
		
	}
}
