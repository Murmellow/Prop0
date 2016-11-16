package prop.assignment0;

import java.io.IOException;

public class ExpressionNode implements INode {

	private TermNode termNode = null;
	private Lexeme opLexeme = null;
	private ExpressionNode expressionNode = null;

	public ExpressionNode(Tokenizer t) throws ParserException, TokenizerException, IOException {
		termNode = new TermNode(t);
		// t.moveNext();
		if (t.current().token() == Token.ADD_OP || t.current().token() == Token.SUB_OP) {
			opLexeme = t.current();
			t.moveNext();
			expressionNode = new ExpressionNode(t);
			// t.moveNext();
		} else {
			// throw new ParserException("Invalid Expression: " +
			// String.valueOf(t.current()));
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		Double d = (Double) termNode.evaluate(args);
		if (expressionNode != null) {
			Double e = (Double) expressionNode.evaluate(args);
			if (opLexeme.token() == Token.ADD_OP) {
				return d + e;
			} else {
				return d - e;
			}
		}
		return d;

	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		String tabString = "";
		for (int i = 0; i < tabs; i++) {
			tabString += "\t";
		}
		builder.append(tabString + "ExpressionNode" + "\n");
		termNode.buildString(builder, tabs + 1);
		if (opLexeme != null) {
			builder.append(tabString + "\t" + opLexeme + "\n");
			expressionNode.buildString(builder, tabs + 1);
		}
	}

}
