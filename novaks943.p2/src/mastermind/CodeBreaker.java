package mastermind;

/**
 * Provides an interface for code breakers.
 * @author Joe Meehean
 *
 */
public interface CodeBreaker {

	/**
	 * Generates the next code breaker guess.
	 * @return the next guess.
	 */
	public Code nextGuess();
	
	/**
	 * Gives the code breaker the results of their most recent guess.
	 * @param guess the most recent guess
	 * @param results the results of comparing that guess to the secret code
	 */
	public void guessResults(Code guess, Code.Results results);
	
	/**
	 * Returns how many codes this breaker thinks are still possibly the secret code.
	 * This method allows me to test your code.
	 * @return possible code count
	 */
	public int possibleCodeCount();
}
