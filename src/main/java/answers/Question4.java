package answers;

public class Question4 {

	public static int selectionFailedTradedesks(String[][] rows, int numberMachines) {
		int[][] intRepresentation = transformStringsToInts(rows);
		int n = rows.length ;
		int answer = Integer.MAX_VALUE ;
		for (int i = 0; i < n ; i++) {
			int minRow = minSumRow(intRepresentation[i],numberMachines) ;
			if ( minRow < answer )
			{
				answer = minRow ;
			}
		}
		if ( answer == Integer.MAX_VALUE )
		{
			return 0 ;
		}
		return answer ;
	}
	private static int[][] transformStringsToInts( String[][] rows )
	{
		int noRows = rows.length;
		int noColumns = rows[0].length;
		int[][] answer = new int[noRows][noColumns] ;
		for (int i = 0; i < noRows ; i++)
		{
			for (int j = 0; j < noColumns; j++) {
				int value = getNumericValue(rows[i][j]);
				answer [ i ] [ j ] = value ;
			}
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
	private static int minSumRow(int[] row , int target )
	{
		int n = row.length ;
		int minSum = Integer.MAX_VALUE ;

		//Use just a variable to contain the sum of a valid subsequence
		int currentSum = 0 ;

		int currentConsecutiveValidNumbers = 0 ;
		for ( int i = 0 ; i < n ; i ++ )
		{
			if ( row [ i ] >= 0 )
			{
				currentSum += row [ i ] ;
				currentConsecutiveValidNumbers ++ ;
				if ( currentConsecutiveValidNumbers >= target )
				{
					if ( currentSum < minSum )
					{
						minSum = currentSum ;
					}
					currentSum -= row [ i - target + 1 ] ;
				}
			}
			else
			{
				//reset the variables in order to not add an invalid value to the sums
				currentSum = 0 ;
				currentConsecutiveValidNumbers = 0 ;
			}
		}
		return minSum ;
	}
}