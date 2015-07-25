package basicGraph;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Kontopoulos Ioannis
 */
public class Graph {
    
    //holds all the vertices of the graph
    private final ArrayList<Vertex> vertices;

    public Graph() {
        vertices = new ArrayList();
    }

    /**
     * Getter for the vertices ArrayList
     * @return list of graph vertices
     */
    public ArrayList<Vertex> getVertices() {
        return vertices;
    }

    /**
     * Function that adds a vertex
     * @param name vertex name
     * @param cost vertex path cost
     */
    public void addVertex(String name, int cost) {
        vertices.add(new Vertex(name, cost, null));
    }

    /**
     * Function that creates an edge between
     * two vertices
     * @param vertex1 name for first vertex
     * @param vertex2 name for second vertex
     */
    public void addEdge(String vertex1, String vertex2) {
        int v1 = indexForName(vertex1);
        int v2 = indexForName(vertex2);
        vertices.get(v1).adjList = new Neighbor(v2, vertices.get(v1).adjList);
        vertices.get(v2).adjList = new Neighbor(v1, vertices.get(v2).adjList);
    }

    /**
     * Function that reads vertex name
     * and translate to vertex numbers
     * @param name vertex name
     * @return vertex number
     */
    public int indexForName(String name) {
        for (int v = 0; v < vertices.size(); v++) {
            if (vertices.get(v).getName().equals(name)) {
                return v;
            }
        }
        return -1;
    }
    
    /**
     * Function that resets 
     * to 0 the costs so far
     */
    public void resetCosts() {
        for (Vertex v : vertices) {
            if(v.getCostSoFar() != 0) v.setCostSoFar(0);
        }
    }
    
    /**
     * Function that finds if 
     * current vertex is the parent vertex
     * @param par the parent
     * @param current the current vertex
     * @return true if the parent is found
     */
    public boolean isNeighbor(int par, int current) {
        for (Neighbor nbr = getVertices().get(current).adjList; nbr != null; nbr = nbr.next) {
            if(par == nbr.vertexNum) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Function that initializes a default
     * graph for testing purposes
     */
    public void initializeDefault() {
        addVertex("A", 1);
        addVertex("B", 1);
        addVertex("C", 2);
        addVertex("G", 2);
        addVertex("D", 2);
        addVertex("E", 3);
        addVertex("F", 2);
        addVertex("H", 1);
        addVertex("Z", 1);
        addEdge("A", "B");
        addEdge("A", "C");
        addEdge("B", "E");
        addEdge("B", "F");
        addEdge("C", "G");
        addEdge("C", "D");
        addEdge("E", "H");
        addEdge("E", "Z");
        addEdge("F", "H");
    }
    
    /**
     * Function that reads a maze from file
     * and creates the graph
     * @param filename name of file to be read
     */
    public void readFromFile(String filename) {
        try {
            System.out.println("Reading from file...");
            //this hashmap holds named vertices from file
            HashMap<String, String>  map = new HashMap();
            //integer for row number
            Integer row = -1;
            //read from file
            for (String line : Files.readAllLines(Paths.get(filename))) {
                row++;
                //integer for column number
                Integer col = -1;
                for (String part : line.split("\\s+")) {
                    col++;
                    try {
                        Integer cost = Integer.valueOf(part);
                    //if cost is greater than -1, meaning there is a vertex, add vertex
                        if(cost>-1) {
                            addVertex(row + "-" + col, cost);
                        }
                    }
                    catch (Exception e) {
                        //if there is a named vertex in file, such as S, we store its coordinates and name
                        map.put(row + "-" + col, part);
                        //default cost of a named vertex is 1
                        addVertex(row + "-" + col, 1);
                    }
                }
            }
        createEdges();
        nameEdges(map);
        System.out.println("Graph created!");
        }
        catch (IOException io) {
            System.out.println("File NOT Found : " + io.getMessage());
        }
    }
    
    /**
     * Function that creates edges between 
     * vertices based on their coordinates
     */
    private void createEdges() {
        for (int i=0;i<vertices.size()-1;i++) {
            for (int j = i+1;j<vertices.size();j++) {
                int[] prev = getCoordinates(vertices.get(i).getName());
                int[] current = getCoordinates(vertices.get(j).getName());
                //if row coordinates of both current and prev differ by 1 
                //and col coordinates of both current and prev differ by 0 they are neighbors
                //if col coordinates of both current and prev differ by 1 
                //and row coordinates of both current and prev differ by 0 they are neighbors
                //the above statements are the adjacency rules based on the file, when vertices only differ by 1 line or column
                if((current[0] - 1 == prev[0] && current[1] - prev[1] == 0) || (current[1] - 1 == prev[1] && current[0] - prev[0] == 0)) {
                    addEdge(vertices.get(i).getName(), vertices.get(j).getName());
                }
            }
        }
    }
    
    /**
     * Function that names the vertices properly
     * @param map HashMap with coordinates as key and names as values
     */
    private void nameEdges(HashMap<String, String>  map) {
        //we iterate through the hashmap, and the vertices list, 
        //in order to change the names of the vertices, wherever we have a named vertex
        Iterator<String> keySetIterator = map.keySet().iterator();
        while(keySetIterator.hasNext()){
            String key = keySetIterator.next();
            for (Vertex v : vertices) {
                if (v.getName().equals(key)) {
                    v.setName(map.get(key));
                }
            }
        }
    }
    
    /**
     * Function that extracts coordinates from vertex name
     * @param name name of the vertex
     * @return array with coordinates
     */
    private int[] getCoordinates(String name) {
        String[] parts = name.split("-");
        int[] coordinates = {Integer.parseInt(parts[0]),Integer.parseInt(parts[1])};
        return coordinates;
    }

}
