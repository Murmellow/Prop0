package prop.assignment0;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Tokenizer implements ITokenizer {

	private static Map<Character, Token> symbols = null;

	private Scanner scanner = null;
	private Lexeme current = null;
	private Lexeme next = null;

	public Tokenizer() {
		symbols = new HashMap<Character, Token>();
		symbols.put(Scanner.EOF, Token.EOF);
	}

	@Override
	public void open(String fileName) throws IOException, TokenizerException {
		scanner = new Scanner();
		scanner.open(fileName);
		scanner.moveNext();
		next = extractLexeme();
	}

	@Override
	public Lexeme current() {
		return current;
	}

	private Lexeme extractLexeme() throws IOException, TokenizerException {
		consumeWhiteSpaces();
		Character ch = scanner.current();
		if (ch == Scanner.EOF)
			return new Lexeme(ch, Token.EOF);
		else if (Character.isLetter(ch)){
			return extractIdentifier();
		}else if (Character.isDigit(ch)){
			return extractInt();
		}else if (ch == '+') {
			scanner.moveNext();
			return new Lexeme(ch, Token.ADD_OP);
		} else if (ch == '-') {
			scanner.moveNext();
			return new Lexeme(ch, Token.SUB_OP);
		} else if (ch == '*') {
			scanner.moveNext();
			return new Lexeme(ch, Token.MULT_OP);
		} else if (ch == '/') {
			scanner.moveNext();
			return new Lexeme(ch, Token.DIV_OP);
		} else if (ch == '=') {
			scanner.moveNext();
			return new Lexeme(ch, Token.ASSIGN_OP);
		} else if (ch == '(') {
			scanner.moveNext();
			return new Lexeme(ch, Token.LEFT_PAREN);
		} else if (ch == ')') {
			scanner.moveNext();
			return new Lexeme(ch, Token.RIGHT_PAREN);
		} else if (ch == ';') {
			scanner.moveNext();
			return new Lexeme(ch, Token.SEMICOLON);
		} else if (ch == '{') {
			scanner.moveNext();
			return new Lexeme(ch, Token.LEFT_CURLY);
		} else if (ch == '}') {
			scanner.moveNext();
			return new Lexeme(ch, Token.RIGHT_CURLY);
		}else if (symbols.containsKey(ch)) {
			 scanner.moveNext();
			 return new Lexeme(ch, symbols.get(ch));
		} else
			throw new TokenizerException("Unknown character: " + String.valueOf(ch));
	}

	private Lexeme extractInt() throws IOException {
		StringBuilder strBuilder = new StringBuilder();
		while (Character.isDigit(scanner.current())) {
			strBuilder.append(scanner.current());
			scanner.moveNext();
		}
		return new Lexeme(strBuilder.toString(), Token.INT_LIT);
	}

	private Lexeme extractIdentifier() throws IOException {
		StringBuilder strBuilder = new StringBuilder();
		while (Character.isLetter(scanner.current())) {
			strBuilder.append(scanner.current());
			scanner.moveNext();
		}
		return new Lexeme(strBuilder.toString(), Token.IDENT);
	}

	private void consumeWhiteSpaces() throws IOException {
		while (Character.isWhitespace(scanner.current())) {
			scanner.moveNext();
		}
	}

	@Override
	public void moveNext() throws IOException, TokenizerException {
		if (scanner == null)
			throw new IOException("No open file.");
		current = next;
		if (next.token() != Token.EOF)
			next = extractLexeme();
	}

	@Override
	public void close() throws IOException {
		if (scanner != null)
			scanner.close();
	}

}
