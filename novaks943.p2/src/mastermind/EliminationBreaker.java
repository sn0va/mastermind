package mastermind;

public class EliminationBreaker implements CodeBreaker {
	
	private int length;
	private int range;
	private Code[] possibilities;
	private boolean[] checked;
	private int checkedCount;
	
	public EliminationBreaker(int codeLength, int codeRange)
	{
		this.length = codeLength;
		this.range = codeRange;
		
		this.possibilities = getPossibilities(codeRange, codeLength);
		this.checked = new boolean[possibilities.length];
		this.checkedCount = 0;
	}
	
	public Code[] getPossibilities(int range, int length)
	{
		char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		
		int size = (int) java.lang.Math.pow(range, length);
		
		StringBuilder[] possArr = new StringBuilder[size];
		 
		for (int i = 0; i < possArr.length; i++) 
		{
			 possArr[i] = new StringBuilder("");
		}
		
		int timesWritten = 1;
		int letterTrack = 0;
		int j = 0;
		
		for (int i = 0; i < length; ++i)
		{
			j = 0;
			while (j < size)
			{
				for (int k = 0; k < timesWritten; ++k)
				{
					possArr[j].insert(0, alphabet[letterTrack]);
					++j;
				}
				if (letterTrack < range - 1)
					++letterTrack;
				else
					letterTrack = 0;
			}
			timesWritten *= range;
		}
		
		Code[] codeArr = new Code[size];
		
		for(int i = 0; i < codeArr.length; ++i)
		{
			codeArr[i] = new Code(possArr[i].toString());
		}
		
		return codeArr;
	}
	
	/**
	 * Generates the next code breaker guess.
	 * @return the next guess.
	 */
	public Code nextGuess()
	{
		int i = 0;
		
		while (checked[i])
			++i;
		
		return possibilities[i];
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
