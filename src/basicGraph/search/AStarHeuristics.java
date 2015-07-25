package basicGraph.search;

import basicGraph.Vertex;

/**
 *
 * @author Kontopoulos Ioannis
 * This class is used for the priority 
 * queue in A* search algorithm
 */
public class AStarHeuristics extends Heuristics {

    /**
     * Heuristic function that is used in the priority queue
     * @param v1 vertex 1
     * @param v2 vertex 2
     * @return integer based on comparison
     */
    @Override
    public int compare(Vertex v1, Vertex v2) {
        if(v1.getTotalCost() < v2.getTotalCost()) {
            return -1;
        }
        if(v1.getTotalCost() > v2.getTotalCost()) {
            return 1;
        }
        return 0;
    }
    
}
