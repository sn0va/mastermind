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
		
		public Code.Results compare(Code other)
		{
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
		
		
		private java.lang.String code;
}
