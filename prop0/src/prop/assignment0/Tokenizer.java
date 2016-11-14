package prop.assignment0;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.HashMap;
//Beatrice
public class Tokenizer {

    private static Map<Character, Token> symbols = null;

    private Scanner scanner = null;
    private Lexeme current = null;
    private Lexeme next = null;

    public Tokenizer() {
	symbols = new HashMap<Character, Token>();
	symbols.put('.', Token.STOP);
	symbols.put(Scanner.EOF, Token.EOF);
    }

    /**
     * Opens a file for tokenizing.
     */
    void open(String fileName) throws IOException, TokenizerException {
	scanner = new Scanner();
	scanner.open(fileName);
	scanner.moveNext();
	next = extractLexeme();		
    }
	
    /**
     * Returns the current token in the stream.
     */
    Lexeme current(){
	return current;
    }

    /**
     * Moves current to the next token in the stream.
     */
    void moveNext() throws IOException, TokenizerException {
	if (scanner == null)
	    throw new IOException("No open file.");
	current = next;
	if (next.token() != Token.EOF)
	    next = extractLexeme();
    }

    private void consumeWhiteSpaces() throws IOException {
	while (Character.isWhitespace(scanner.current())){
	    scanner.moveNext();
	}
    }

    private Lexeme extractLexeme() throws IOException, TokenizerException {
	consumeWhiteSpaces();
	Character ch = scanner.current();
	if (ch == Scanner.EOF)
	    return new Lexeme(ch, Token.EOF);
	else if (Character.isLetter(ch))
	    if (ch == 'a' || ch == 't'){
		return extractDeterminer();
	    } else if (ch == 'c' || ch == 'm') {
		return extractNoun();
	    } else if (ch == 'h' || ch == 's') {
		return extractVerb();
	    } else throw new TokenizerException("Unknown character: " + String.valueOf(ch)); 
	else if (symbols.containsKey(ch)) {
	    scanner.moveNext();
	    return new Lexeme(ch, symbols.get(ch));
	}
	else
	    throw new TokenizerException("Unknown character: " + String.valueOf(ch));
	}

    private Lexeme extractDeterminer() throws IOException {
	StringBuilder strBuilder = new StringBuilder();
	while (Character.isLetter(scanner.current())) {
	    strBuilder.append(scanner.current());
	    scanner.moveNext();
	}
	return new Lexeme(strBuilder.toString(), Token.DETERMINER);
    }

    private Lexeme extractNoun() throws IOException {
	StringBuilder strBuilder = new StringBuilder();
	while (Character.isLetter(scanner.current())) {
	    strBuilder.append(scanner.current());
	    scanner.moveNext();
	}
	return new Lexeme(strBuilder.toString(), Token.NOUN);
    }

    private Lexeme extractVerb() throws IOException {
	StringBuilder strBuilder = new StringBuilder();
	while (Character.isLetter(scanner.current())) {
	    strBuilder.append(scanner.current());
	    scanner.moveNext();
	}
	return new Lexeme(strBuilder.toString(), Token.VERB);
    }

    /**
     * Closes the file and releases any system resources associated with it.
     */
    public void close() throws IOException {
		if (scanner != null)
			scanner.close();
    }

}


