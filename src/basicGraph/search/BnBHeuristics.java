package basicGraph.search;

/**
 *
 * @author Kontopoulos Ioannis
 * This class is used for the priority 
 * queue in branch and bound search algorithm
 */
public class BnBHeuristics extends RouteHeuristics {
    
    /**
     * Heuristic function that is used in the priority queue
     * @param r1 route1
     * @param r2 route2
     * @return integer based on comparison
     */
    @Override
    public int compare(Route r1, Route r2) {
        if(r1.getRouteCost() < r2.getRouteCost()) {
            return -1;
        }
        if(r1.getRouteCost() > r2.getRouteCost()) {
            return 1;
        }
        return 0;
    }
    
}
