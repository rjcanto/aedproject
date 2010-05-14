package testes;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import Grupo2_Ex4.txtFilter;

public class Exercicio41 {
	public static Iterable<File> getAllFiles(final File baseDir,
			final FileFilter filter) {
		
		File[] files = baseDir.listFiles();
		//caso o array esteja vazio
		if(files.length==0){
			return new Iterable<File>(){
				public Iterator<File> iterator() {
					return new Iterator<File>(){

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
			};
		}

		ArrayList<File> A_files = new ArrayList<File>();
		for(File f : files)
			A_files.add(f);
		
		Iterator<File> it = A_files.iterator();
		
		/*
		 * return new Iterable<File>() {
		 * 	 retorna iterador que percorre os vários iteradores para 
		 *   a directoria corrente
		 * }
		 */
		
		while(it.hasNext()){
			final File f = it.next();
			if(f.isDirectory())
				getAllFiles(f, filter);
			else{
				if(filter.accept(f))
					return new Iterable<File>(){
						public Iterator<File> iterator() {
							return new Iterator<File>(){
								boolean ret = false;
								@Override
								public boolean hasNext() {
									if(!ret)
										return true;
									return false;
								}

								@Override
								public File next() {
									if(!ret){
										ret = true;
										return f;
									}
									throw new NoSuchElementException();
								}

								@Override
								public void remove() {
									throw new UnsupportedOperationException();
								}
							};
						}
				};
			}
		}
		return null;
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
