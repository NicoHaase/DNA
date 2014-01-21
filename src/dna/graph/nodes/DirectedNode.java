package dna.graph.nodes;

import dna.graph.IElement;
import dna.graph.datastructures.GraphDataStructure;
import dna.graph.datastructures.INodeListDatastructure;
import dna.graph.edges.DirectedEdge;
import dna.graph.edges.Edge;

public class DirectedNode extends Node {
	private INodeListDatastructure neighbors;

	public DirectedNode(int i, GraphDataStructure gds) {
		super(i, gds);
	}

	public DirectedNode(String str, GraphDataStructure gds) {
		super(str, gds);
	}

	public void init() {
		this.neighbors = this.gds.newLocalNodeList();
	}

	@Override
	public boolean hasEdge(Edge eIn) {
		if (!(eIn instanceof DirectedEdge))
			return false;
		DirectedEdge e = (DirectedEdge) eIn;
		return e.getSrc().getIndex() == this.index
				&& this.neighbors.contains(e.getDst())
				|| e.getDst().getIndex() == this.index
				&& this.neighbors.contains(e.getSrc());
	}

	@Override
	public boolean addEdge(Edge eIn) {
		if (!(eIn instanceof DirectedEdge))
			return false;
		DirectedEdge e = (DirectedEdge) eIn;
		if (e.getSrc().getIndex() == this.index) {
			boolean success = !this.neighbors.contains(e.getDst()) && this.neighbors.add(e.getDst());
			return success;
		}
		if (e.getDst().getIndex() == this.index) {
			boolean success = !this.neighbors.contains(e.getSrc()) && this.neighbors.add(e.getSrc());
			return success;
		}
		return false;
	}

	@Override
	public boolean removeEdge(Edge eIn) {
		if (!(eIn instanceof DirectedEdge))
			return false;
		DirectedEdge e = (DirectedEdge) eIn;
		if (e.getSrc().getIndex() == this.index) {
			return this.neighbors.remove(e.getDst());
		}
		if (e.getDst().getIndex() == this.index) {
			return this.neighbors.remove(e.getSrc());
		}
		return false;
	}

	@Override
	public Iterable<IElement> getEdges() {
		throw new RuntimeException("Dont call this");
	}

	public Iterable<IElement> getIncomingEdges() {
		throw new RuntimeException("Dont call this");
	}

	public Iterable<IElement> getOutgoingEdges() {
		throw new RuntimeException("Dont call this");
	}

	public Iterable<IElement> getNeighbors() {
		return this.neighbors;
	}

	public int getNeighborCount() {
		return this.neighbors.size();
	}

	public boolean hasNeighbor(DirectedNode n) {
		return this.neighbors.contains(n);
	}

	public int getDegree() {
		return this.neighbors.size();
	}

	public int getInDegree() {
		throw new RuntimeException("Dont call this");
	}

	public int getOutDegree() {
		throw new RuntimeException("Dont call this");
	}

	public void print() {
		System.out.println(this.toString());
		System.out.println("In: " + this.getIncomingEdges());
		System.out.println("Out: " + this.getOutgoingEdges());
		System.out.println("Neighbors: " + this.getNeighbors());
	}

	public String toString() {
		return super.toString() + " (" + this.neighbors.size() + ")";
	}

	public boolean removeNeighbor(DirectedNode node) {
		return this.neighbors.remove(node);
	}
}
