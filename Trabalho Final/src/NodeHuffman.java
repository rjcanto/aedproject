
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
		NodeHuffman nd1 = new NodeHuffman();
		nd1.character = 'b';
		nd1.freq = 5;
		nd1.isLeaf = false;
		pq.insert(nd1);
		NodeHuffman nd2 = new NodeHuffman();
		nd2.character = 'c';
		nd2.freq = 1;
		nd2.isLeaf = false;
		pq.insert(nd2);
		NodeHuffman nd3 = new NodeHuffman();
		nd3.character = 'd';
		nd3.freq = 9;
		nd3.isLeaf = false;
		pq.insert(nd3);
		
		System.out.println(pq.element().character);
		System.out.println(pq.extractMin().character);

	}
}