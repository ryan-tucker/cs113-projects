package edu.miracosta.cs113.Graph;

public class Edge {
    private int source;
    private int dest;
    private double weight;

    /**
     * Constructor for unweighted Edge
     *
     * @param source source vertex for an edge
     * @param dest destination vertex for an edge
     */
    public Edge(int source, int dest) {
        this.dest = dest;
        this.source = source;
        this.weight = 1.0;
    }

    /**
     * Constructor for weighted Edge
     *
     * @param source source vertex for an edge
     * @param dest destination vertex for an edge
     * @param weight double 0 <= weight <= 1
     */
    public Edge(int source, int dest, double weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }

    /**
     * Accessor for dest vertex
     *
     * @return int dest vertex
     */
    public int getDest() {
        return dest;
    }

    /**
     * Accessor for source vertex
     *
     * @return int source vertex
     */
    public int getSource() {
        return source;
    }

    /**
     * Accessor for weight
     *
     * @return double weight
     */
    public double getWeight() {
        return weight;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(dest) + Integer.hashCode(source);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        } else {
            Edge other = (Edge)obj;
            return other.source == this.source && other.dest == this.dest;
        }
    }

    @Override
    public String toString() {
        return "Source: " + source+
                " Dest.: " + dest+
                " Weight: " + weight+"\n";
    }
}
