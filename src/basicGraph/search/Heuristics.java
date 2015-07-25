package basicGraph.search;

import basicGraph.Vertex;
import java.util.Comparator;

/**
 *
 * @author Kontopoulos Ioannis
 * This class is used for scalability purposes
 * Every new heuristics class must extend Heuristics
 */
public class Heuristics implements Comparator<Vertex> {

    @Override
    public int compare(Vertex v1, Vertex v2) {
        throw new UnsupportedOperationException("Every new heuristic function must extend this class.");
    }
    
}
