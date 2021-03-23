package mastermind;

public class ConsoleInterface {

	public static void main(String[] args) {
		java.util.Scanner scan = new java.util.Scanner(System.in);
		java.io.PrintStream out = new java.io.PrintStream(System.out);
		
		int length = getIntAboveZero("length", scan);
		int range = getRange(scan);
		
		
		CodeMaker maker;
		
		if(getIsHuman("code maker", scan))
			maker = new ConsoleCodeMaker(scan, out, length, range);
		else
			maker = new RandomCodeMaker(length, range);
		
		
		CodeBreaker breaker;
		
		if(getIsHuman("code breaker", scan))
			breaker = new ConsoleCodeBreaker(scan, out, length, range);
		else
			breaker = new EliminationBreaker(length, range);
		
		
		Code secretCode = maker.generateCode();
		
		out.println("The secret code is: " + secretCode.toString());
		
		Code currentGuess;
		Code.Results currentResults;
		int possibleCodeCount;
		int guessCount = 1;
		
		do
		{
			possibleCodeCount = breaker.possibleCodeCount();
			currentGuess = breaker.nextGuess();
			currentResults = secretCode.compare(currentGuess);
			out.println("Possible codes: " + possibleCodeCount);
			out.println("Breakers guess(" + guessCount++ + "): " + currentGuess.toString());
			out.println("Bulls-Cows: " + currentResults.getBulls() + "-" + currentResults.getCows());
			
			breaker.guessResults(currentGuess, currentResults);
		} while(possibleCodeCount > 1 && !(currentResults.getBulls() == length));
		
		out.println("Game over!");
		scan.close();
		out.close();
	}

	private static int getIntAboveZero(String variableName, java.util.Scanner scan)
	{
		int integer = -1;
		int input;
		
		do
		{
			try 
			{			
				System.out.println("Enter " + variableName + ": ");
				input = scan.nextInt();
				
				if(input > 0)
					integer = input;
				
				else
					System.out.println("Please enter a number above 0");
			}
			
			catch (java.util.InputMismatchException e)
			{
				System.out.println("Invalid input, please try again");
				scan.next();
			}
		} while (integer == -1);
		
		return integer;
	}
	
	private static int getRange(java.util.Scanner scan)
	{
		int range = -1;
		int input;
		
		do
		{
			input = getIntAboveZero("range", scan);
			
			if (input > 26)
				System.out.println("Range must be between 1 and 26");
			
			else
				range = input;
			
		} while (range == -1);
		
		
		return range;
	}
	
	private static boolean getIsHuman(String roleName, java.util.Scanner scan)
	{
		boolean isHuman = false;
		String choice;
		
		do
		{
			System.out.println("Select a human(h) or computer(c) " + roleName + ": ");
			choice = scan.next().toLowerCase();
			
			if (!choice.equals("h") && !choice.equals("c"))
				System.out.println("Invalid input, please try again");
			
		} while (!choice.equals("h") && !choice.equals("c"));
		
		if (choice.equals("h"))
			isHuman = true;
		
		return isHuman;
	}
	
}
