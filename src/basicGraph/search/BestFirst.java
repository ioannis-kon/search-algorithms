package basicGraph.search;

import basicGraph.Graph;
import basicGraph.Neighbor;
import basicGraph.Vertex;
import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 *
 * @author Kontopoulos Ioannis
 */
public class BestFirst implements SearchAlgorithms {
    
    //flag that notifies if the value is found
    private boolean found = false;
    //this list is used for backtracking and it stores the parent vertices
    private ArrayList<Integer> parents;
    //this priority queue is used for the search algorithm
    private PriorityQueue<Vertex> priorityQueue;

    /**
     * Function that initializes best-first search
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
        System.out.println("Best-First Search Traversing...");
        System.out.println("====================");
        traverse(index, goal, visited, g);
        backtrackPath(g, g.indexForName(goal), goal);
    }
    
    /**
     * Function that implements best-first search algorithm
     * @param v the starting point of the algorithm
     * @param goal vertex to be found
     * @param visited an array that holds the visited nodes
     * @param g the graph we apply the search on
     */
    private void traverse(int v, String goal, boolean[] visited, Graph g) {
        //vertex to be evaluated
        int evaluationVertex;
        //create a priority queue
        priorityQueue = new PriorityQueue(g.getVertices().size(), new BestFirstHeuristics());
        //mark current vertex as visited
        visited[v] = true;
        System.out.println("Evaluating : " + g.getVertices().get(v).getName() + " with heuristic value : h(n) = " + g.getVertices().get(v).getCost());
        if (g.getVertices().get(v).getName().equals(goal)) {
            found = true;
            return;
        }
        //add vertex to priority queue
        priorityQueue.add(g.getVertices().get(v));
        //loop while there are items in the queue
        while (!priorityQueue.isEmpty()) {
            //evaluate vertex with minimum heuristic value
            evaluationVertex = getVertexWithMinimumHeuristicValue(g);
            //loop through every neighbor or child of evaluation vertex
            for (Neighbor nbr = g.getVertices().get(evaluationVertex).adjList; nbr != null; nbr = nbr.next) {
                if(!visited[nbr.vertexNum] && evaluationVertex != nbr.vertexNum) {
                    System.out.println(g.getVertices().get(evaluationVertex).getName() + " --> " + g.getVertices().get(nbr.vertexNum).getName());
                    System.out.println("Evaluating : " + g.getVertices().get(nbr.vertexNum).getName() + " with heuristic value : h(n) = " + g.getVertices().get(nbr.vertexNum).getCost());
                    parents.add(evaluationVertex);
                    if (g.getVertices().get(nbr.vertexNum).getName().equals(goal)) {
                        found = true;
                        return;
                    }
                    //add vertex to priority queue
                    priorityQueue.add(g.getVertices().get(nbr.vertexNum));
                    //mark current vertex as visited
                    visited[nbr.vertexNum] = true;
                }
            }
        }
    }
    
    /**
     * Function that retrieves the vertex with minimum heuristic value
     * from the priority queue
     * @param g the graph
     * @return vertex number
     */
    private int getVertexWithMinimumHeuristicValue(Graph g) {
        Vertex v = priorityQueue.remove();
        for(int i=0;i<g.getVertices().size();i++) {
            if(v.getName().equals(g.getVertices().get(i).getName())) {
                return i;
            }
        }
        return -1;
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
