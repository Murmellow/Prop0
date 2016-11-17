/*
 Created by:
 Yahya Ajwad (yaaj2898)
 Max Jonsson (majo6981)
 */

package prop.assignment0;

import java.io.IOException;

public class Parser implements IParser {
	Tokenizer tokenizer = null;

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		tokenizer = new Tokenizer();
		tokenizer.open(fileName);
		tokenizer.moveNext();
	}

	@Override
	public INode parse() throws IOException, TokenizerException, ParserException {
		if (tokenizer == null)
			throw new IOException("No open file.");
		else {
			return new AssignmentNode(tokenizer);
		}
	}

	@Override
	public void close() throws IOException {
		if (tokenizer != null)
			tokenizer.close();
	}

	public String tabing(int tabs) {
		String tab = "";
		for (int i = 0; i < tabs; i++) {
			tab += "\t";
		}
		return tab;
	}

}
