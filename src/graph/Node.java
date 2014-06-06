package graph;

import java.util.ArrayList;

/**
 * Each node stores data and has adjacent nodes.
 * 
 * @author Kieraj Mumick
 *
 * @param <T>
 *            The type of data you would like to store in the node.
 */
public class Node<T> {

	/**
	 * The data to store in the node.
	 */
	private T data;

	/**
	 * The adjacent nodes to this node.
	 */
	private ArrayList<Node<T>> adjacent;

	/**
	 * A unique identifier for this node.
	 */
	private int identifier;

	/**
	 * Stores whether or not this node has been visited or not when searching.
	 */
	private boolean visited;

	/**
	 * The level this node was accessed at.
	 */
	private int level;

	/**
	 * Constructor that creates an empty node. The hash code for the object is
	 * used to create the unique identifier for it.
	 */
	public Node() {
		this.data = null;
		this.identifier = this.hashCode();
		this.adjacent = new ArrayList<Node<T>>();
		this.level = 0;
	}

	/**
	 * Constructor that creates a node with the given data and identifier.
	 * 
	 * @param data
	 *            The data to put in this node.
	 * @param identifer
	 *            The identifier for this node.
	 */
	public Node(T data, int identifer, int level) {
		this.data = data;
		this.identifier = identifer;
		this.adjacent = new ArrayList<Node<T>>();
		this.level = level;
	}

	/**
	 * Constructor that creates a node with the given data. It uses the hash
	 * code of the object to make a unique identifier for it.
	 * 
	 * @param data
	 *            The data to put in this node.
	 */
	public Node(T data, int level) {
		this.data = data;
		this.identifier = this.hashCode();
		this.adjacent = new ArrayList<Node<T>>();
		this.level = level;
	}

	/**
	 * Adds an edge to this node.
	 * 
	 * @param n
	 *            The node to add to an edge.
	 * @return true if the node was successfully added, false otherwise.
	 */
	public boolean addEdge(Node<T> n) {
		return this.adjacent.add(n);
	}

	/**
	 * @return The degree of the node.
	 */
	public int getDegree() {
		return this.adjacent.size();
	}

	/**
	 * Finds out whether or not this node is adjacent to the node <code>
	 * n </code> that was passed in.
	 * 
	 * @param n
	 *            The node you would like to check if <code> this </code> is
	 *            next to.
	 * @return true if <code> n </code> is adjacent to this node, false
	 *         otherwise.
	 */
	public boolean isAdjacentTo(Node<T> n) {
		return this.adjacent.contains(n);
	}

	/**
	 * Finds out whether or not this node is adjacent to the node with the given
	 * <code> id </code> that was passed in.
	 * 
	 * @param id
	 *            The node you would like to check if <code> this </code> is
	 *            next to.
	 * @return true if the node with the identifier <code> id </code> is
	 *         adjacent to to this node, false otherwise.
	 */
	public boolean isAdjacentTo(int id) {
		for (Node<T> n : this.adjacent)
			if (n.getIdentifier() == id)
				return true;
		return false;
	}

	/**
	 * @return the visited
	 */
	public boolean isVisited() {
		return visited;
	}

	/**
	 * @param visited
	 *            the visited to set
	 */
	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	/**
	 * @return the data
	 */
	public T getData() {
		return data;
	}

	/**
	 * @return the identifier
	 */
	public int getIdentifier() {
		return identifier;
	}

	/**
	 * @return the adjacent
	 */
	public ArrayList<Node<T>> getAdjacent() {
		return adjacent;
	}

	@Override
	public String toString() {
		return this.data.toString();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
