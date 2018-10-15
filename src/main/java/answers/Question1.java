package answers;

public class Question1 {

	public static int bestMergedPortfolio(int[] portfolios) {
		int n = portfolios.length ;
		int maxValue = 0 ;
		for (int i = 0; i < n - 1 ; i++) {
			for (int j = i + 1 ; j < n ; j++) {
				int pairValue = portfolios [ i ] ^ portfolios [ j ] ;
				if ( pairValue > maxValue )
				{
					maxValue = pairValue ;
				}
			}
		}
		return maxValue;
	}

}
