package prop.assignment0;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

public class Program {
	public static void main(String[] args) {
		args = new String[] { "C:\\Users\\yahya\\git\\Prop0\\prop0\\src\\program1.txt",
				"C:\\Users\\yahya\\git\\Prop0\\prop0\\src\\parsetree1.txt" };
		String inputFileName = null;
		String outputFileName = null;
		IParser parser = null;
		INode root = null; // Root of the parse tree.
		StringBuilder builder = null;
		FileOutputStream stream = null;
		OutputStreamWriter writer = null;

		try {
			try {
				if (args.length < 2)
					throw new Exception("Incorrect number of parameters to program.");
				inputFileName = args[0];
				outputFileName = args[1];

				parser = new Parser();
				parser.open(inputFileName);
				root = parser.parse();
				builder = new StringBuilder();
				builder.append("PARSE TREE:\n");
				root.buildString(builder, 0);
				builder.append("\nEVALUATION:\n");
				builder.append(root.evaluate(null));

				stream = new FileOutputStream(outputFileName);
				writer = new OutputStreamWriter(stream);
				writer.write(builder.toString());
			} catch (Exception exception) {
				System.out.println("EXCEPTION: " + exception);
				exception.printStackTrace(System.out);
			} finally {
				if (parser != null)
					parser.close();
				if (writer != null)
					writer.close();
				if (stream != null)
					stream.close();
			}
		} catch (Exception exception) {
			exception.printStackTrace(System.out);
			System.out.println("EXCEPTION: " + exception);
		}
	}
}
