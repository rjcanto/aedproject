package Grupo2_Ex4;

import java.io.File;
import java.io.FileFilter;

public class txtFilter implements FileFilter{

	@Override
	public boolean accept(File file) {
		String name = file.getName();
		String ext=null;
		int i=name.length();
		
		//ler o nome do ficheiro da direita para a esquerda ate encontrar ponto.
		//assim tenho a extensao do ficheiro
		//depois comparar com o a minha extensao (.txt)
		
		//while(name.charAt(i--)!='.');
		
		//while(i<name.length())
				//ext = ext+(name.charAt(i++)+"");
		
		//if(ext.equals(".txt"))
		if(name.endsWith(".txt"))
			return true;
		return false;

	}

}



