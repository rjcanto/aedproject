import java.io.ByteArrayOutputStream;

public class SaveHuffManCode {
	public static ByteArrayOutputStream saveCode (NodeHuffman node){
		ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
		BitSink bitSnk = new BitSink(BAOS);
		saveNodes(node, bitSnk);
		bitSnk.close();
		return BAOS;
	}
	
	public static void saveNodes(NodeHuffman node, BitSink bS){
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
		constrTree(bitS, nd);
		return nd;
	}

	private static NodeHuffman constrTree(BitSource bitS, NodeHuffman nd) {
		int c = bitS.read(1);
		if(c==-1)
			return nd;
		if(c == 0){
			nd.left = new NodeHuffman();
			nd = constrTree(bitS, nd.left);
		}
		if(c == 1){
			nd.character = (char) bitS.read(8);
			nd.isLeaf = true;
			return nd;
		}
		nd.right = new NodeHuffman();
		return nd = constrTree(bitS, nd.right);		
	}
}
