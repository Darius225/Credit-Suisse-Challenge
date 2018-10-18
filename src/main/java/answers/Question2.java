package answers;

import java.util.ArrayList;
import java.util.List;

public class Question2 {
	public static int equallyBalancedCashFlow(int[] cashflowIn, int[] cashflowOut) {


		//Dimensions
		int n = cashflowIn.length;
		int m = cashflowOut.length;

		//Sums in solutions
		OptimalValue[][] optimalValues =new OptimalValue[n+1][m+1] ;

		//We need to treat the case where there are no values added
		optimalValues[0][0] = new OptimalValue();
		optimalValues[0][0].setTotalIn(Integer.MAX_VALUE);
		optimalValues[0][0].setTotalOut(Integer.MAX_VALUE);
		optimalValues[0][0].setDifferenceBetweenSubsets(Integer.MAX_VALUE);
		for (int i = 1; i <= m ; i++)
		{
			optimalValues [ 0 ] [ i ] = new OptimalValue();
			optimalValues [ 0 ] [ i ] .setTotalIn(0);
			int outSum = optimalValues[0][i-1].getTotalOut();
			if ( outSum > cashflowOut [ i - 1 ] )
			{
				outSum = cashflowOut [ i - 1 ] ;
			}
			optimalValues[0][i].setTotalOut(outSum);
			optimalValues[0][i].setDifferenceBetweenSubsets(outSum);
		}
		for (int i = 1; i <= n ; i++) {
			optimalValues[i][0] = new OptimalValue();
			int inSum = optimalValues[i-1][0].getTotalIn();
			optimalValues[i][0].setTotalOut(0);
			if ( cashflowIn [ i - 1 ]  < inSum )
			{
				inSum = cashflowIn [ i - 1 ] ;
			}
			optimalValues[i][0].setDifferenceBetweenSubsets(inSum);
			optimalValues[i][0].setTotalIn(inSum);

			for ( int j = 1 ; j <= m ; j ++ )
			{
				optimalValues[i][j] = new OptimalValue();
				//The cases we do not add elements i and j to either subset
				OptimalValue optimalNorValue = optimalValues [ i - 1 ] [ j - 1 ];
				OptimalValue optimalIValue = optimalValues [ i ] [ j - 1 ] ;
				OptimalValue optimalJValue = optimalValues [ i - 1 ] [ j ] ;

				OptimalValue includingBothValues = new OptimalValue();
				includingBothValues.setTotalIn(optimalNorValue.getTotalIn() + cashflowIn [ i - 1 ] );
				includingBothValues.setTotalOut(optimalNorValue.getTotalOut() + cashflowOut [ j - 1 ] );
				includingBothValues.calculateDifference();

				OptimalValue includingIValue = new OptimalValue();
				includingIValue.setTotalIn(optimalJValue.getTotalIn() + cashflowIn [ i - 1 ] ) ;
				includingIValue.setTotalOut(optimalJValue.getTotalOut());
				includingIValue.calculateDifference();

				OptimalValue includingJValue = new OptimalValue();
				includingJValue.setTotalIn(optimalIValue.getTotalIn());
				includingJValue.setTotalOut(optimalIValue.getTotalOut() + cashflowOut [ j - 1 ] );
				includingJValue.calculateDifference();


				List<OptimalValue> candidates = new ArrayList<>();
				candidates.add(optimalNorValue);
				candidates.add(optimalIValue);
				candidates.add(optimalJValue);
				candidates.add(includingBothValues);
				candidates.add(includingIValue);
				candidates.add(includingJValue);

				OptimalValue optimalValue = candidates.get(0);

				for (OptimalValue candidate:candidates)
				{
					if ( optimalValue.compareTo(candidate) > 0 )
					{
						optimalValue = candidate ;
					}
				}

				optimalValues [ i ] [ j ] = optimalValue ;
			}
		}

		return optimalValues [ n ]  [ m ] .getDifferenceBetweenSubsets() ;
	}
	public static void printMatrix( OptimalValue[][] matrix )
	{
		int n = matrix.length ;
		int m = matrix[0].length;
		for (int i = 0; i < n  ; i++) {
			for (int j = 0; j < m ; j++) {
				System.out.println( matrix [ i ] [ j ].getTotalIn() + " " + matrix [ i ] [ j ] .getTotalOut() + " " + matrix[i][j].getDifferenceBetweenSubsets());
			}
		}
	}
}
class OptimalValue implements Comparable<OptimalValue>
{
	private int totalIn ;
	private int totalOut ;

	public int getDifferenceBetweenSubsets() {
		return differenceBetweenSubsets;
	}

	public void setDifferenceBetweenSubsets(int differenceBetweenSubsets) {
		this.differenceBetweenSubsets = differenceBetweenSubsets;
	}
	public int calculateDifference()
	{
		this.differenceBetweenSubsets = Math.abs(totalIn-totalOut);
		return differenceBetweenSubsets;
	}
	private int differenceBetweenSubsets;

	public int getTotalIn() {
		return totalIn;
	}

	public void setTotalIn(int totalIn) {
		this.totalIn = totalIn;
	}

	public int getTotalOut() {
		return totalOut;
	}

	public void setTotalOut(int totalOut) {
		this.totalOut = totalOut;
	}

	@Override
	public int compareTo(OptimalValue optimalValue) {
		return this.differenceBetweenSubsets - optimalValue.getDifferenceBetweenSubsets() ;
	}
}