import java.io.*;

public class CountBytes {

	static int[] freqTbl = new int[256];

	public static int[] freqTable(char c) throws IOException {
		char value = 0;
		if (c < 0 && c > -129) {
			value = (char) (c * (-1) + 127);
			++freqTbl[value];
		} else
			++freqTbl[c];
		return freqTbl;
	}
	
	public static int[] getArray(){
		return freqTbl;
	}
	
	public static int getfreq(char c){
		char value = 0;
		if (c < 0 && c > -129) {
			value = (char) (c * (-1) + 127);
			return freqTbl[value];
		} else
			return freqTbl[c];
	}

//	public static void main(String[] args) throws IOException {
//		// byte [] streamBuf = {-128,-127,-120,-120, 6,2,1,1,1,2,4,6,6,5};
//
//		File file = new File("C:\\TESTES\\teste1.txt");
//		try {
//			FileInputStream fileStream = new FileInputStream(file);
//			BitSource bitSrc = new BitSource(fileStream);
//
//			int n = 0;
//			while ((n = bitSrc.read(8)) != -1) {
//				char c = (char) n;
//				System.out.println(c);
//				freqTable(c);
//			}
//
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//		for (int i = 0; i < freqTbl.length; ++i)
//			if (freqTbl[i] != 0)
//				System.out.println(freqTbl[i] + "-->" + i);
//	}
}
