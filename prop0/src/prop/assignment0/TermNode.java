/*
 Created by:
 Yahya Ajwad (yaaj2898)
 Max Jonsson (majo6981)
 */

package prop.assignment0;

import java.io.IOException;

public class TermNode implements INode {
	private FactorNode factorNode = null;
	private Lexeme operatorLexeme = null;
	private TermNode termNode = null;
	private Parser parserTabing = new Parser();

	public TermNode(Tokenizer tokenizer) throws ParserException, TokenizerException, IOException {
		factorNode = new FactorNode(tokenizer);
		if (tokenizer.current().token() == Token.MULT_OP || tokenizer.current().token() == Token.DIV_OP) {
			operatorLexeme = tokenizer.current();
			tokenizer.moveNext();
			termNode = new TermNode(tokenizer);
		} else {
		}
	}

	@Override
	public Object evaluate(Object[] args) throws Exception {
		Double factorNodeEvaluation = (Double) factorNode.evaluate(args);
		if (termNode != null) {
			Double termNodeEvaluation = (Double) termNode.evaluate(args);
			if (operatorLexeme.token() == Token.MULT_OP) {
				return factorNodeEvaluation * termNodeEvaluation;
			} else {
				return factorNodeEvaluation / termNodeEvaluation;
			}
		}
		return factorNodeEvaluation;
	}

	@Override
	public void buildString(StringBuilder builder, int tabs) {
		builder.append(parserTabing.tabing(tabs) + "TermNode" + "\n");
		factorNode.buildString(builder, tabs + 1);
		if (operatorLexeme != null) {
			builder.append(parserTabing.tabing(tabs) + "\t" + operatorLexeme + "\n");
			termNode.buildString(builder, tabs + 1);
		}

	}

}
