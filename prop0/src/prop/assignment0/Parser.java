package prop.assignment0;

import java.io.IOException;

//Beatrice
class Parser {
	Tokenizer t = null;

	void open(String fileName) throws IOException, TokenizerException {
		t = new Tokenizer();
		t.open(fileName);
		t.moveNext();
	}

	public INode parse() throws IOException, TokenizerException, ParserException {
		if (t == null)
			throw new IOException("No open file.");

		return new AssignNode(t);
	}

	public void close() throws IOException {
		if (t != null)
			t.close();
	}

	class AssignNode implements INode {
		ident=null;
		assign_op= "=";
		ExprNode expr = null;
		SEMICOLON = ";";
		AssignNode assign = null;

		public AssignNode(Tokenizer t) {
			expr = new ExprNode(t);
			if (t.current().token() != Token.EOF)
				assign = new AssignNode(t);
		}

		@Override
		public Object evaluate(Object[] args) throws Exception {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public void buildString(StringBuilder builder, int tabs) {
			// TODO Auto-generated method stub
			
		}
	}

	class ExprNode implements INode {
		TermNode term = null;
		ADD_OP = "+";
		ExprNode expr = null;

		public ExprNode(Tokenizer t) {
			term = new TermNode(t);
			expr = new ExprNode(t);
		}
	}

	class TermNode implements INode {
		FactorNode factor = null;
		MULT_OP = "*";
		TermNode term = null;

		public TermNode(Tokenizer t) {
			factor = new FactorNode(t);
			term = new TermNode(t);
		}
	}

	class FactorNode implements INode {
		INT_LIT = null;
		TermNode term = null;

		public FactorNode(Tokenizer t) {
			
			term = new TermNode(t);
		}
	}

	class DeterminerNode implements INode {
		Lexeme l = null;

		public DeterminerNode(Tokenizer t) {
			// get next token and check that it's a determiner
		}
	}

	class NounNode implements INode {
		Lexeme l = null;

		public NounNode(Tokenizer t) {
			// get next token and check that it's a Noun
		}
	}

	class VerbNode implements INode {
		Lexeme l = null;

		public VerbNode(Tokenizer t) {
			// get next token and check that it's a Verb
		}
	}

}
