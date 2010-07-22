package main;

import java.io.IOException;
import java.io.InputStream;

public class BitSource {
    private InputStream input;
    private byte remainder = 0;
    private int bitsRemaining = 0;

    public BitSource(InputStream in) { input = in; if (input.markSupported()) input.mark(Integer.MAX_VALUE);}

    public void close() {
        try {
            input.close();
        } catch (IOException e) {
            // TODO: Treat input.close exception
        }
    }

    public int read(int bits) {
        if (bits < 1 || bits > 31) throw new IllegalArgumentException();
        int valueRead = 0;
        int bitsRead = 0;
        while (bits > 0) {
            while (bitsRemaining > 0 && bits > 0) {
                valueRead = (valueRead << 1) | ((remainder >>> 7) & 0x01);
                bitsRemaining--;
                remainder <<= 1;
                bits--;
                bitsRead++;
            }
            if (bits > 0) {
                int read = -1;
                try {
                    read = input.read();
                } catch (IOException e) {
                    System.err.print("FODEU");
                }
                if (read == -1)
                    if (bitsRead > 0) return valueRead << (8 - bitsRead);
                    else return -1;
                int shifts = bits <= 8 ? bits : 8;
                int mask = (int)Math.pow(2, shifts) - 1;
                valueRead = (valueRead << shifts) | ((read >> (8 - shifts)) & mask);
                remainder = (byte)(read << shifts);
                bitsRemaining = 8 - shifts;
                bits -= shifts;
            }
        }
        return valueRead;
    }

    public void reset() {
        try {
            if (input.markSupported()) input.reset();
        } catch (IOException e) {
            // TODO: Treat ioexception
        }
    }
}