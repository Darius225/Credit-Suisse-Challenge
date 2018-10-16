package answers;

public class Question4 {

	public static int selectionFailedTradedesks(String[][] rows, int numberMachines) {
		int[][] intRepresentation = transformStringsToInts(rows);
		int n = rows.length ;
		int answer = Integer.MAX_VALUE ;
		if ( ! noSolution(intRepresentation,numberMachines) )
		{
			return 0 ;
		}
		for (int i = 0; i < n ; i++) {
			int minRow = minSumRow(intRepresentation[i],numberMachines) ;
			if ( minRow < answer )
			{
				answer = minRow ;
			}
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
	private static boolean noSolution(int[][] values , int target )
	{
		int noRows = values.length;
		int noColumns = values[0].length;
		for (int i = 0; i < noRows ; i++)
		{
			int currentConsecutiveValues = 0 ;
			for (int j = 0; j < noColumns ; j++)
			{
				if ( values [ i ] [ j ] >= 0 )
				{
					currentConsecutiveValues ++ ;
					if ( currentConsecutiveValues == target )
					{
						return true ;
					}
				}
				if ( values [ i ] [ j ] < 0 )
				{
					currentConsecutiveValues = 0 ;
				}
			}
		}
		return false ;
	}
	private static int minSumRow(int[] row , int target )
	{
		int n = row.length ;
		int[] partialSums = new int[n];
		int minSum = Integer.MAX_VALUE ;
		partialSums [ 0 ] = row [ 0 ] ;
		for (int i = 1; i < n ; i++)
		{
			partialSums [ i ] = partialSums [ i - 1 ] + row [ i ] ;
		}
		int currentConsecutiveValidNumbers = 0 ;
		for ( int i = 0 ; i < n ; i ++ )
		{
			if ( row [ i ] >= 0 )
			{
				currentConsecutiveValidNumbers ++ ;
				if ( currentConsecutiveValidNumbers >= target )
				{
					int currentSum = partialSums [ i ]  ;
					if ( i >= target )
					{
						currentSum -= partialSums [ i - target ] ;
					}
					if ( currentSum < minSum )
					{
						minSum = currentSum ;
					}
				}
			}
			else
			{
				currentConsecutiveValidNumbers = 0 ;
			}
		}
		return minSum ;
	}
}