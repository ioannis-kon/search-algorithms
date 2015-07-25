package basicGraph.search;

import java.util.Comparator;

/**
 *
 * @author Kontopoulos Ioannis
 * This class is used for scalability purposes
 * Every new heuristics class used for 
 * evaluating a route must extend RouteHeuristics
 */
public class RouteHeuristics implements Comparator<Route> {

    @Override
    public int compare(Route o1, Route o2) {
        throw new UnsupportedOperationException("Every new heuristic function for the route must extend this class.");
    }
    
}
