/*
 Created by:
 Yahya Ajwad (yaaj2898)
 Max Jonsson (majo6981)
 */

package prop.assignment0;

import java.io.IOException;

public class AssignmentNode implements INode {

	private Lexeme identifierLexeme = null;
	private Lexeme assignmentLexeme = null;
	private ExpressionNode expressionNode = null;
	private Lexeme semiColonLexeme = null;
	private Parser parserTabing = new Parser();

	public AssignmentNode(Tokenizer tokenizer) throws ParserException, TokenizerException, IOException {
		if (tokenizer.current().token() == Token.IDENT) {
			identifierLexeme = tokenizer.current();
			tokenizer.moveNext();
			if (tokenizer.current().token() == Token.ASSIGN_OP) {
				assignmentLexeme = tokenizer.current();
				tokenizer.moveNext();
				expressionNode = new ExpressionNode(tokenizer);
				if (tokenizer.current().token() == Token.SEMICOLON) {
					semiColonLexeme = tokenizer.current();
					tokenizer.moveNext();
				} else {
					throw new ParserException("Invalid Expression: " + String.valueOf(tokenizer.current()));
				}
			}
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		return identifierLexeme.value().toString() + " " + assignmentLexeme.value().toString() + " "
				+ (Double) expressionNode.evaluate(args);
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append("AssignmentNode" + "\n");
		builder.append(parserTabing.tabing(tabs + 1) + identifierLexeme + "\n");
		builder.append(parserTabing.tabing(tabs + 1) + assignmentLexeme + "\n");
		expressionNode.buildString(builder, tabs + 1);
		builder.append(parserTabing.tabing(tabs + 1) + semiColonLexeme + "\n");

	}

}
