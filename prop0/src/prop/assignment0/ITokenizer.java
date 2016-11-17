/*
 Created by:
 Yahya Ajwad (yaaj2898)
 Max Jonsson (majo6981)
 */

package prop.assignment0;

import java.io.IOException;

public interface ITokenizer {

	void open(String fileName) throws IOException, TokenizerException;

	Lexeme current();

	void moveNext() throws IOException, TokenizerException;

	public void close() throws IOException;
}