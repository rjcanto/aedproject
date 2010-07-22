package testes;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import main.BitSink;
import main.BitSource;

public class TestBitStream {
	static byte[] byteArray ;
	static int nBits = 6 ;
	static int value = 0x12 ;
	
	private static void bitSink() {
		ByteArrayOutputStream bitStreamOut = new ByteArrayOutputStream();
		BitSink bitSnk = new BitSink(bitStreamOut);
		bitSnk.write(nBits, value);
	    bitSnk.close();
	    byteArray = bitStreamOut.toByteArray();
	}
	
	private static void bitSource() {
		ByteArrayInputStream bitStreamIn = new ByteArrayInputStream(byteArray) ;
		BitSource bitSrc = new BitSource(bitStreamIn) ;
		System.out.println(bitSrc.read(6)) ;
	}
	
	public static void readHelloWorld() {
		byte[] a = new byte[10];
		String s = "ola mundo!";
		
		for(int i=0; i<s.length(); ++i){
			a[i] = (byte) s.charAt(i);
		}
		
		ByteArrayInputStream bitStream = new ByteArrayInputStream(a);
		BitSource bitSrc = new BitSource(bitStream);
		
		for(int i=0; i<=s.length(); ++i)
			System.out.print((char)bitSrc.read(8));
		System.out.println();
	}
	
	public static void main(String[] args) {
		bitSink();
		bitSource() ;
		readHelloWorld();
	}
	
}
