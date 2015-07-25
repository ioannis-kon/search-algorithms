package basicGraph;

/**
 *
 * @author Kontopoulos Ioannis
 */
public class Neighbor {

    //vertex number for graph`s adjacency list
    public int vertexNum;
    //shows the next neighbor
    public Neighbor next;

    public Neighbor(int vnum, Neighbor nbr) {
        this.vertexNum = vnum;
        next = nbr;
    }

}
