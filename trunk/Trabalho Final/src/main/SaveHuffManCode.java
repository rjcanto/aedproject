package main;
import java.io.ByteArrayOutputStream;

public class SaveHuffManCode {
	public static ByteArrayOutputStream saveCode (NodeHuffman node){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		BitSink bitSnk = new BitSink(baos);
		saveNodes(node, bitSnk);
		return baos;
	}
	
	private static void saveNodes(NodeHuffman node, BitSink bS){
		if(node.isLeaf){
			bS.write(1, 1);
			bS.write(8, node.character);
		}else{
			bS.write(1, 0);
			saveNodes(node.left, bS);
			saveNodes(node.right, bS);
		}
	}
	
	public static NodeHuffman constructTreeHuffman (BitSource bitS){
		NodeHuffman nd = new NodeHuffman();
		nd = constrTree(bitS);
		return nd;
	}

//	private static NodeHuffman constrTree(BitSource bitS, NodeHuffman nd) {
//		int c = bitS.read(1);
//		if(c==-1)
//			return nd;
//		if(c == 0){
//			nd.left = new NodeHuffman();
//			nd = constrTree(bitS, nd.left);
//		}
//		if(c == 1){
//			char ch = (char)bitS.read(8);
//			nd.character = ch ;
//			nd.isLeaf = true;
//			return nd;
//		}
//		nd.right = new NodeHuffman();
//		return nd = constrTree(bitS, nd.right);		
//	}
	
	private static NodeHuffman constrTree(BitSource bitS) {
		int c = bitS.read(1);
		NodeHuffman nd = new NodeHuffman();
		if(c==-1)
			return nd;
		if(c == 0){
			nd.left = constrTree(bitS);
			nd.right = constrTree(bitS);
		}
		else if(c == 1){
			char ch = (char)bitS.read(8);
			nd.character = ch ;
			nd.isLeaf = true;	
		}
		return nd;
	}
}