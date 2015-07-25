package basicGraph;

import basicGraph.search.BranchAndBound;

/**
 *
 * @author Kontopoulos Ioannis
 */
public class Main {

    /**
     * 
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        initializeComponents();
    }
    
    
    /**
     * Function that initializes the algorithms
     * and the graph for testing purposes
     */
    private static void initializeComponents() {
        Graph g = new Graph();
        //g.readFromFile("maze.txt");
        g.initializeDefault();
        //DepthFirst dfs = new DepthFirst();
        //BreadthFirst bfs = new BreadthFirst();
        //dfs.traverse(g, "A", "Z");
        //bfs.traverse(g, "A", "Z");
        //BestFirst bf = new BestFirst();
        //bf.traverse(g, "A", "Z");
        //AStar as = new AStar();
        //as.traverse(g, "A", "Z");
        BranchAndBound BnB = new BranchAndBound();
        BnB.traverse(g, "A", "Z");
    }
    
}
