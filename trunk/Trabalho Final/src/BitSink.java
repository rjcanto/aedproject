import java.io.IOException;
import java.io.OutputStream;

public class BitSink {
	private OutputStream out;
	
	
	/**
	 * Construa e teste as classes BitSource e BitSink, que serão utilizadas
	 * para realizar, respectivamente, leituras e escritas bit a bit em streams.
	 * A classe BitSink deverá ter, no mínimo: construtor a partir de
	 * OutputStream; o método close; e o método write com dois argumentos
	 * inteiros, o número de bits a escrever (entre 1 e 31) e o seu valor.
	 **/
	
	public BitSink(OutputStream out){
		this.out = out;
	}
	
	public void write(int nBits, int value){
		
	}
	
	public void close(){
		try {
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
