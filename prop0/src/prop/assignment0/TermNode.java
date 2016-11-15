package prop.assignment0;

public class TermNode implements INode {
	private FactorNode factorNode = null;
	private Lexeme mulOpLexeme = null;
	private Lexeme devOpLexeme = null;
	private TermNode termNode = null;


	public TermNode(Tokenizer t) {
		if (t.current().token() == Token.MULT_OP)
			mulOpLexeme = t.current();
		else if (t.current().token() == Token.DIV_OP )
			devOpLexeme = t.current();
		else {
			factorNode = new FactorNode(t);
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append("TermNode" + "\n");
		builder.append("\t"+mulOpLexeme + "\n");
		builder.append("\t"+devOpLexeme + "\n");
		
		
	}

}
