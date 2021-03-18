package mastermind;

public class ConsoleCodeBreaker implements CodeBreaker {
	private java.util.Scanner scanner;
	private java.io.PrintStream out;
	
	private Code[] possibilities;
	private boolean[] checked;
	private int checkedCount;
	
	public ConsoleCodeBreaker(java.util.Scanner scanner, java.io.PrintStream out, int codeLength, int codeRange)
	{
		this.scanner = scanner;
		this.out = out;
		
		this.possibilities = getPossibilities(codeRange, codeLength);
		this.checked = new boolean[possibilities.length];
		this.checkedCount = 0;
	}
	
	private Code[] getPossibilities(int range, int length)
	{
		
		int size = (int) java.lang.Math.pow(range, length);
		
		String[] possArr = new String[size];
		
		char[] currentLetters = new char[length];
		for(int i = 0; i < length; ++i)
			currentLetters[i] = 'a';
		
		for (int i = 0; i < size; ++i)
		{
			possArr[i] = String.valueOf(currentLetters);
			currentLetters = nextCombo(currentLetters, range);
		}
		
		Code[] codeArr = new Code[size];
		
		for(int i = 0; i < codeArr.length; ++i)
		{
			codeArr[i] = new Code(possArr[i].toString());
		}
		
		return codeArr;
	}
	
	private char[] nextCombo (char[] letters, int range)
	{
		int i = letters.length - 1;
		boolean isLastInRange;
		
		do
		{
			isLastInRange = nextLetter(letters[i], range) == 'a';
			letters[i] = nextLetter(letters[i], range);
			--i;
		} while (isLastInRange && i >= 0);
		
		return letters;
	}
	
	private char nextLetter(char letter, int range)
	{
		char nextLetter;
		
		if (letter == 'a' + range - 1)
		{
			nextLetter = 'a';
		}
		
		else
		{
			nextLetter = (char) (letter + 1);
		}
		
		return nextLetter;
	}
	
	
	/**
	 * Generates the next code breaker guess.
	 * @return the next guess.
	 */
	public Code nextGuess()
	{
		out.println("What is your next guess: ");
		return new Code(scanner.next());
	}
	
	/**
	 * Gives the code breaker the results of their most recent guess.
	 * @param guess the most recent guess
	 * @param results the results of comparing that guess to the secret code
	 */
	public void guessResults(Code guess, Code.Results results)
	{
		Code.Results currentResult;
		
		for (int i = 0; i < possibilities.length; ++i)
		{
			currentResult = guess.compare(possibilities[i]);
			
			if(!currentResult.equals(results))
			{
				if (!checked[i])
				{
					checked[i] = true;
					++checkedCount;
				}
			}
		}
	}
	
	/**
	 * Returns how many codes this breaker thinks are still possibly the secret code.
	 * This method allows me to test your code.
	 * @return possible code count
	 */
	public int possibleCodeCount()
	{
		return possibilities.length - checkedCount;
	}
}
