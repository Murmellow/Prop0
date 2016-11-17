/*
 Created by:
 Yahya Ajwad (yaaj2898)
 Max Jonsson (majo6981)
 */

package prop.assignment0;

import java.io.IOException;

public class FactorNode implements INode {
	private Lexeme leftParenthesisLexeme = null;
	private Lexeme rightParenthesisLexeme = null;
	private ExpressionNode expressionNode = null;
	private Lexeme integerLexeme = null;
	private Parser parserTabing = new Parser();

	public FactorNode(Tokenizer tokenizer) throws ParserException, TokenizerException, IOException {
		if (tokenizer.current().token() == Token.INT_LIT) {
			integerLexeme = tokenizer.current();
			tokenizer.moveNext();
		} else if (tokenizer.current().token() == Token.LEFT_PAREN) {
			leftParenthesisLexeme = tokenizer.current();
			tokenizer.moveNext();
			expressionNode = new ExpressionNode(tokenizer);
			if (tokenizer.current().token() == Token.RIGHT_PAREN) {
				rightParenthesisLexeme = tokenizer.current();
				tokenizer.moveNext();
			}
		} else {
			throw new ParserException("Invalid Expression: " + String.valueOf(tokenizer.current()));

		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		if (integerLexeme != null) {
			return (Double) integerLexeme.value();
		} else {
			return (Double) expressionNode.evaluate(args);
		}
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append(parserTabing.tabing(tabs) + "FactorNode" + "\n");
		if (integerLexeme != null) {
			builder.append(parserTabing.tabing(tabs + 1) + integerLexeme + "\n");
		} else {
			builder.append(parserTabing.tabing(tabs + 1) + leftParenthesisLexeme + "\n");
			expressionNode.buildString(builder, tabs + 1);
			builder.append(parserTabing.tabing(tabs + 1) + rightParenthesisLexeme + "\n");
		}
	}

}
