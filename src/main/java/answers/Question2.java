package answers;

import java.util.HashSet;
import java.util.Set;

import java.util.HashSet;
import java.util.Set;

public class Question2 {
	public static int equallyBalancedCashFlow(int[] cashflowIn, int[] cashflowOut)
	{


		Set<Integer> aValues = createSums ( cashflowIn ) ;
		Set<Integer> bValues = createSums ( cashflowOut ) ;

		//the difference margin,howewer this will immediately get low if the the input respects the constraints
		int ans = 1000 ;

		//We need to check both cases
		ans = checkCase(aValues,bValues,ans);
		ans = checkCase(bValues,aValues,ans);

		return ans ;
	}

	//Method useful for finding if the values in set b are smaller then a by a difference
	public static int checkCase(Set<Integer> a , Set<Integer> b , int ans )
	{
		for (int i = 1; i <= 10000  && ans != 0; i++) {
			if (a.contains(i))
			{
				boolean found = false ;
				for (int j = 0; j < ans && found == false ; j++) {
					if ( b.contains( i - j))
					{
						found = true ;
						ans = j ;
					}
				}
			}
		}
		return ans ;
	}

	//We will maintain a set of obtainable sums
	public static Set<Integer> createSums(int[] cashFlow ) {
		Set<Integer> sums = new HashSet<>();
		int n = cashFlow.length ;
		sums.add(0);
		for (int i = 0; i < n ; i++) {
			Set<Integer> stepSums = new HashSet<>();
			for (Integer sum:sums
			)
			{
				//Trying to exploit that all the numbers are between 1 and 100
				if ( sum + cashFlow [ i ] <= 10000 ) {
					stepSums.add(sum + cashFlow[i]);
				}
			}
			sums.addAll(stepSums);
		}
		return sums ;
	}
}