package basicGraph;

/**
 *
 * @author Kontopoulos Ioannis
 */
public class Vertex {

    //vertex name
    private String name;
    //adjacency list of neighbors for vertex
    public Neighbor adjList;
    
    // integers below are used for heuristic search algorithms
    
    //vertex path cost -> h(n)
    private int cost;
    //cost from source to this vertex -> g(n)
    private int costSoFar = 0;

    public Vertex(String name, int cost, Neighbor neighbors) {
        this.name = name;
        this.cost = cost;
        this.adjList = neighbors;
    }

    /**
     * Getter for the name of the vertex
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for the name of the vertex
     * @param name name of vertex
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter for integer cost - h(n)
     * @return cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * Setter for integer cost
     * @param cost cost of vertex
     */
    public void setCost(int cost) {
        this.cost = cost;
    }
    
    /**
     * Getter for integer costSoFar - g(n)
     * @return costSoFar
     */
    public int getCostSoFar() {
        return costSoFar;
    }

    /**
     * Setter for integer costSoFar
     * @param costSoFar cost from previous vertices to this one
     */
    public void setCostSoFar(int costSoFar) {
        this.costSoFar = costSoFar;
    }
    
    /**
     * Function that returns the 
     * heuristic value for A* search algorithm
     * @return f(n) = g(n) + h(n)
     */
    public int getTotalCost() {
        return costSoFar + cost;
    }

    /**
     * Function that adds to costSoFar
     * @param cost value to be added to costSoFar
     */
    public void addToCostSoFar(int cost) {
        this.costSoFar += cost;
    }

}
