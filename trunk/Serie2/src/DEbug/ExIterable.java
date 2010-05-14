package DEbug;

import java.io.File;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ExIterable implements Iterable<File>{

	@Override
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

}
