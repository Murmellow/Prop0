package prop.assignment0;

public class FactorNode implements INode {
	private Lexeme leftParLexeme = null;
	private Lexeme rightParLexeme = null;
	private ExpressionNode expressionNode = null;
	private Lexeme intLexeme = null;

	public FactorNode(Tokenizer t) {
		if (t.current().token() == Token.LEFT_PAREN)
			leftParLexeme = t.current();
		else if (t.current().token() == Token.RIGHT_PAREN)
			rightParLexeme = t.current();
		else if (t.current().token() == Token.INT_LIT)
			intLexeme = t.current();
		else{
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
		builder.append("FactorNode" + "\n");
		builder.append("\t" + leftParLexeme + "\n");
		builder.append("\t" + leftParLexeme + "\n");
		builder.append("\t" + intLexeme + "\n");
		builder.append("\t" + expressionNode + "\n");
		
		
	}

}
