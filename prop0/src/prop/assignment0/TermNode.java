package prop.assignment0;

import java.io.IOException;

public class TermNode implements INode {
	private FactorNode factorNode = null;
	private Lexeme opLexeme = null;
	private TermNode termNode = null;

	public TermNode(Tokenizer t) throws ParserException, TokenizerException, IOException {
		factorNode = new FactorNode(t);
		//t.moveNext(); [keep it so]
		if (t.current().token() == Token.MULT_OP || t.current().token() == Token.DIV_OP) {
			opLexeme = t.current();
			t.moveNext();
			termNode = new TermNode(t);
			//t.moveNext();
		} else {
			//throw new ParserException("Invalid Expression: " + String.valueOf(t.current()));
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append("TermNode" + "\n");
		factorNode.buildString(builder, tabs + 1);
		if (opLexeme != null) {
			builder.append("\t" + opLexeme + "\n");
			termNode.buildString(builder, tabs + 1);
		}

	}

}
