package Codigo;

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
}
