package mastermind;

public class Code {

		public Code(java.lang.String code) 
		{
			this.code = code;
		}
		
		public boolean equals(java.lang.Object otherObject) 
		{
			boolean equal = false;
			boolean same = (this == otherObject);
			
			if (otherObject == null)
				equal = false;
			
			else if (!same && this.getClass() == otherObject.getClass()) {
				Code other = (Code) otherObject;
				
				equal = this.code.toLowerCase().equals(other.code.toLowerCase());
			}
			
			return equal || same;
		}
		
		public Code.Results compare (Code other) throws java.lang.IllegalArgumentException
		{
			if( this.code.length() != other.code.length() )
			{
				throw new java.lang.IllegalArgumentException("Codes being compared are not the same length");
			}
			
			int bullCount = 0;
			int cowCount = 0;
			
			java.lang.String thisCode = this.code.toLowerCase();
			java.lang.String otherCode = other.code.toLowerCase();
			
			boolean[] isBullIndex = new boolean[thisCode.length()];
			boolean[] isCowIndex = new boolean[thisCode.length()];
				
			for (int i = 0; i < thisCode.length(); ++i)
			{	
				if (thisCode.charAt(i) == otherCode.charAt(i)) 
				{
					isBullIndex[i] = true;
					++bullCount;
				}
			}
			
			int j = 0;
			for (int i = 0; i < thisCode.length(); ++i)
			{
				j = 0;
				
				while ( j < thisCode.length() && !isBullIndex[i] && thisCode.charAt(i) != otherCode.charAt(j) || 
							j < thisCode.length() && isBullIndex[j] || 
							j < thisCode.length() && isCowIndex[j] )
				{
					++j;
				}
				
				if ( j < thisCode.length() && !isBullIndex[i] )
				{
					isCowIndex[j] = true;
					++cowCount;
				}
			}
			
			Code.Results result = new Code.Results(bullCount, cowCount);
			
			return result;
		}
		
		public Code nextCode(int range) throws java.util.NoSuchElementException
		{
			if (!hasNextCode(range))
				throw new java.util.NoSuchElementException("No next code within that range");
			
			char[] letters = code.toCharArray();
			int i = letters.length - 1;
			boolean isLastInRange;
			
			do
			{	
				isLastInRange = nextLetter(letters[i], range) == 'a';
				letters[i] = nextLetter(letters[i], range);
				--i;
			} while (isLastInRange && i >= 0);
			
			return new Code(String.valueOf(letters));
		}
		
		private char nextLetter(char letter, int range)
		{
			char nextLetter;
			
			if (letter == 'a' + range - 1)
				nextLetter = 'a';
			
			else
				nextLetter = (char) (letter + 1);
			
			return nextLetter;
		}
		
		public boolean hasNextCode(int range)
		{
			char[] letters = code.toCharArray();
			boolean hasNext = true;
			int i = 0;
			
			while( i < letters.length && nextLetter(letters[i], range) == 'a' )
			{
				++i;
			}
			
			if ( i == letters.length )
				hasNext = false;
			
			return hasNext;
		}
		
		public int range()
		{
			char[] letters = code.toCharArray();
			
			char highestChar = 'a';
			
			for ( char letter : letters)
			{
				if (letter > highestChar)
					highestChar = letter;
			}
			
			return (int) (highestChar - 'a') + 1;
		}
		
		public class Results {
			
			Results(int bulls, int cows)
			{
				this.bulls = bulls;
				this.cows = cows;
			}
			
			public boolean equals(java.lang.Object otherObject) 
			{
				boolean equal = false;
				boolean same = (this == otherObject);
				
				if (otherObject == null)
					equal = false;
				
				else if (!same && this.getClass() == otherObject.getClass()) {
					Results other = (Results) otherObject;
					
					equal = this.bulls == other.bulls && this.cows == other.cows;
				}
				
				return equal || same;
			}
			
			public int getBulls() { return bulls; }
			public int getCows() { return cows; }
			
			private int bulls;
			private int cows;
		}
		
		public java.lang.String toString() {return code;}
		
		private java.lang.String code;
}
