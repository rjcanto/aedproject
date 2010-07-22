package main;

public class NodeHuffman implements Comparable <NodeHuffman>{
	Integer freq;
	char character;
	NodeHuffman left;
	NodeHuffman right;
	boolean isLeaf;
	CodHuffman cod;

	public int compareTo(NodeHuffman n) {
		return freq - n.freq;
	}
	
	/* Getters*/
	public Integer getFrequency() {
		return freq ;
	}
	
	public char getCharacter() {
		return character ;
	}
	
	public NodeHuffman getLeft() {
		return left ;
	}
	
	public NodeHuffman getRight() {
		return right ;
	}
	
	public CodHuffman getCode() {
		return cod ;
	}
	
	/* Setters */
	public void setFrequency(Integer f) {
		this.freq = f ;
	}
	
	public void setCharacter(char c) {
		this.character = c ;
	}
	
	public void setLeft(NodeHuffman l) {
		this.left = l;
	}
	
	public void setRight(NodeHuffman r) {
		this.right = r;
	}
	
	public void setCode(CodHuffman c) {
		this.cod = c ;
	}
	
	public boolean isLeaf() {
		return (character != 0) ;
	}
}