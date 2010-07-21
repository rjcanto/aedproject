import java.io.InputStream;


public class HuffmanCompress {
	private BitSink bitSnk;
	private BitSource bitSrc;
	private PriorityQueue<NodeHuffman> pq;
	
	public HuffmanCompress (InputStream in){
		bitSrc = new BitSource (in);
		pq = HuffmanCode.buildPriorityQueue(bitSrc);
		NodeHuffman node = HuffmanCode.huffman(pq);
		
	}
	
}
