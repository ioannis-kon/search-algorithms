package basicGraph.search;

import basicGraph.Graph;
import basicGraph.Neighbor;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;

/**
 *
 * @author Kontopoulos Ioannis
 */
public class BreadthFirst implements SearchAlgorithms {
    
    //this list is used for backtracking and it stores the parent vertices
    private ArrayList<Integer> parents;
    //flag that notifies if the value is found
    private boolean found = false;
    
    /**
     * Function that initializes breadth-first search
     * @param g the graph we apply the search on
     * @param start name of starting vertex
     * @param goal name of goal vertex
     */
    @Override
    public void traverse(Graph g, String start, String goal) {
        int index = g.indexForName(start);
        parents = new ArrayList();
        boolean visited[] = new boolean[g.getVertices().size()];
        System.out.println("====================");
        System.out.println("Breadth-First Search Traversing...");
        System.out.println("====================");
        traverse(index, goal, visited, g);
        backtrackPath(g, g.indexForName(goal), goal);
    }
    
    /**
     * Function that implements breadth-first search algorithm
     * @param v the starting point of the algorithm
     * @param goal vertex to be found
     * @param visited an array that holds the visited nodes
     * @param g the graph we apply the search on
     */
    private void traverse (int v, String goal, boolean[] visited, Graph g) {
        //Create a Queue
        Queue parentsQueue = new ArrayDeque();
        //mark the vertex as visited
        visited[v] = true;
        System.out.println("Visiting : " + g.getVertices().get(v).getName());
        if (g.getVertices().get(v).getName().equals(goal)) {
            found = true;
            return;
        }
        //add parent to the queue
        parentsQueue.add(v);
        //loop while there are items in the queue
        while(!parentsQueue.isEmpty()) {
            //hold the parent vertex
            int parentVertex = Integer.parseInt(parentsQueue.remove().toString());
            //loop through every neighbor or child of parent vertex
            for (Neighbor nbr = g.getVertices().get(parentVertex).adjList; nbr != null; nbr = nbr.next) {
                if (!visited[nbr.vertexNum]) {
                    System.out.println(g.getVertices().get(parentVertex).getName() + " --> " + g.getVertices().get(nbr.vertexNum).getName());
                    //mark current vertex as visited
                    visited[nbr.vertexNum] = true;
                    parents.add(parentVertex);
                    System.out.println("Visiting : " + g.getVertices().get(nbr.vertexNum).getName());
                    if (g.getVertices().get(nbr.vertexNum).getName().equals(goal)) {
                        found = true;
                        return;
                    }
                    //add next parent to the queue
                    parentsQueue.add(nbr.vertexNum);
                }
            }
            
        }
    }
    
    /**
     * Function that backtracks the path from root to goal state
     * and prints it
     * @param g the graph we backtrack the path
     * @param current vertex number of vertex we examine
     * @param goal the goal state
     */
    private void backtrackPath(Graph g, int current, String goal) {
        ArrayList<Integer> pathToGoal = new ArrayList();
        System.out.println("====================");
        System.out.println("Search Results");
        System.out.println("====================");
        System.out.println("Goal State : " + goal);
        if(!found) {
            System.out.println("Value not found!");
        }
        else {
            //add the goal state to path
            pathToGoal.add(current);
            //reverse iterate the list
            for (int i=parents.size()-1;i>-1;i--) {
                //find the parent and add to the list
                if(!pathToGoal.contains(parents.get(i)) && g.isNeighbor(parents.get(i), current)) {
                    pathToGoal.add(parents.get(i));
                    //now the current vertex is the parent of the previous vertex
                    current = parents.get(i);
                }
            }
            print(g, pathToGoal);
        }
    }
    
    /**
     * Function that prints the path
     * @param g the graph which contains the path
     * @param path the path it prints
     */
    private void print(Graph g, ArrayList<Integer> path) {
        System.out.println("Path found from source to goal state :");
        for (int i=path.size()-1;i>-1;i--) {
            System.out.println(g.getVertices().get(path.get(i)).getName());
        }
    }
    
}
