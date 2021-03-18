package mastermind;

public class RandomCodeMaker implements CodeMaker {
	
	private int length;
	private int range;
	
	public RandomCodeMaker(int length, int range)
	{
		this.length = length;
		this.range = range;
	}
	
	/**
	 * Generates a code.
	 * @return the code generated
	 */
	public Code generateCode()
	{
		char[] charArr = new char[length];
		java.util.Random rand = new java.util.Random();
		
		for(int i = 0; i < length; ++i)
		{
			charArr[i] = (char) ('a' + rand.nextInt(range));
		}
		
		return new Code(String.valueOf(charArr));
	}
}
