package mastermind;

public class MS1Main {

	public static void main(String[] args) {
		Code code1 = new Code("abdd");
		Code code2 = new Code("afda");
		
		Code.Results myResults = code1.compare(code2);
		
		System.out.println("Bulls: " + myResults.getBulls());
		System.out.println("Cows: " + myResults.getCows());
	}

}
