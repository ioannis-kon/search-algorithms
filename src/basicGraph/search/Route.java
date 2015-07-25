package basicGraph.search;

import basicGraph.Vertex;
import java.util.ArrayList;

/**
 *
 * @author Kontopoulos Ioannis
 */
public class Route {
    
    //list that contains the vertices of a route
    private ArrayList<Vertex> route;

    public Route() {
        route = new ArrayList();
    }

    /**
     * Getter for route
     * @return ArrayList route
     */
    public ArrayList<Vertex> getRoute() {
        return route;
    }
    
    /**
     * @return the number of vertices in a route
     */
    public int length() {
        return this.route.size();
    }
    
    /**
     * @return list containing the names of the vertices
     */
    public ArrayList<String> toList() {
        ArrayList<String> path = new ArrayList();
        for (Vertex v : this.route) {
            path.add(v.getName());
        }
        return path;
    }
    
    /**
     * @return the cost of the route
     */
    public int getRouteCost() {
        int cost = 0;
        for (Vertex v : this.route) {
            cost+=v.getCost();
        }
        return cost;
    }
    
    /**
     * Function that adds a vertex to route
     * @param v vertex to be added
     */
    public void add(Vertex v) {
        this.route.add(v);
    }
    
    /**
     * @return the last vertex of the route
     */
    public Vertex getLastVertex() {
        return this.route.get(this.route.size() - 1);
    }
    
    /**
     * Function that copies 
     * the old route to new route
     * @param r old route
     */
    public void copy(ArrayList<Vertex> r) {
        this.route = new ArrayList(r);
    }
    
    /**
     * @return the printed route
     */
    public String printRoute() {
        String r ="";
        for (Vertex v : this.route) {
            r+=" --> " + v.getName();
        }
        return r;
    }
    
}
