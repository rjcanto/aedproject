package testes;

import main.NodeHuffman;
import main.PriorityQueue;

public class TestNodeHuffman {

	public static void main (String[]args){
		PriorityQueue<NodeHuffman> pq = new PriorityQueue<NodeHuffman>(5);
		NodeHuffman nd = new NodeHuffman();
		nd.setCharacter('a') ;
		nd.setFrequency(4) ;
		pq.insert(nd);
		NodeHuffman nd1 = new NodeHuffman();
		nd1.setCharacter('b');
		nd1.setFrequency(5) ;
		pq.insert(nd1);
		NodeHuffman nd2 = new NodeHuffman();
		nd2.setCharacter('c') ;
		nd2.setFrequency(10) ;
		pq.insert(nd2);
		NodeHuffman nd3 = new NodeHuffman();
		nd3.setCharacter('d') ;
		nd3.setFrequency(9) ;
		pq.insert(nd3);
		
		System.out.println(pq.element().getCharacter());
		System.out.println(pq.extractMin().getCharacter());

	}
}
