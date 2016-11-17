package prop.assignment0;

import java.io.IOException;

public class TermNode implements INode {
	private FactorNode factorNode = null;
	private Lexeme opLexeme = null;
	private TermNode termNode = null;
	private Parser parserTabing = new Parser();

	public TermNode(Tokenizer t) throws ParserException, TokenizerException, IOException {
		factorNode = new FactorNode(t);
		if (t.current().token() == Token.MULT_OP || t.current().token() == Token.DIV_OP) {
			opLexeme = t.current();
			t.moveNext();
			termNode = new TermNode(t);
			// t.moveNext();
		} else {
			// throw new ParserException("Invalid Expression: " +
			// String.valueOf(t.current()));
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		Double d = (Double) factorNode.evaluate(args);
		if (termNode != null) {
			Double e = (Double) termNode.evaluate(args);
			if (opLexeme.token() == Token.MULT_OP) {
				return d * e;
			} else {
				return d / e;
			}
		}
		return d;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append(parserTabing.tabing(tabs) + "TermNode" + "\n");
		factorNode.buildString(builder, tabs + 1);
		if (opLexeme != null) {
			builder.append(parserTabing.tabing(tabs) + "\t" + opLexeme + "\n");
			termNode.buildString(builder, tabs + 1);
		}

	}

}
