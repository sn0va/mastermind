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
		Code secretCode = null;
		Code input;
		
		do
		{
			out.println("Enter a code with length " + length + " and range " + range + ":");
			input = new Code(scanner.next().toLowerCase());
			
			if (input.toString().length() != length)
				out.println("Error: incorrect length, please try again");
			
			else if (!isLetters(input.toString()))
				out.println("Invalid input, please provide letters only");
			
			else if (input.range() > range)
			{
				out.println("Error: out of range, please try again");
				out.println("Hint: your code shouldn't include any letters past " + String.valueOf((char) (('a' + range) - 1)) + " alphabetically");
			}
			
			else
				secretCode = input;
			
		} while (secretCode == null);
		
		
		return secretCode;
	}
	
	private boolean isLetters(String str) 
	{
		boolean isLetters = true;
		char[] characters = str.toCharArray();
		int i = 0;
		
		while ( isLetters && i < characters.length )
		{
			if (!Character.isLetter(characters[i]))
				isLetters = false;
			++i;
		}
		
		return isLetters;
	}
}
