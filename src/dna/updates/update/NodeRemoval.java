package dna.updates.update;

import dna.graph.Graph;
import dna.graph.IElement;
import dna.graph.edges.DirectedEdge;
import dna.graph.edges.Edge;
import dna.graph.edges.UndirectedEdge;
import dna.graph.nodes.DirectedNode;
import dna.graph.nodes.INode;
import dna.graph.nodes.Node;
import dna.graph.nodes.UndirectedNode;
import dna.util.Log;

public class NodeRemoval extends NodeUpdate {

	public NodeRemoval(INode node) {
		super(UpdateType.NODE_REMOVAL, node);
	}

	@Override
	public boolean apply_(Graph g) {
		boolean success = true;
		if (this.node instanceof DirectedNode) {
			DirectedNode node = (DirectedNode) this.node;
			
			for ( IElement n: node.getNeighbors()) {
				success &= ((DirectedNode)n).removeNeighbor(node);
			}
		} else if (this.node instanceof UndirectedNode) {
			UndirectedNode node = (UndirectedNode) this.node;
			for (IElement eTemp : node.getEdges()) {
				UndirectedEdge e = (UndirectedEdge) eTemp;
				success &= e.getDifferingNode(node).removeEdge(e);
				success &= g.removeEdge(e);
			}
		} else {
			Log.error("attempting to remove unsupported node type "
					+ this.node.getClass());
			return false;
		}
		success &= g.removeNode((Node) this.node);
		return success;
	}

}
