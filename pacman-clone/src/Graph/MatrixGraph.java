/*
 *  MatrixGraph.java
 *
 *  The purposes of this class is to make a matrix representation of a graph.
 *  On top of this there is an inner class iterator that shows all the adjancecies
 *  for a specific vertex.
 *
 *
 *  Author:  Victor Vazquez / Ryan Tucker / Carsten Singleton
 *  Version: 1.0
 */
package edu.miracosta.cs113.Graph;
import edu.miracosta.cs113.Map;
import java.util.*;

public class MatrixGraph extends AbstractGraph {

    private double[][] edges;

    public MatrixGraph(int numV, boolean directed) {
        super(numV, directed);
        if (numV > 0) {
            edges = new double[numV][numV];
        }
        /**
         * Filling in array with Positive Infinitive
         */
        for(int i = 0; i < numV; i++)
        {
            for(int j = 0;j < numV; j++)
            {
                edges[i][j] = Double.POSITIVE_INFINITY;
            }
        }
    }

    /**
     * Constructs a new undirected graph based on an existing Map.
     * Edges are determined by north, south, east, west adjacencies and
     * barriers in the map.
     *
     * @param map Map to be represented as an undirected graph
     */
    public MatrixGraph(Map map) {
        super(map.getRows() * map.getColumns(), false);
        edges = new double[getNumV()][getNumV()];
        boolean isBarrier;
        int currentIndex, upIndex, downIndex, leftIndex, rightIndex;
        for (int i = 0; i < edges.length; i ++) {
            for (int j = 0; j < edges.length; j ++) {
                edges[i][j] = Double.POSITIVE_INFINITY;
            }
        }
        for (int i = 0; i < map.getRows(); i ++) {
            for (int j = 0; j < map.getColumns(); j ++) {
                currentIndex = map.get1DIndex(i,j);
                if (map.getTile(i,j) == Map.BARRIER_TILE) {
                    isBarrier = true;
                } else {
                    isBarrier = false;
                }
                if (j - 1 > -1 && j - 1 < map.getColumns()) {
                    leftIndex = map.get1DIndex(i, j - 1);
                    if (map.getTile(i, j - 1) != Map.BARRIER_TILE && !isBarrier) {
                        insert(new Edge(leftIndex,currentIndex, 1.0));
                    }
                }
                if (j + 1 < map.getColumns()) {
                    rightIndex = map.get1DIndex(i, j + 1);
                    if (map.getTile(i,j + 1) != Map.BARRIER_TILE && !isBarrier) {
                        insert(new Edge(rightIndex, currentIndex, 1.0));
                    }
                }
                if (i - 1 > -1 && i - 1 < map.getRows()) {
                    upIndex = map.get1DIndex(i - 1, j);
                    if (map.getTile(i -1, j) != Map.BARRIER_TILE &&!isBarrier) {
                        insert(new Edge(upIndex,currentIndex, 1.0));
                    }
                }
                if (i + 1 < map.getRows()) {
                    downIndex = map.get1DIndex(i + 1, j);
                    if (map.getTile(i + 1, j) != Map.BARRIER_TILE && !isBarrier) {
                        insert(new Edge(downIndex,currentIndex, 1.0));
                    }
                }
            }
        }
    }

    /**
     * Created to see a visual representation of the matrix
     *
     */
    public void drawGraph()
    {

        System.out.println("Zeros represent Positive Infinity");
        for(int i = 0; i < edges.length;i++)
        {

            for(int j = 0; j < edges.length; j++)
            {
                if(edges[i][j] == Double.POSITIVE_INFINITY)
                {
                    System.out.print("0\t");
                }
                else
                {
                    System.out.print((int)edges[i][j] + "\t");
                }

            }
            System.out.println();
        }
    }

    @Override
    public void insert(Edge edge) {


        int dest = edge.getDest();
        int source = edge.getSource();

        //if dest or source int values go past array bounds
        if(dest >= edges.length || source >= edges.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        edges[source][dest] = edge.getWeight();

        //Code below makes the matrix symmetrical if undirected graph
        if(!isDirected()) {
            edges[dest][source] = edge.getWeight();
        }

    }

    @Override
    public boolean isEdge(int source, int dest) {
        return edges[source][dest] != Double.POSITIVE_INFINITY;
    }

    @Override
    public Edge getEdge(int source, int dest) {

        //if dest or source int values go past array bounds
        if(dest >= edges.length || source >= edges.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return new Edge(source,dest,edges[source][dest]);
    }

    @Override
    public Iterator<Edge> edgeIterator(int source) {
        return new MatrixIterator(source);
    }

    /**
     * Inner class Iterator which checks adjacencies of a single vertex
     *
     */
    private class MatrixIterator implements Iterator<Edge>,Iterable{


        private int vertex;
        private int lastReturned;
        public MatrixIterator(int source){
            vertex = source;
            lastReturned = 0;

        }
        @Override
        public boolean hasNext() {

            while (lastReturned < edges.length)
            {
                if(edges[vertex][lastReturned] != Double.POSITIVE_INFINITY)
                {
                    return true;
                }
                lastReturned++;
            }
            return false;

        }

        @Override
        public Edge next() {
            if(!hasNext())
            {
                throw new NoSuchElementException();
            }
            lastReturned++;
            return new Edge(vertex,lastReturned-1);
        }

        @Override
        public Iterator<Edge> iterator() {
            return this;
        }
    }

    /**
     * Reads the relationships between vertices from a graph
     * @return  All the connections
     */
    public ArrayList<Edge> readConnections()
    {
        if(isDirected())
        {
            return readDirectedConnections();
        }
        else
        {
            return readUndirectedConnections();
        }

    }

    /**
     * Reads the relationships between vertices from a directed graph
     * @return  All the connections
     */
    private ArrayList<Edge> readDirectedConnections()
    {
        ArrayList<Edge> array = new ArrayList<Edge>();
        for(int i = 0; i < edges.length;i++)
        {
            for(int j = 0; j < edges.length;j++)
            {
                if(edges[i][j] > 0)
                {
                    array.add(new Edge(i,j,edges[i][j]));
                }
            }
        }
        return array;
    }
    /**
     * Reads the relationships between vertices from a undirected graph
     * But only reads half of the matrix and creates an array of all the edges
     * @return  half of the connections
     */
    private ArrayList<Edge> readUndirectedConnections()
    {
        ArrayList<Edge> array = new ArrayList<Edge>();
        for(int i = 0; i < edges.length;i++)
        {
            for(int j = 0; j < i;j++)
            {
                if(edges[i][j] > 0)
                {
                    array.add(new Edge(i,j,edges[i][j]));
                }
            }
        }
        return array;
    }

    /**
     * Obtains a single path by combing dijkstra's method and getSingleShortestPath method
     * To make it easier for programmer to code
     * @param start         Stating vertex
     * @param destination   Destination
     * @return              Returns the path in the form of an array
     */
    public int[] getPath(int start,int destination)
    {
        int[] predecesors = new int[getNumV()];
        double[] dist = new double[getNumV()];
        dijkstrasAlgorithm(start,predecesors,dist);
        return getSingleShortestPath(start,destination,predecesors);
    }
    /**
     * This runs dijkstra's algorithm
     * @param start     Staring vertex where all paths will be based from
     * @param pred      An array that stores int references to a vertex from another
     * @param dist      The Total distance from a vertex to the starting vertex
     */
    public void dijkstrasAlgorithm(int start,int[] pred,double[] dist)
    {
        int numV = getNumV();
        HashSet<Integer> vMinuesS = new HashSet<Integer>(numV);
        //Initialize V-S
        for(int i = 0;i<numV;i++ )
        {
            if(i != start)
            {
                vMinuesS.add(i);
            }
        }

        //Initialize pred and dist
        for(Integer vertices:vMinuesS)
        {
            pred[vertices] = start;
            dist[vertices] = getEdge(start,vertices).getWeight();

        }

        //Main loop
        while(vMinuesS.size() != 0)
        {
            //find value u in V-S with the smallest dist[u]
            double minDist = Double.POSITIVE_INFINITY;
            int u = -1;

            for(Integer vertex : vMinuesS)
            {
                if(dist[vertex] < minDist)
                {
                    minDist = dist[vertex];
                    u = vertex;
                }
            }

            if (u == -1) {
                for(Integer vertex : vMinuesS)
                {
                    u = vertex;
                    break;
                }
            }
            //Remove u from v minus
            vMinuesS.remove(u);
            //update the distance
            for(Integer vertex: vMinuesS)
            {
                if(isEdge(u,vertex))
                {
                    double weight = getEdge(u,vertex).getWeight();
                    if(dist[u] + weight < dist[vertex])
                    {
                        dist[vertex] = dist[u] + weight;
                        pred[vertex] = u;
                    }
                }
            }
        }
    }

    /**
     * In order to run this method Dikstras must be ran first to obtain all paths fom a vertex
     * What this method does is simply backtrack dijkstra from the end vertex you want to reach to your current location
     * @param start     Staring point or vertex
     * @param end       The target location where you want to be
     * @param pred      The array that holds the way to get to start to end
     * @return          an array that has the path to get to your single end point
     */
    public int[] getSingleShortestPath(int start, int end, int[] pred)
    {
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(end);
        while(end != start)
        {
            //Pushing te predecessor of the end where you want to go
            //And works backwards
            stack.push(end);
            end = pred[end];
        }
        int[] array = new int[stack.size()];
        int counter = 0;
        while (!stack.isEmpty()) {
            array[counter] = stack.pop();
            counter ++;
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < edges.length;i++)
        {
            for(int j = 0; j < edges.length; j++)
            {
                if(edges[i][j] == Double.POSITIVE_INFINITY)
                {
                    sb.append("X\t");
                }
                else
                {
                    sb.append((int)edges[i][j] + "\t");
                }

            }
            sb.append('\n');
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MatrixGraph that = (MatrixGraph) o;
        return Arrays.equals(edges, that.edges);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(edges);
    }
}
