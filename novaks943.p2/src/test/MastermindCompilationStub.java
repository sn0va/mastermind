package test;

import java.util.Scanner;

import mastermind.Code;
import mastermind.CodeBreaker;
import mastermind.CodeMaker;
import mastermind.ConsoleCodeBreaker;
import mastermind.ConsoleCodeMaker;
import mastermind.EliminationBreaker;
import mastermind.RandomCodeMaker;
import mastermind.Code.Results;

/**
 * This class is designed to help you test whether your Mastermind code will compile in my JUnit tests.
 * Be sure that you can compile this class, unmodified.  
 * If your code does not compile with this class, I will not be able to test your code.
 * If your code does compile with this class, odds are good it will compile in my JUnit tests.
 * Your code compiling with this class is not a sufficient level of testing.
 * @author Joe Meehean
 *
 */
public class MastermindCompilationStub {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// Code
		Code a = new Code("aaaa");
		Code b = new Code("cccd");
		Code.Results result = a.compare(b);
		int integer = a.range();
		boolean bool = a.hasNextCode(4);
		Code next = a.nextCode(4);
		
		// ConsoleCodeMaker
		Scanner scanner = new Scanner(System.in);
		CodeMaker maker = new ConsoleCodeMaker(scanner, System.out, 4, 4);
		Code code = maker.generateCode();
		
		// ConsoleCodeBreaker
		CodeBreaker breaker = new ConsoleCodeBreaker(scanner, System.out, 4, 4);
		code = breaker.nextGuess();
		breaker.guessResults(code, result);
		integer = breaker.possibleCodeCount();
		
		// RandomCodeMaker
		maker = new RandomCodeMaker(4, 4);
		code = maker.generateCode();
		
		// EliminationCodeBreaker
		breaker = new EliminationBreaker(4, 4);
		code = breaker.nextGuess();
		breaker.guessResults(code, result);
		integer = breaker.possibleCodeCount();
	}

}
