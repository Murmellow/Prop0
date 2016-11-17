package prop.assignment0;

import java.io.IOException;

public class AssignmentNode implements INode {

	private Lexeme identLexeme = null;
	private Lexeme assignLexeme = null;
	private ExpressionNode expressionNode = null;
	private Lexeme semiColonLexeme = null;
	private Parser parserTabing = new Parser();

	public AssignmentNode(Tokenizer t) throws ParserException, TokenizerException, IOException {
		if (t.current().token() == Token.IDENT) {
			identLexeme = t.current();
			t.moveNext();
			if (t.current().token() == Token.ASSIGN_OP) {
				assignLexeme = t.current();
				t.moveNext();
				expressionNode = new ExpressionNode(t);
				// t.moveNext();
				if (t.current().token() == Token.SEMICOLON) {
					semiColonLexeme = t.current();
					t.moveNext();
				} else {
					throw new ParserException("Invalid Expression: " + String.valueOf(t.current()));
				}
			}
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		return identLexeme.value().toString() + " " + assignLexeme.value().toString() + " "
				+ (Double) expressionNode.evaluate(args);
		// return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append("AssignmentNode" + "\n");
		builder.append(parserTabing.tabing(tabs + 1) + identLexeme + "\n");
		builder.append(parserTabing.tabing(tabs + 1) + assignLexeme + "\n");
		expressionNode.buildString(builder, tabs + 1);
		builder.append(parserTabing.tabing(tabs + 1) + semiColonLexeme + "\n");

	}

}
