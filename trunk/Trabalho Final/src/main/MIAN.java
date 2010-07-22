package main;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class MIAN {
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
	
	public static void main(String[] args) {
		PriorityQueue<NodeHuffman> pq = new PriorityQueue<NodeHuffman>(9);
		String s = "ola mundo!";
		int i = 0;
		while (i < s.length()) {
			try {
				CountBytes.freqTable(s.charAt(i++));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		int[] freqT = CountBytes.getArray();

		for (i = 0; i < freqT.length; ++i) {
			if (freqT[i] != 0) {
				NodeHuffman nd = new NodeHuffman();
				nd.character = (char) i;
				nd.freq = freqT[i];
				nd.isLeaf = true;
				pq.insert(nd);
			}
		}

		NodeHuffman node = HuffmanCode.huffman(pq);
		
		PrintinOrder(node);
		//System.out.println(checkSiblingProp(node));
		
		//CodHuffman[] cd = HuffmanCode.getListOfLeafs(node);
		//for (int j = 0; j < cd.length; ++j) {
		//if(cd[j]!=null)
		//	System.out.println((char)j + " have:" + cd[j].bits + "bits and code:" + cd[j].code);
		//}
		
		//System.out.println("bits: "+ HuffmanCode.getCode(node, 'a').bits);
		//System.out.println("codigo: "+HuffmanCode.getCode(node, 'a').code);
		
		//CodHuffman cod = new CodHuffman();
		//cod.bits = 1;
		//cod.code = 1;
		//System.out.println(HuffmanCode.getSimbol (node, cod));
		
		System.out.println("________________________________________________");
		ByteArrayOutputStream baos = SaveHuffManCode.saveCode(node);
		byte[] byteArray = baos.toByteArray();
		
		for(int j=0; j<byteArray.length; ++j){
			System.out.println(String.format("0x%x", byteArray[j]));
		}
		
		System.out.println("________________________________________________");
		
		ByteArrayInputStream bais = new ByteArrayInputStream(byteArray);
		BitSource bS = new BitSource(bais);
		//NodeCode nd = SaveHuffManCode.constructTreeHuffman(bS);
		NodeHuffman nd = SaveHuffManCode.constructTreeHuffman(bS);
		PrintinOrder(nd);
		
		
	}

}
