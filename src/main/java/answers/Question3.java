package answers;

import helpers.Edge;

import java.util.*;

public class Question3 {

	static List<Integer> evenLevel ;
	static List<Integer> oddLevel ;
	static Map<Integer, List<Integer> > graph ;
	static Set<Integer> visitedNodes ;
	public static int lowestExposureToExchanges(int numNodes, Edge[] edgeList) {
		visitedNodes = new HashSet<>();
		graph = new HashMap<>();
		int ans = 0 ;
		for ( Edge edge: edgeList )
		{
			int a = edge.getEdgeA();
			int b = edge.getEdgeB();
			addUndirectedEdge(a,b);
		}
		for (int i = 1; i <= numNodes ; i++)
		{
			if ( ! visitedNodes.contains(i) )
			{
				evenLevel = new ArrayList<>();
				oddLevel = new ArrayList<>();
				DFS(i,0);
				ans += Math.abs(evenLevel.size()-oddLevel.size());
			}
		}
		return ans;
	}
	private static void addUndirectedEdge(int a , int b )
	{
		addDirectedEdge(a,b);
		addDirectedEdge(b,a);
	}
	private static void addDirectedEdge(int a , int b)
	{
		List <Integer> neighbours = new ArrayList<>();
		if ( graph.containsKey(a) )
		{
			neighbours = graph.get(a);
		}
		neighbours.add(b) ;
		graph.put(a,neighbours);
	}
	private static void DFS ( int root , int level )
	{
		visitedNodes.add(root);
		if ( level % 2 == 0 )
		{
			evenLevel.add(root);
		}
		else
		{
			oddLevel.add(root);
		}
		List<Integer> neighbours = new ArrayList<>();
		if ( graph.containsKey(root)) {
			neighbours = graph.get(root);
		}
		for ( Integer neighbour:neighbours )
		{
			if ( ! visitedNodes.contains(neighbour) )
			{
				DFS(neighbour,level+1);
			}
		}
	}

}
