
public class NodeHuffman implements Comparable <NodeHuffman>{
	Integer freq;
	char character;
	NodeHuffman left;
	NodeHuffman right;
	boolean isLeaf;

	public int compareTo(NodeHuffman n) {
		return freq - n.freq;
	}
	
	public static void main (String[]args){
		PriorityQueue<NodeHuffman> pq = new PriorityQueue<NodeHuffman>(5);
		NodeHuffman nd = new NodeHuffman();
		nd.character = 'a';
		nd.freq = 4;
		nd.isLeaf = false;
		pq.insert(nd);
		
		System.out.println(pq.element().character);
	}
}