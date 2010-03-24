package Serie1;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;

public class Exercicio5 {
	
	private static void addArray(ArrayList<File> ret, File[] aux) {
			for(int i=0; i<aux.length; ++i)
				ret.add(aux[i]);
	}
	
	public static File[] listAllFiles (File baseDir, FileFilter filter){
		ArrayList <File> ret = new ArrayList <File> ();
		File[] files;
		File[] aux;
		//usar iterador para percorrer a directoria e subdirectoria
		//por cada directoria verificar se o file pertence ao filter
		//se pertencer adiciona ao array
		
		//lista de todos os ficheiros da directoria
		files = baseDir.listFiles();
		
		for(int i=0; i<files.length; ++i){
			if(files[i].isDirectory()){
				aux= listAllFiles(files[i], filter);
				//adicionar a lista
				if(aux != null)
					addArray (ret, aux);
			}
				if(filter.accept(files[i]))
					ret.add(files[i]);
		}
		
		if(ret.size()!=0)
			return (File[]) ret.toArray();
		return null;
	}

	public static void main(String[] args) {
		File directoria = new File("C:\\testes");
		File[] ret;
		
		txtFilter filter = new txtFilter();
		
		ret = listAllFiles(directoria, filter);
		
		for(int i=0; i<ret.length; ++i){
			System.out.println(ret[i]);
		}
	}

}
