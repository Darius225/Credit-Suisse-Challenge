package answers;

public class Question2 {
	public static int equallyBalancedCashFlow(int[] cashflowIn, int[] cashflowOut)
	{


		//Dimensions
		int n = cashflowIn.length;
		int m = cashflowOut.length;

		boolean[] aValues = createSums ( cashflowIn ) ;
		boolean[] bValues = createSums ( cashflowOut ) ;

		int ans = 1000 ;
		for (int i = 1; i <= 100000  && ans != 0; i++) {
			if (aValues[i])
			{
				boolean found = false ;
				for (int j = 0; j < ans && found == false ; j++) {
					if ( bValues [ i - j ] )
					{
						found = true ;
						ans = j ;
					}
				}
			}
		}
		return ans ;
	}

	public static boolean[] createSums( int[] cashFlow ) {
		boolean[] previousTouchedValues = new boolean[100001];
		boolean[] touchedValues = new boolean[100001];
		int n = cashFlow.length ;
		previousTouchedValues [ 0 ] = true ;
		for (int i = 0; i < n ; i++) {
			for (int j = 0; j <= 100000 - cashFlow [ i ] ; j++)
			{
				touchedValues [ j + cashFlow [ i ] ] = previousTouchedValues [ j + cashFlow [ i ] ] | previousTouchedValues [ j ]  ;
			}
			for (int j = 1; j <= 100000 ; j++) {
				previousTouchedValues [ j ] = touchedValues [ j ] ;
			}
		}
		return previousTouchedValues ;
	}
}