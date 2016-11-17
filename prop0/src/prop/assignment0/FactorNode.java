package prop.assignment0;

import java.io.IOException;

public class FactorNode implements INode {
	private Lexeme leftParLexeme = null;
	private Lexeme rightParLexeme = null;
	private ExpressionNode expressionNode = null;
	private Lexeme intLexeme = null;
	private Parser parserTabing = new Parser();

	public FactorNode(Tokenizer t) throws ParserException, TokenizerException, IOException {
		if (t.current().token() == Token.INT_LIT) {
			intLexeme = t.current();
			t.moveNext();
		} else if (t.current().token() == Token.LEFT_PAREN) {
			leftParLexeme = t.current();
			t.moveNext();
			expressionNode = new ExpressionNode(t);
			// t.moveNext();
			if (t.current().token() == Token.RIGHT_PAREN) {
				rightParLexeme = t.current();
				t.moveNext();
			}
		} else {
			throw new ParserException("Invalid Expression: " + String.valueOf(t.current()));

		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if (intLexeme != null) {
			System.out.print(intLexeme.value());
			return (Double) intLexeme.value();
		} else {
			return (Double) expressionNode.evaluate(args);
		}
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append(parserTabing.tabing(tabs) + "FactorNode" + "\n");
		if (intLexeme != null) {
			builder.append(parserTabing.tabing(tabs + 1) + intLexeme + "\n");
		} else {
			builder.append(parserTabing.tabing(tabs + 1) + leftParLexeme + "\n");
			expressionNode.buildString(builder, tabs + 1);
			builder.append(parserTabing.tabing(tabs + 1) + rightParLexeme + "\n");
		}
	}

}
