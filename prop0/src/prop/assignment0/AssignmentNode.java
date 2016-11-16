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
				//t.moveNext();
				if (t.current().token() == Token.SEMICOLON) {
					semiColonLexeme = t.current();
					//t.moveNext();
				} else {
					throw new ParserException("Invalid Expression: " + String.valueOf(t.current()));
				}
			}
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append("AssignmentNode" + "\n");
		builder.append("\t" + identLexeme + "\n");
		builder.append("\t" + assignLexeme + "\n");
		expressionNode.buildString(builder, tabs + 1);
		builder.append("\t" + semiColonLexeme + "\n");

	}

}
