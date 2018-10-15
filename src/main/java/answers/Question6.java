package answers;

import helpers.Map;

import java.util.LinkedList;
import java.util.Queue;

public class Question6 {
    private static final long INF = Long.MAX_VALUE ;
    public static long shortestServerRoute(int numServers, int targetServer, int[][] times) {
        Map graph = new Map();
        graph.setArcs(times);
        graph.setNumServers(numServers);
        graph.setTarget(targetServer);
        return bellmanFord(graph);
    }
    private static long bellmanFord(Map graph)
    {
        Queue<Integer> servers = new LinkedList<>();
        int n = graph.getNumServers();
        int k = graph.getTarget();
        long[] bestDistances = new long[n];
        initializeWithINFValue(bestDistances);
        int[][] costs = graph.getArcs();
        bestDistances [ 0 ] = 0 ;
        servers.add(0);
        while( ! servers.isEmpty() )
        {
            int candidateServer = ((LinkedList<Integer>) servers).pollFirst();
            long distanceFromSource = bestDistances [ candidateServer ] ;
            for (int dest = 0; dest < n ; dest ++) {
                int cost = costs [ candidateServer ] [ dest ] ;
                long bestDistanceToDest = bestDistances [ dest ] ;
                if ( bestDistanceToDest > distanceFromSource + cost )
                {
                    bestDistances [ dest ] = distanceFromSource + cost ;
                    servers.add(dest);
                }
            }
        }
        return bestDistances[k] ;
    }
    private static void initializeWithINFValue ( long[] array )
    {
        int n = array.length ;
        for (int i = 0; i < n ; i++)
        {
            array [ i ] = INF ;
        }
    }



}
