package mastermind;

public class ConsoleCodeBreaker implements CodeBreaker {
	private java.util.Scanner scanner;
	private java.io.PrintStream out;
	private int length;
	private int range;
	
	private ULLinkedList<Code> possibilities;
	
	public ConsoleCodeBreaker(java.util.Scanner scanner, java.io.PrintStream out, int codeLength, int codeRange)
	{
		this.scanner = scanner;
		this.out = out;
		this.length = codeLength;
		this.range = codeRange;
		
		this.possibilities = getPossibilities(codeRange, codeLength);
	}
	
	private ULLinkedList<Code> getPossibilities(int range, int length)
	{
		
		String initialString = "";
		for(int i = 0; i < length; ++i)
			initialString += "a";
		
		Code initialCode = new Code(initialString);
		Code currentCode = initialCode;
		
		ULLinkedList<Code> list = new ULLinkedList<Code>();
		
		list.addLast(initialCode);
		
		while (currentCode.hasNextCode(range))
		{
			currentCode = currentCode.nextCode(range);
			list.addLast(currentCode);
		}
		
		return list;
	}
	
	public Code nextGuess()
	{
		Code guess = null;
		Code input;
		
		do
		{
			out.println("Enter a guess with length " + length + " and range " + range + ":");
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
				guess = input;
			
		} while (guess == null);
		
		
		return guess;
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
	
	public void guessResults(Code guess, Code.Results results)
	{
		Code.Results currentResult;
		java.util.Iterator<Code> iter = possibilities.iterator();
		
		while (iter.hasNext())
		{
			currentResult = guess.compare(iter.next());
			
			if(!currentResult.equals(results))
			{
				iter.remove();
			}
		}
	}
	

	public int possibleCodeCount()
	{
		return possibilities.size();
	}
}
