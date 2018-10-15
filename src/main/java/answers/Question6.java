package answers;

import helpers.Map;

import java.util.LinkedList;
import java.util.Queue;

public class Question6 {
    private static final int INF = Integer.MAX_VALUE ;
	public static int shortestServerRoute(int numServers, int targetServer, int[][] times) {
		Map graph = new Map();
		graph.setArcs(times);
		graph.setNumServers(targetServer);
		graph.setTarget(numServers);
		return bellmanFord(graph);
	}
	public static int bellmanFord(Map graph)
	{
		Queue<Integer> servers = new LinkedList<>();
		int n = graph.getNumServers();
		int k = graph.getTarget();
		int[] bestDistances = new int[n];
		initializeWithINFValue(bestDistances);
		int[][] costs = graph.getArcs();
		bestDistances [ 0 ] = 0 ;
		servers.add(0);
		while( ! servers.isEmpty() )
		{
			int candidateServer = ((LinkedList<Integer>) servers).pollFirst();
			int distanceFromSource = bestDistances [ candidateServer ] ;
			for (int dest = 0; dest < n ; dest ++) {
				if ( candidateServer != dest )
				{
					int cost = costs [ candidateServer ] [ dest ] ;
					int bestDistanceToDest = bestDistances [ dest ] ;
					if ( bestDistanceToDest > distanceFromSource + cost )
					{
						bestDistances [ dest ] = distanceFromSource + cost ;
						servers.add(dest);
					}
				}
			}
		}
        return bestDistances[k] ;
	}
	public static void initializeWithINFValue ( int[] array )
	{
		int n = array.length ;
		for (int i = 0; i < n ; i++)
		{
			array [ i ] = INF ;
		}
	}


}
