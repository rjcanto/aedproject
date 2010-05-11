package Grupo2_Ex4;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;

public class Exercicio4 {
	private static void addArray(ArrayList<File> ret, File[] aux) {
		for(int i=0; i<aux.length; ++i)
			ret.add(aux[i]);
	}
	
	public static Iterable<File> getAllFiles(final File baseDir, FileFilter filter){
		return new Iterable<File>(){

			public Iterator<File> iterator() {
				return new Iterator<File>(){
					File[] files = baseDir.listFiles();
					File[] arrayFiles;
					

					public boolean hasNext() {
						return false;
					}

					public File next() {
						return null;
					}

					public void remove() {
						throw new UnsupportedOperationException();
					}
					
				};
			}};
		
		
	}
	
public static File[] listAllFiles (File baseDir, FileFilter filter){
	ArrayList <File> ret = new ArrayList <File> ();
	File[] files;
	File[] retArray;
	//usar iterador para percorrer a directoria e subdirectoria
	//por cada directoria verificar se o file pertence ao filter
	//se pertencer adiciona ao array
	
	//lista de todos os ficheiros da directoria
	files = baseDir.listFiles();
	
	for(int i=0; i<files.length; ++i){
		if(files[i].isDirectory()){
			retArray= listAllFiles(files[i], filter);
			//adicionar a lista
			if(retArray != null)
				addArray (ret, retArray);
		}
			if(filter.accept(files[i]))
				ret.add(files[i]);
	}
	
	if(ret.size()!=0){
		retArray = new File[ret.size()];
		return  ret.toArray(retArray);
	}
	return null;
}
}
