import java.io.IOException;
import java.nio.charset.CoderMalfunctionError;
import java.util.ArrayList;

public class HuffmanCode {
	public static NodeHuffman huffman(PriorityQueue<NodeHuffman> C) {
		//int n = C.size();
		PriorityQueue<NodeHuffman> Q = C;

		for (int i = 0; i < /*n-1*/C.size(); ++i) {
			NodeHuffman z = new NodeHuffman();
			NodeHuffman nl = new NodeHuffman();
			NodeHuffman nr = new NodeHuffman();
			nl = Q.extractMin();
			nr = Q.extractMin();
			z.left = nl;
			z.right = nr;
			z.freq = nl.freq + nr.freq;
			Q.insert(z);
		}
		return Q.extractMin();
	}

	public static boolean checkSiblingProp(NodeHuffman nh) {
		NodeHuffman previous = null;
		ArrayList<NodeHuffman> binList = new ArrayList<NodeHuffman>();
		binList.add(nh);
		while (binList.size() > 0) {
			NodeHuffman node = binList.remove(0);
			if (node.left != null) {
				if (node.left.freq >= node.right.freq) {
					binList.add(node.left);
					binList.add(node.right);
				} else {
					binList.add(node.right);
					binList.add(node.left);
				}
			}
			if (previous != null)
				if (previous.freq < node.freq)
					return false;

			previous = node;
		}
		return true;
	}

	public static <E> void inOrder(NodeHuffman root) {
		if (root == null)
			return;

		inOrder(root.left);
		if (root.freq != null)
			System.out.println(root.freq);
		inOrder(root.right);
	}
	
	public static CodHuffman getCode (NodeHuffman n, char s){
		/*percorrer a arvore ate encontrar o valor pretendido e por cada
		 * iteraçao guardar o count de bits e colocar o bit no cod*/
		CodHuffman code = new CodHuffman();
		if(!searchTree(n, s, code.bits, code.code))
			return null;
		return code;
	}

	private static boolean searchTree(NodeHuffman n, char s, int bits, int code) {
		if(n==null)
			return false;
		if(n.character == s)
			return true;
		if(n.character < s){
			++bits;
			code = (code& 0 ) << 1;
			return searchTree(n.left, s, bits, code);
		}
		else{
			++bits;
			code = (code&1) << 1;
			return searchTree(n.left, s, bits, code);
		}
	}

	public static void main(String[] args) {
		PriorityQueue<NodeHuffman> pq = new PriorityQueue<NodeHuffman>(7);
		String s = "olaaa";
		int i=0;
		while(i<s.length()){
			try {
				CountBytes.freqTable(s.charAt(i++));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		i=0;
		while(i<s.length()){
			NodeHuffman nd = new NodeHuffman();
			nd.character = s.charAt(i);
			nd.freq = CountBytes.getfreq(s.charAt(i));
			pq.insert(nd);
			++i;
		}
		
		NodeHuffman node = huffman(pq);
		inOrder(node);
		System.out.println(checkSiblingProp (node));
		
	}
}
