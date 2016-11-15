package prop.assignment0;

import java.io.IOException;

public class AssignmentNode implements INode {

	private Lexeme identLexeme = null;
	private Lexeme assignLexeme = null;
	private ExpressionNode expressionNode = null;
	private Lexeme semiColonLexeme = null;

	public AssignmentNode(Tokenizer t) throws ParserException, TokenizerException, IOException {
		if (t.current().token() == Token.IDENT)
			identLexeme = t.current();
		else if (t.current().token() == Token.ASSIGN_OP)
			assignLexeme = t.current();
		else if (t.current().token() == Token.SEMICOLON)
			semiColonLexeme = t.current();
		else {
			expressionNode = new ExpressionNode(t);
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
		builder.append("\t" + semiColonLexeme + "\n");
		expressionNode.buildString(builder, tabs + 1);

	}

}
