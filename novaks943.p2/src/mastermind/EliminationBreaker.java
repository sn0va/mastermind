package mastermind;

public class EliminationBreaker implements CodeBreaker {
	
	private ULLinkedList<Code> possibilities;
	
	public EliminationBreaker(int codeLength, int codeRange)
	{
		this.possibilities = getPossibilities(codeRange, codeLength);
	}
	
	private ULLinkedList<Code> getPossibilities(int range, int length)
	{		
		// Create an initial string with the correct amount of a's
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
		return possibilities.getFirst();
	}
	
	public void guessResults(Code guess, Code.Results results)
	{
		Code.Results currentResult;
		java.util.Iterator<Code> iter = possibilities.iterator();
		
		while (iter.hasNext())
		{
			// Comparing the guess passed in to whatever possibility iter is on
			currentResult = guess.compare(iter.next());
			
			// If the bulls and cows of currentResult don't equal the results passed in
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
