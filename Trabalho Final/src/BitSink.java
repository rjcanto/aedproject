import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Construa e teste as classes BitSource e BitSink, que serão utilizadas para
 * realizar, respectivamente, leituras e escritas bit a bit em streams. A classe
 * BitSink deverá ter, no mínimo: construtor a partir de OutputStream; o método
 * close; e o método write com dois argumentos inteiros, o número de bits a
 * escrever (entre 1 e 31) e o seu valor.
 **/
public class BitSink {
	private OutputStream out;
	private int valueI = 0;

	public BitSink(OutputStream out) {
		this.out = out;
	}

	private int constructVal(int cicle) {
		int val = 0;
		int countBits = 0;
		int bit = 0;
		while (countBits < cicle) {
			bit = valueI & 0x1;
			valueI >>= 1;
			val = val | bit << countBits++;
		}
		return val;
	}

	public void write(int nBits, int value) {
		int val = 0;
		valueI = value;
		int dec = nBits / 8;
		int rest = nBits % 8;

		try {
			while (dec > 0) {
				val = constructVal(8);
				--dec;
				out.write(val);
			}
			if(rest!=0){
				val = constructVal(rest);
				out.write(val);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			//out.write(0x100); //final de ficheiro
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
		BitSink bitSnk = new BitSink(BAOS);
		bitSnk.write(3, 0x4);
		bitSnk.close();
		byte[] byteArray = BAOS.toByteArray();
		
		for(int i=0; i<byteArray.length; ++i){
			System.out.println(byteArray[i]);
		}
	}
}
