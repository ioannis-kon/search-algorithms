package basicGraph.search;

import basicGraph.Graph;
import basicGraph.Neighbor;
import java.util.ArrayList;

/**
 *
 * @author Kontopoulos Ioannis
 */
public class DepthFirst implements SearchAlgorithms {
    
    //this list is used for backtracking
    private ArrayList<Integer> pathTraversed;
    //flag that notifies if the value is found
    private boolean found = false;

    /** 
     * Function that initializes depth-first search
     * and prints the path found
     * @param g the graph we apply the search on
     * @param start name of starting vertex
     * @param goal name of goal vertex
     */
    @Override
    public void traverse(Graph g, String start, String goal) {
        int index = g.indexForName(start);
        boolean visited[] = new boolean[g.getVertices().size()];
        pathTraversed = new ArrayList();
        System.out.println("====================");
        System.out.println("Depth-First Search Traversing...");
        System.out.println("====================");
        traverse(index, goal, visited, g);
        backtrackPath(g, start, goal);
    }
    
    /**
     * Function that implements
     * recursive depth-first search algorithm
     * @param v the starting point of the algorithm
     * @param goal vertex to be found
     * @param visited an array that holds the visited nodes
     * @param g the graph we apply the search on
     */
    private void traverse(int v, String goal, boolean[] visited, Graph g) {
        //if the goal has been found stop traversing
        if(found) return;
        //mark the vertex as visited
        visited[v] = true;
        if (g.getVertices().get(v).getName().equals(goal)) {
            //if we find the goal state notify
            found = true;
            pathTraversed.add(v);
        }
        System.out.println("Visiting : " + g.getVertices().get(v).getName());
        //loop through every neighbor or child of current vertex
        for (Neighbor nbr = g.getVertices().get(v).adjList; nbr != null; nbr = nbr.next) {
            //if the current node has not been visited
            if (!visited[nbr.vertexNum]) {
                //this if-statement is used, so when we find our goal we do not print the next edge to visit
                if(!found) {
                    System.out.println(g.getVertices().get(v).getName() + " --> " + g.getVertices().get(nbr.vertexNum).getName());
                }
                pathTraversed.add(v);
                //now traverse again starting from the current vertex
                traverse(nbr.vertexNum, goal, visited, g);
            }
        }
    }
    
    /** 
     * Function that backtracks the path from root to goal state
     * and prints it
     * @param g the graph we backtrack the path
     * @param root the root vertex
     * @param goal vertex to be found
     */
    private void backtrackPath(Graph g, String root, String goal) {
        System.out.println("====================");
        System.out.println("Search Results");
        System.out.println("====================");
        System.out.println("Goal State : " + goal);
        if(!found) {
            System.out.println("Value not found!");
        }
        else {
            ArrayList<Integer> pathToGoal = trim(g, root, goal);
            print(g, pathToGoal);
        }
    }
    
    /**
     * Function that trims the pathTraversed-list
     * because it contains all the traversed vertices
     * we need the ones from goal to root
     * @param g the graph we backtrack the path
     * @param root the root vertex
     * @param goal vertex to be found
     * @return the trimmed list
     */
    private ArrayList<Integer> trim(Graph g, String root, String goal) {
        ArrayList<Integer> pathToGoal = new ArrayList();
       // ArrayList<Integer> trimmedPath = new ArrayList();
        boolean located = false;
        //reverse iterate the list
        for(int i=pathTraversed.size()-1;i>-1;i--) {
            //if the goal has already been found start adding to the list
            if(located) {
                //check if we already added it in the list cause of duplicates
                pathToGoal = addDistinct(pathToGoal, i);
                //when we find the root, stop the process
                if(g.getVertices().get(pathTraversed.get(i)).getName().equals(root)) {
                    break;
                }
            }
            //if the goal is found, enable adding to the list
            if(g.getVertices().get(pathTraversed.get(i)).getName().equals(goal)) {
                pathToGoal = addDistinct(pathToGoal, i);
                //notify that we located the goal`s position
                located = true;
            }
        }
        return pathToGoal;
    }
    
    /**
     * Function that adds to path distinct items
     * cause of duplicates in traversed path 
     * @param path the path to goal state
     * @param i index for traversed path
     * @return path
     */
    private ArrayList<Integer> addDistinct(ArrayList<Integer> path, int i) {
        if(!path.contains(pathTraversed.get(i))) {
            path.add(pathTraversed.get(i));
        }
        return path;
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
