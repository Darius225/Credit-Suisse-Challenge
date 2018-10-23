package answers;


import helpers.Map;

import java.util.Collection;
import java.util.PriorityQueue;

public class Question6 {
    public static int shortestServerRoute(int numServers, int targetServer, int[][] times) {
        Map graph = new Map();
        graph.setArcs(times);
        graph.setNumServers(numServers);
        graph.setTarget(targetServer);
        return dijkstra(graph);
    }
    private static int dijkstra(Map graph)
    {
        int n = graph.getNumServers();
        int k = graph.getTarget();
        int[][] costs = graph.getArcs();
        Server[] serverList = new Server[n];
        PriorityQueue<Server> servers = new PriorityQueue<>(n);
        initializeServers(serverList,costs[0][k]);
        addInQueue(serverList,servers);
        while( ! servers.isEmpty() )
        {
            Server server = servers.poll();
            int candidateServer = server.getNumber();
            int distanceFromSource = server.getDistance();
            for (int dest = 0; dest < n ; dest ++) {
                if ( candidateServer != dest )
                {
                    int lastDistance = serverList[dest].getDistance();
                    int cost = costs [ candidateServer ] [ dest ] ;

                    if ( lastDistance - cost > distanceFromSource   )
                    {
                        servers.remove(serverList[dest]);

                        /**
                         * We are only interested to minimise the distance to kth server
                         * so if a path already exceeds that value is no longer a valid
                         * candidate
                         */

                        if ( distanceFromSource + cost < serverList [ k ] .getDistance() )
                        {
                            serverList[dest].setDistance(distanceFromSource  + cost );
                            servers.add(serverList[dest]);
                        }
                    }

                }
            }
        }
        return serverList[k].getDistance() ;
    }
    private static void initializeServers ( Server[] servers , int value )
    {
        int n = servers.length ;
        for (int i = 1; i < n ; i++)
        {
            servers [ i ] = new Server(value , i ) ;
        }
        servers [ 0 ] = new Server( 0 , 0 ) ;
    }
    private static void addInQueue(Server[] serverList , PriorityQueue<Server> serversQueue )
    {
        for (Server server:serverList)
        {
            serversQueue.add(server);
        }
    }
    private static void displayServers(Collection<Server> servers )
    {
        for (Server server:servers)
        {
            System.out.println(server);
        }
    }


}
class Server implements Comparable<Server>
{
    private int distance ;
    private int number ;

    public Server(int distance, int number)
    {
        this.distance = distance ;
        this.number = number ;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }


    @Override
    public int compareTo(Server server) {
        return this.distance - server.getDistance();
    }
    @Override
    public String toString()
    {
        return "The node is " + number + " and the distance: " + distance;
    }
}