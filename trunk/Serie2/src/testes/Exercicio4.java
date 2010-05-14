package testes;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;
import Grupo2_Ex4.txtFilter;

public class Exercicio4 {
	private static boolean NoDirectory (File[] fl){
		
		for(File f : fl){
			if(f.isDirectory())
				return false;
		}
		return true;
	}
	
	public static Iterable<File> getAllFiles(final File baseDir,
			final FileFilter filter) {
		return new Iterable<File>() {
			public Iterator<File> iterator() {
				File[] files = baseDir.listFiles();
				final ArrayList<File> file = new ArrayList<File>();
				for(int i=0; i<files.length; ++i)
					file.add(files[i]);

				if (files.length == 0) {
					return new Iterator<File>() {
						public boolean hasNext() {
							return false;
						}

						public File next() {
							throw new NoSuchElementException();
						}

						public void remove() {
							throw new UnsupportedOperationException();
						}
					};
				}
				else if(!NoDirectory(files)){
					return new Iterator<File>(){
						Iterator<File> it = file.iterator();					
						File curr;
						
						public boolean hasNext() {
							if(curr!=null)
								return true;
							while(it.hasNext()){
								curr = it.next();
								if(curr.isDirectory())
									getAllFiles(curr, filter);
								else if(filter.accept(curr)){
									return true;
								}
							}
							return false;
						}

						public File next() {
							if(hasNext()){
								File aux = curr;
								curr = null;
								return aux;
							}
							throw new NoSuchElementException();
						}

						public void remove() {
							throw new UnsupportedOperationException();
						}
					};
				}
				else {
					return new Iterator<File>(){
						Iterator<File> it = file.iterator();
						File curr;
						public boolean hasNext() {
							if(curr!=null)
								return true;
							while(it.hasNext()){
								curr = it.next();
								if(filter.accept(curr))
									return true;
							}
							return false;
						}

						public File next() {
							if(hasNext()){
								File aux = curr;
								curr = null;
								return aux;
							}
							throw new NoSuchElementException();
						}

						public void remove() {
							throw new UnsupportedOperationException();
						}
					};
				}
			}
		};
	}

	public static void main(String[] args) {
		File directoria = new File("C:\\TESTES");
		Iterable<File> ret;
		
		txtFilter filter = new txtFilter();
		
		ret = getAllFiles(directoria, filter);
		
		Iterator<File> it = ret.iterator();
		
		if(ret != null)
			while(it.hasNext()) 
				System.out.println(it.next().toString());
		else
			System.out.println("Nao foram encontrados ficheiros.");
	}
}
