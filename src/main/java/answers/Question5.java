package answers;

public class Question5 {
	private static int offset ;
	public static int shareExchange(int[] allowedAllocations, int totalValue)
	{
		int n = allowedAllocations.length;
		int absoluteTotal = Math.abs(totalValue);
		int[] leastCoins = new int[2*absoluteTotal+1];
		int numWays = 0 ;
		offset = absoluteTotal ;
		for ( int j = -absoluteTotal ; j <= absoluteTotal ; j ++ )
		{
			leastCoins [ transformIndex( j ) ] = Integer.MAX_VALUE ;
		}
		leastCoins[transformIndex(0)] = 0 ;
		for (int i = 0; i < n ; i++) {
			for (int j = -absoluteTotal; j <= absoluteTotal ; j++) {
				if ( Math.abs(j + allowedAllocations [ i ] ) <= absoluteTotal )
				{
					if ( leastCoins [ transformIndex( j + allowedAllocations [ i ] ) ] - 1 > leastCoins [ transformIndex(j) ]  )
					{
						leastCoins [ transformIndex( j + allowedAllocations [ i ] ) ] = leastCoins [ transformIndex( j  ) ] + 1 ;
						if ( transformIndex(j + allowedAllocations [ i ] ) == transformIndex( totalValue ) )
						{
							numWays = 1 ;
						}
					}
					else if ( leastCoins [ transformIndex( j + allowedAllocations [ i ] ) ] -1 == leastCoins [ transformIndex(j) ]  )
					{
						if ( transformIndex(j + allowedAllocations [ i ] ) == transformIndex( totalValue ) )
						{
							numWays ++ ;
						}
					}
				}
			}
		}
		if ( numWays == 1 )
		{
			return leastCoins [ transformIndex(totalValue) ] ;
		}
		return 0;
	}
	public static int transformIndex (  int index  )
	{
		return index + offset ;
	}

}
