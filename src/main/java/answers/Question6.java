package answers;


import helpers.Map;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Question6 {
    public static int shortestServerRoute(int numServers, int targetServer, int[][] times) {
        Map graph = new Map();
        graph.setArcs(times);
        graph.setNumServers(numServers);
        graph.setTarget(targetServer);
        return bellmanFord(graph);
    }
    private static int bellmanFord(Map graph)
    {
        QueueSet<Integer> queueSet = new QueueSet<>();
        int n = graph.getNumServers();
        int k = graph.getTarget();
        int[] bestDistances = new int[n];
        int[][] costs = graph.getArcs();
        initializeWithINFValue(bestDistances,costs [ 0 ] [ k ]);
        bestDistances [ 0 ] = 0 ;
        queueSet.add(0);
        while( ! queueSet.isEmpty() )
        {
            int candidateServer = queueSet.pollFirst();
            for (int dest = 0; dest < n ; dest ++) {
                if ( candidateServer != dest )
                {
                    //It does not make any sense to go further than the necessary distance to reach node k
                    bestDistances [ dest ] = Math.min(bestDistances [ k ] ,bestDistances[dest] );

                    int cost = costs [ candidateServer ] [ dest ] ;
                    int distanceFromSource = bestDistances [ candidateServer ] ;
                    int bestDistanceToDest = bestDistances [ dest ] ;

                    if ( bestDistanceToDest - cost > distanceFromSource   )
                    {
                        bestDistances [ dest ] = distanceFromSource + cost ;
                        queueSet.add(dest);
                    }

                }
            }
        }
        return bestDistances[k] ;
    }
    private static void initializeWithINFValue ( int[] array , int INF )
    {
        int n = array.length ;
        for (int i = 0; i < n ; i++)
        {
            array [ i ] = INF ;
        }
    }



}
class QueueSet<T>
{
    Queue<T> queue;
    Set<T> inQueue;
    public QueueSet()
    {
        queue = new LinkedList<>();
        inQueue = new HashSet<>();

    }
    public void add(T value )
    {
        if ( ! inQueue.contains(value) )
        {
            queue.add(value);
            inQueue.add(value);
        }
    }
    public T pollFirst()
    {
        T firstElement = queue.poll();
        inQueue.remove(firstElement);
        return firstElement;
    }
    public boolean isEmpty()
    {
        return queue.isEmpty();
    }
}