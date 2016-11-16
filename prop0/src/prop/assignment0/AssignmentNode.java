package prop.assignment0;

import java.io.IOException;

public class AssignmentNode implements INode {

	private Lexeme identLexeme = null;
	private Lexeme assignLexeme = null;
	private ExpressionNode expressionNode = null;
	private Lexeme semiColonLexeme = null;

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
		String tabString = "";
		for (int i = 0; i < tabs; i++) {
			tabString += "\t";
		}
		builder.append("AssignmentNode" + "\n");
		builder.append(tabString + "\t" + identLexeme + "\n");
		builder.append(tabString + "\t" + assignLexeme + "\n");
		expressionNode.buildString(builder, tabs + 1);
		builder.append(tabString + "\t" + semiColonLexeme + "\n");

	}

}
