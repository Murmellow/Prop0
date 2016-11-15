package prop.assignment0;

public class ExpressionNode implements INode {

	private TermNode termNode = null;
	private Lexeme addOpLexeme = null;
	private Lexeme subOpLexeme = null;
	private ExpressionNode expressionNode = null;

	public ExpressionNode(Tokenizer t) {
		if (t.current().token() == Token.ADD_OP)
			addOpLexeme = t.current();
		else if (t.current().token() == Token.SUB_OP)
			subOpLexeme = t.current();
		else {
			termNode = new TermNode(t);
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
		builder.append(addOpLexeme + "\n");
		builder.append(subOpLexeme + "\n");
	}

}
