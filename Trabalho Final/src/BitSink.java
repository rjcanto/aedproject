import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

public class BitSink {
	private OutputStream out;
	private int valueI;
	private int res;
	private int resBits;
	private int nBit;

	public BitSink(OutputStream out) {
		this.out = out;
		valueI = 0;
		res =0;
		resBits = 0;
		nBit = 0;
	}

	private int constructVal(int cicle) {
		int val = res;
		int countBits = resBits;
		int bit = 0;
		while (countBits < cicle) {
			bit = valueI & 0x1;
			valueI >>= 1;
			val = val | bit << countBits++;
		}
		resBits = countBits;
		return val;
	}

	public void write(int nBits, int value) {
		int val = 0;
		valueI = value;
		nBit = nBits + resBits;
		int dec = nBit / 8;
		int rest = nBit % 8;

		try {
			while (dec > 0) {
				val = constructVal(8);
				--dec;
				out.write(val);
				if(resBits == 8){
					resBits = 0;
					nBit = 0;
					res = 0;
				}
			}
			if(rest!=0){
				val = constructVal(rest);
				res = val;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			if(resBits!=0)
				out.write(res);
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
		BitSink bitSnk = new BitSink(BAOS);
		//bitSnk.write(9, 888);
		//bitSnk.write(8, 180);
		bitSnk.write(1, 0x3);
		bitSnk.write(2, 0x4);
		bitSnk.write(3, 0x4);
		bitSnk.write(9, 0x15);
		
		bitSnk.close();
		byte[] byteArray = BAOS.toByteArray();
		
		for(int i=0; i<byteArray.length; ++i){
			System.out.println(String.format("0x%x", byteArray[i]));
		}
	//	System.out.println(String.format("%x", 45));
	}
}
