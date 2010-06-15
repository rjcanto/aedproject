import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitSource {
	private InputStream in;
	private int read;
	private int countBits = -1;
	private int ret=0;
	private int bit =0;
	private int shift = 0;
	/**
	 * Construa e teste as classes BitSource e BitSink, que serão utilizadas
	 * para realizar, respectivamente, leituras e escritas bit a bit em streams.
	 * A classe BitSource deverá ter, no mínimo: construtor com um argumento do
	 * tipo InputStream, de onde serão lidos os dados, byte a byte, à medida que
	 * for sendo necessário; o método close, para fechar o stream subjacente; e
	 * o método read com um argumento inteiro para indicar o número de bits a
	 * ler (entre 1 e 31) e retorno inteiro com os dados lidos ou -1, se o
	 * stream subjacente estiver esgotado.
	 **/

	public BitSource(InputStream in) {
		this.in = in;
		read = 0;
		countBits = 0;
	}

	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void readB (int readBits){
		int ret=0;
		int bit =0;
		int shift = 0;
		while(countBits<=8 && readBits>=0){
			bit = read | 0x1;
			read >>=1;
			ret = ret | (bit<<shift++);
			
			++countBits;
			--readBits;
		}
	}
	
	public int read(int readBits) {

		try {
			if(countBits == -1 || countBits >8)
				read = in.read();
			
			readB(readBits);

			if(countBits>8 && readBits>=0)
				read = in.read();
				
			readB(readBits);
			
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
			BitSource bitSrc = new BitSource(fileStream);
			System.out.println(bitSrc.read(1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}