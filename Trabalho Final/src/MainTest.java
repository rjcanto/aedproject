import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


public class MainTest {
	public static void main(String[] args) throws IOException {
		File file = new File("C:\\TESTES\\teste1.txt");
		try {
			FileInputStream fileStream = new FileInputStream(file);
			BitSource bitSrc = new BitSource(fileStream);

			int n = 0;
			while ((n = bitSrc.read(8)) != -1) {
				char c = (char) n;
				//System.out.println(c);
				CountBytes.freqTable(c);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
//		for (int i = 0; i < CountBytes.freqTbl.length; ++i)
//			if (CountBytes.freqTbl[i] != 0)
//				System.out.println(CountBytes.freqTbl[i] + "-->" + i);

	}

}
