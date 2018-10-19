package answers;

public class Question4 {

	//Uglier code but hope this will improve performance
	public static int selectionFailedTradedesks(String[][] rows, int numberMachines) {

		int n = rows.length ;
		int m = rows [ 0 ] . length;
		int answer = Integer.MAX_VALUE ;
		for (int i = 0; i < n ; i++) {
			int currentSum = 0 ;
			int currentConsecutiveValidValues = 0 ;
			for (int j = 0; j < m ; j++)
			{
				int value = getNumericValue( rows [ i ] [ j ] ) ;
				if ( value >= 0 )
				{
					currentConsecutiveValidValues++;
					currentSum += value ;
					if ( currentConsecutiveValidValues >= numberMachines )
					{
						if ( currentSum < answer )
						{
							answer = currentSum ;
						}
						int value1 = getNumericValue( rows [ i ] [ j - numberMachines + 1 ] ) ;
						currentSum -= value1 ;
					}
				}
				else
				{
					currentConsecutiveValidValues = 0 ;
					currentSum = 0 ;
				}
			}
		}
		if ( answer == Integer.MAX_VALUE )
		{
			return 0 ;
		}
		return answer ;
	}
	private static int getNumericValue(String s)
	{
		if ( s.length() == 1 && s.charAt(0) == 'X' )
		{
			return -1 ;
		}
		return Integer.parseInt(s);
	}
}