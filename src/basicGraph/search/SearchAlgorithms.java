package basicGraph.search;

import basicGraph.Graph;

/**
 * 
 * @author Kontopoulos Ioannis
 * This interface is used for scalability purposes
 * Every new search algorithm must implement SearchAlgorithms
 */
public interface SearchAlgorithms {
    
    public abstract void traverse(Graph g, String start, String goal);
    
}
