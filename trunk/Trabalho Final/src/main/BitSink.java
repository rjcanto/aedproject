package main;

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
	
	public void closeWithBit() {
		try {
			if(resBits!=0)
				out.write(res);
			out.write(1) ;
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
