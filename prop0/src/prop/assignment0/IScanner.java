/*
 Created by:
 Yahya Ajwad (yaaj2898)
 Max Jonsson (majo6981)
 */

package prop.assignment0;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IScanner {

	void open(String fileName) throws FileNotFoundException;

	char current();

	void moveNext() throws IOException;

	public void close() throws IOException;
}