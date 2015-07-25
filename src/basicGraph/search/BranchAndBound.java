package basicGraph.search;

import basicGraph.Graph;
import basicGraph.Neighbor;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author Kontopoulos Ioannis
 */
public class BranchAndBound implements SearchAlgorithms {

    //flag that notifies if the value is found
    private boolean found = false;
    //this list holds the final path found by the algorithm
    ArrayList<String> path;
    //this priority queue is used for the search algorithm
    private PriorityQueue<Route> priorityQueue;
    
    /**
     * Function that initializes Branch and Bound search
     * @param g the graph we apply the search on
     * @param start name of starting vertex
     * @param goal name of goal vertex
     */
    @Override
    public void traverse(Graph g, String start, String goal) {
        int index = g.indexForName(start);
        boolean visited[] = new boolean[g.getVertices().size()];
        System.out.println("====================");
        System.out.println("Branch And Bound Search Traversing...");
        System.out.println("====================");
        traverse(index, goal, visited, g);
        System.out.println("====================");
        System.out.println("Search Results");
        System.out.println("====================");
        System.out.println("Goal State : " + goal);
        if(!found) {
            System.out.println("Value not found!");
        }
        else {
            printRoute(g);
        }
    }
    
    /**
     * Function that implements Branch and Bound search algorithm
     * @param v the starting point of the algorithm
     * @param goal vertex to be found
     * @param visited an array that holds the visited nodes
     * @param g the graph we apply the search on
     */
    private void traverse(int v, String goal, boolean[] visited, Graph g) {
        //create a priority queue
        priorityQueue = new PriorityQueue(g.getVertices().size(), new BnBHeuristics());
        //create a new route
        Route route = new Route();
        //add the first vertex
        route.add(g.getVertices().get(v));
        //mark current vertex as visited
        visited[v] = true;
        System.out.println("Evaluating Route : " + route.printRoute());
        if (g.getVertices().get(v).getName().equals(goal)) {
            found = true;
            path = route.toList();
            return;
        }
        //add route to priority queue
        priorityQueue.add(route);
        //loop while there are routes in the queue
        while (!priorityQueue.isEmpty()) {
            //get best route so far
            Route r = priorityQueue.remove();
            //expand route and create new routes
            for (Neighbor nbr = r.getLastVertex().adjList; nbr != null; nbr = nbr.next) {
                if(!visited[nbr.vertexNum]) {
                    Route newRoute = new Route();
                    //copy the old one
                    newRoute.copy(r.getRoute());
                    //build new route
                    newRoute.add(g.getVertices().get(nbr.vertexNum));
                    System.out.println("Evaluating Route : " + newRoute.printRoute());
                    if (g.getVertices().get(nbr.vertexNum).getName().equals(goal)) {
                        found = true;
                        path = newRoute.toList();
                        return;
                    }
                    //add route to priority queue
                    priorityQueue.add(newRoute);
                    //mark current vertex as visited
                    visited[nbr.vertexNum] = true;
                }
            }
        }
    }
    
    /**
     * Function that prints the optimal route
     * @param g the graph which contains the path
     */
    private void printRoute(Graph g) {
        System.out.println("Path found from source to goal state :");
        for (String v : path) {
            System.out.println(v);
        }
    }
    
}
