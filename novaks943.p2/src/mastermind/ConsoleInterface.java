package mastermind;

public class ConsoleInterface {

	public static void main(String[] args) {
		java.util.Scanner scan = new java.util.Scanner(System.in);
		java.io.PrintStream out = new java.io.PrintStream(System.out);
		
		out.println("Enter length: ");
		int length = scan.nextInt();
		
		out.println("Enter range: ");
		int range = scan.nextInt();
		
		String choice;
		CodeMaker maker;
		CodeBreaker breaker;
		
		do
		{
		out.println("Select a human(h) or computer(c) code maker: ");
		choice = scan.next();
		} while (!choice.equals("h") && !choice.equals("c"));
		
		if(choice.equals("h"))
			maker = new ConsoleCodeMaker(scan, out, length, range);
		else
			maker = new RandomCodeMaker(length, range);
		
		do
		{
		out.println("Select a human(h) or computer(c) code breaker: ");
		choice = scan.next();
		} while (!choice.equals("h") && !choice.equals("c"));
		
		if(choice.equals("h"))
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

}
