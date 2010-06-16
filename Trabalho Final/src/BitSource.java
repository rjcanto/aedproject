import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class BitSource {
	private int countBits = -1;
	private int readB = 0; //guarda o readBits para pdoer ser actualizado
	private InputStream in;
	private int read;
	private int shift = 0;
	/**
	 * Construa e teste as classes BitSource e BitSink, que ser�o utilizadas
	 * para realizar, respectivamente, leituras e escritas bit a bit em streams.
	 * A classe BitSource dever� ter, no m�nimo: construtor com um argumento do
	 * tipo InputStream, de onde ser�o lidos os dados, byte a byte, � medida que
	 * for sendo necess�rio; o m�todo close, para fechar o stream subjacente; e
	 * o m�todo read com um argumento inteiro para indicar o n�mero de bits a
	 * ler (entre 1 e 31) e retorno inteiro com os dados lidos ou -1, se o
	 * stream subjacente estiver esgotado.
	 **/

	public BitSource(InputStream in) {
		this.in = in;
		read = 0;
	}

	public void close() {
		try {
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			BitSource bitSrc = new BitSource(fileStream);
			System.out.println(bitSrc.read(15));
			System.out.println(bitSrc.read(2));
			System.out.println(bitSrc.read(6));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}