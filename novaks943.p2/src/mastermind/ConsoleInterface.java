package mastermind;

public class ConsoleInterface {

	public static void main(String[] args) {
		java.util.Scanner scan = new java.util.Scanner(System.in);
		
		System.out.println("Enter length: ");
		int length = scan.nextInt();
		
		System.out.println("Enter range: ");
		int range = scan.nextInt();
		
		System.out.println("Enter a code with length " + length + " and range " + range + ":");
		Code secretCode = new Code(scan.next());
		
		EliminationBreaker cpu = new EliminationBreaker(length, range);
		
		Code currentGuess;
		Code.Results currentResults;
		int possibleCodeCount;
		int guessCount = 1;
		
		do
		{
			possibleCodeCount = cpu.possibleCodeCount();
			currentGuess = cpu.nextGuess();
			currentResults = secretCode.compare(currentGuess);
			System.out.println("Possible codes: " + possibleCodeCount);
			System.out.println("Breakers guess(" + guessCount++ + "): " + currentGuess.toString());
			System.out.println("Bulls-Cows: " + currentResults.getBulls() + "-" + currentResults.getCows());
			
			cpu.guessResults(currentGuess, currentResults);
		} while(possibleCodeCount > 1 && !(currentResults.getBulls() == length));
		
		System.out.println("Game over!");
	}

}
