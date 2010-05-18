package Grupo2_Ex4;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Exercicio4 {
	public static Iterable<File> getAllFiles(final File baseDir,final FileFilter filter) {
		
		final File[] files = baseDir.listFiles();
		//caso o array esteja vazio

		if(!baseDir.isDirectory()){
			return new Iterable<File> (){
				public Iterator<File> iterator() {
					return new Iterator<File>(){
						boolean flag = false;
						public boolean hasNext() {
							if(flag)
								return false;
							return filter.accept(baseDir);
						}

						public File next() {
							if(!flag){
								flag = true;
								return baseDir;
							}
							throw new NoSuchElementException();
						}
					
						public void remove() {
							throw new UnsupportedOperationException();
						}				
					};
				}
			};
		}
	
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
		
		/*
		 * return new Iterable<File>() {
		 * 	 retorna iterador que percorre os vários iteradores para 
		 *   a directoria corrente
		 * }
		 */
			return new Iterable<File>(){
				public Iterator<File> iterator() {
					return new Iterator<File>() {
						int i=0;
						Iterator<File> curr = getAllFiles(files[i], filter).iterator();
						File elem;
	 
						public void remove() {
							throw new UnsupportedOperationException();
						}
						
						public File next() {
							if(hasNext()){
								File aux = elem;
								elem = null;
								return aux;
							}
							throw new NoSuchElementException();
						}
						
						public boolean hasNext() {
							/*
							 * caso E curr tiver diferent de null retorna true
							 * iterador corrente ainda tem elementos? returna true
							 * senao obtem iterator i+1 e retorna true
							 * caso contrario retorna false
							 */
							if (elem!= null)	return true;
							if (curr.hasNext())	{
								elem = curr.next();
								return true;
							}
							while(!curr.hasNext() && i< files.length){
								curr = getAllFiles(files[i++], filter).iterator();
								return true;
							}
							return false;
						}
					};
				}
			};
//		}
	}
	
	public static void main(String[] args) {
		File directoria = new File("C:\\TESTES");
		Iterable<File> ret;
		
		txtFilter filter = new txtFilter();
		
		ret = getAllFiles(directoria, filter);
		
		Iterator<File> it = ret.iterator();
		
		if(ret != null)
			while(it.hasNext()) {
				File f = it.next();
				if(f != null)
					System.out.println(f.toString());
			}
		else
			System.out.println("Nao foram encontrados ficheiros.");
	}
}