package Tema_7.Session_10_11;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Titulo: Clase Node
 * 
 * @author Omar Teixeira, UO281847
 * @version 1 abr 2022
 */
public abstract class Node implements Comparable<Node> {
	/**
	 * Atributo depth
	 */
    protected int depth; //Number of moves made so far (is equal to the number of nodes developed) on this branch
    /**
     * Atributo parentID
     */
    protected UUID parentID; //Parent ID for node tracking
    /**
     * Atributo ID
     */
    protected UUID ID; //ID for the node
    /**
     * Atributo heuristicValue
     */
    protected double heuristicValue; //Value of the calculated heuristic

    /**
     * Constructor for Node objects
     */
	public Node() { //Values by default
    	depth = 0; 
    	parentID = null; //It does not have parent unless we say another thing
    	ID = UUID.randomUUID();
	}
	
	/**
	 * Getter for depth
	 * @return The depth variable
	 */
    public int getDepth() { return depth;}
	  
    /**
     * Getter for heuristicValue
     * @return The heuristicValue variable
     */
	public double getHeuristicValue() { return heuristicValue; }
	
	/**
	 * Compares whether two nodes are equal using the ToString method
	 * @param n Another node to be compared with
	 * @return True if there are equal. False otherwise
	 */
    public boolean equals(Node n) {
		return (n.toString().equals(toString()));
	}
    
    /**
     * Getter for parentID
     * @return The parentID variable
     */
    public UUID getParentID() {
    	return parentID;
    }
    
    /**
     * Gets the ID of the node
     * @return ID of the node
     */
    public UUID getID() {
    	return ID;
    }
    
    /**
     * We can have extra information about the problem to prune all the nodes
     * above a specific heuristicValue. By default we know nothing, so we 
     * do not prune anything
     * @return Value of the initial prune limit 
     */
	public int initialValuePruneLimit() {
		return Integer.MAX_VALUE; //Implementation by default
	}
    
	/**
	 * Método compareTo
	 * @param node
	 */
	@Override
	public int compareTo(Node node) { //BRANCHING METHOD
		double totalValue = heuristicValue;
		double totalValueToBeCompared = node.getHeuristicValue();
		
		if (totalValue > totalValueToBeCompared) return 1; //this has less priority (is bigger)
		else if (totalValue == totalValueToBeCompared) return 0; //The same priority
		else return -1; //this has more priority (is smaller)
	}
    
	/**
	 * Método que calcula el valor heuristico
	 */
	public abstract void calculateHeuristicValue();
	
	/**
	 * Método que expande los nodos
	 * @return sol
	 */
	public abstract ArrayList<Node> expand();
	
	/**
	 * Método isSolution
	 * @return true o false
	 */
	public abstract boolean isSolution();
}
