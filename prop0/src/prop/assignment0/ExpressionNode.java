/*
 Created by:
 Yahya Ajwad (yaaj2898)
 Max Jonsson (majo6981)
 */

package prop.assignment0;

import java.io.IOException;

public class ExpressionNode implements INode {

	private TermNode termNode = null;
	private Lexeme operatorLexeme = null;
	private ExpressionNode expressionNode = null;
	private Parser parserTabing = new Parser();

	public ExpressionNode(Tokenizer tokenizer) throws ParserException, TokenizerException, IOException {
		termNode = new TermNode(tokenizer);
		if (tokenizer.current().token() == Token.ADD_OP || tokenizer.current().token() == Token.SUB_OP) {
			operatorLexeme = tokenizer.current();
			tokenizer.moveNext();
			expressionNode = new ExpressionNode(tokenizer);
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		Double termNodeEvaluation = (Double) termNode.evaluate(args);
		if (expressionNode != null) {
			Double expressionNodeEvaluation = (Double) expressionNode.evaluate(args);
			if (operatorLexeme.token() == Token.ADD_OP) {
				return termNodeEvaluation + expressionNodeEvaluation;
			} else {
				return termNodeEvaluation - expressionNodeEvaluation;
			}
		}
		return termNodeEvaluation;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append(parserTabing.tabing(tabs) + "ExpressionNode" + "\n");
		termNode.buildString(builder, tabs + 1);
		if (operatorLexeme != null) {
			builder.append(parserTabing.tabing(tabs + 1) + operatorLexeme + "\n");
			expressionNode.buildString(builder, tabs + 1);
		}
	}

}
