package prop.assignment0;

import java.io.IOException;

public class Parser implements IParser {
	Tokenizer t = null;

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		t = new Tokenizer();
		t.open(fileName);
		t.moveNext();
	}

	@Override
	public INode parse() throws IOException, TokenizerException, ParserException {
		if (t == null)
			throw new IOException("No open file.");

		return new AssignmentNode(t);

	}

	@Override
	public void close() throws IOException {
		if (t != null)
			t.close();
	}

}
