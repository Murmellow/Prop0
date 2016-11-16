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
			//t.moveNext();
		} else {
			// throw new ParserException("Invalid Expression: " +
			// String.valueOf(t.current()));
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append("ExpressionNode" + "\n");
		termNode.buildString(builder, tabs + 1);
		if (opLexeme != null) {
			builder.append("\t" + opLexeme + "\n");
			expressionNode.buildString(builder, tabs + 1);
		}
	}

}
