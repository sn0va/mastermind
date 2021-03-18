package mastermind;

public class ConsoleCodeMaker implements CodeMaker{
	private java.util.Scanner scanner;
	private java.io.PrintStream out;
	private int length;
	private int range;
	
	public ConsoleCodeMaker(java.util.Scanner scanner, java.io.PrintStream out, int codeLength, int codeRange)
	{
		this.scanner = scanner;
		this.out = out;
		this.length = codeLength;
		this.range = codeRange;
	}
	
	/**
	 * Generates a code.
	 * @return the code generated
	 */
	public Code generateCode()
	{
		out.println("Enter a code with length " + length + " and range " + range + ":");
		return new Code(scanner.next());
	}
}
