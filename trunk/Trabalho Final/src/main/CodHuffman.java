package main;

public class CodHuffman {
	int code;
	int bits;
	
	public CodHuffman (int code, int bits){
		this.code = code;
		this.bits = bits;
	}
	
	public CodHuffman (){
		this.code = 0;
		this.bits = 0;
	}
	
	public int getCode() {
		return code ;
	}
	
	public int getBits() {
		return bits ;
	}
	
	public void setCode(int c) {
		code = c ;
	}
	
	public void setBits(int b) {
		bits = b;
	}
}
