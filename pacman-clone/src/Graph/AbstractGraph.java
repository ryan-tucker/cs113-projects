/*
 *  AbstractGraph.java
 *
 *  This class implements the Graph interface but it is still to generic to make as a concrete class.
 *  It adds more features to a generic graph such as number of vertices and whether a graph is directed or not.
 *  Since this graph is still too generic it is labeled as abstract, to be inherited from.
 *
 *  Author:  Victor Vazquez / Ryan Tucker / Carsten Singleton
 *  Version: 1.0
 */
package edu.miracosta.cs113.Graph;
import java.util.Iterator;

public abstract class AbstractGraph implements Graph {

    private boolean directed;
    private int numV;

    /**
     * Default Constructor
     */
    public AbstractGraph() {
        this.directed = false;
        this.numV = 0;
    }

    /**
     * Full Constructor
     *
     * @param numV Number of vertices contained within this Graph
     * @param directed boolean if Graph is directed or not
     */
    public AbstractGraph(int numV, boolean directed) {
        this.numV = numV;
        this.directed = directed;
    }

    @Override
    public boolean isDirected() {
        return directed;
    }

    @Override
    public int getNumV() {
        return numV;
    }

    @Override
    public abstract void insert(Edge edge);

    @Override
    public abstract boolean isEdge(int source, int dest);

    @Override
    public abstract Edge getEdge(int source, int dest);

    @Override
    public abstract Iterator<Edge> edgeIterator(int source);
}
