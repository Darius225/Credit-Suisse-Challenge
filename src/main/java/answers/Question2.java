package answers;

import java.util.ArrayList;
import java.util.Arrays;

public class Question2 {

	public static int equallyBalancedCashFlow(int[] cashflowIn, int[] cashflowOut) {

		//Sort the arrays as we intend to use a greedy approach
		Arrays.sort(cashflowIn);
		Arrays.sort(cashflowOut);

		//We can choose one subset to be empty
		int ans = Math.min( cashflowIn [ 0 ] , cashflowOut [ 0 ] ) ;

		int n = cashflowIn.length;
		int m = cashflowOut.length;

		int inSum = cashflowIn [ 0 ] ;
		int outSum = cashflowOut [ 0 ] ;

		for (int i = 1 , j = 1 ; i < n && j < m ; )
		{
			if ( inSum > outSum )
			{
				if ( inSum > cashflowIn [ i ] && cashflowIn [ i ] > outSum )
				{
					inSum = cashflowIn [ i ] ;
					i++;
				}
				else
				{
					outSum += cashflowOut [ j ];
					j ++ ;
				}
			}
			else if ( outSum > inSum )
			{
				if ( outSum > cashflowOut [ j ]  && cashflowOut [ j ] > inSum )
				{
					outSum = cashflowOut [ j ] ;
					j ++ ;
				}
				else
				{
					inSum += cashflowIn [ i ] ;
					i ++ ;
				}
			}
			ans = Math.min(ans,Math.abs(inSum - outSum ) ) ;
		}
		return ans;
	}

}
