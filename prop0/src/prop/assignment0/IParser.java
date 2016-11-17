/*
 Created by:
 Yahya Ajwad (yaaj2898)
 Max Jonsson (majo6981)
 */

package prop.assignment0;

import java.io.IOException;

public interface IParser {

	void open(String fileName) throws IOException, TokenizerException;

	INode parse() throws IOException, TokenizerException, ParserException;

	public void close() throws IOException;
}