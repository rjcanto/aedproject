import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;


public class TESSTES {
	int countBits = -1;
	int readB = 0; //guarda o readBits para pdoer ser actualizado
	private InputStream in;
	private int read;
	private int shift = 0;
	
	public TESSTES (InputStream in){
		this.in = in;
	}
	
	private int readB (int ret){
		int bit = 0;
		while(countBits<8 && readB>0){
			bit = read & 0x1;
			read >>=1;
			ret = ret | (bit<<shift++);
			
			++countBits;
			--readB;
		}
		return ret;
	}
	
	public int read(int readBits) {
		readB = readBits;
		int ret=0;
		ret = 0;
		try {
			if(countBits == -1 || countBits >=8){
				read = in.read();
				countBits = 0;
			}
			ret = readB(ret);

			while(readB>0){
				if(countBits>=8 && readB>0){
					read = in.read();
					countBits = 0;
				}
				ret = readB(ret);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		shift = 0;
		return ret;
	}
	
	public static void main(String[] args) {
		File file = new File("C:\\TESTES\\teste1.txt");
		try {
			FileInputStream fileStream = new FileInputStream(file);
			TESSTES bitSrc = new TESSTES(fileStream);
			System.out.println(bitSrc.read(15));
			System.out.println(bitSrc.read(2));
			System.out.println(bitSrc.read(6));
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
