package main;

import java.io.IOException;
import java.io.OutputStream;

public class BitSink {
    private OutputStream output;
    private byte remainder;
    private int bitCount = 0;

    public BitSink(OutputStream out) { output = out; }

    public void close() {
        try {
            byte maskVal = (byte)((int)(Math.pow(2, bitCount) - 1) << (8 - bitCount));
            if (bitCount > 0) output.write(remainder & maskVal);
            output.close();
        } catch (IOException e) {
            // TODO: Treat output.close exception
        }
    }

    public void write(int bits, int value) {
        if (bits < 1 || bits > 31) throw new IllegalArgumentException();
        while (bits > 0) {
            int bitsToAdd = 8 - bitCount < bits ? 8 - bitCount : bits;
            int remainderMask = (int)Math.pow(2, bitCount) - 1 << (8 - bitCount);
            remainder &= remainderMask;
            remainderMask = ~remainderMask & 0xFF;
            remainder |= ((value >>> (bits - bitsToAdd)) << 8 - bitCount - bitsToAdd) & remainderMask;
            bitCount += bitsToAdd;
            try {
                if (bitCount == 8) {
                    output.write(remainder);
                    bitCount = 0;
                }
            } catch (IOException e) {
                // TODO: Treat output.write exception
            }
            bits -= bitsToAdd;
        }
    }
}