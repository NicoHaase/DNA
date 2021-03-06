package dna.graph.datastructures;

import java.util.Collection;
import java.util.Hashtable;
import java.util.Iterator;

import dna.graph.IElement;
import dna.graph.edges.Edge;
import dna.graph.nodes.Node;
import dna.profiler.complexity.Complexity;
import dna.profiler.complexity.ComplexityType.Base;
import dna.util.Rand;

/**
 * Data structure to store IElements in a hashtable
 * 
 * @author Nico
 * 
 */
public class DHashTable extends DataStructureReadable implements
		INodeListDatastructureReadable, IEdgeListDatastructureReadable {

	private Hashtable<String, IElement> list;

	private int maxNodeIndex;

	public DHashTable(Class<? extends IElement> dT) {
		this.init(dT, defaultSize);
	}

	@Override
	public void init(Class<? extends IElement> dT, int initialSize) {
		this.dataType = dT;
		this.list = new Hashtable<String, IElement>(initialSize);
		this.maxNodeIndex = -1;
	}

	public boolean add(IElement element) {
		if (element instanceof Node)
			return this.add((Node) element);
		if (element instanceof Edge)
			return this.add((Edge) element);
		throw new RuntimeException("Can't handle element of type "
				+ element.getClass() + " here");
	}

	public boolean add(Node element) {
		super.canAdd(element);

		if (this.list.get(element) == null) {
			this.list.put(Integer.toString(element.getIndex()), element);
			if (element.getIndex() > this.maxNodeIndex) {
				this.maxNodeIndex = element.getIndex();
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean add(Edge element) {
		super.canAdd(element);

		if (this.list.get(element) == null) {
			this.list.put(Integer.toString(element.hashCode()), element);
			return true;
		}
		return false;
	}

	@Override
	public boolean contains(IElement element) {
		if (element instanceof Node)
			return this.contains((Node) element);
		if (element instanceof Edge)
			return this.contains((Edge) element);
		throw new RuntimeException("Can't handle element of type "
				+ element.getClass() + " here");
	}

	@Override
	public boolean contains(Node element) {
		return list.containsValue(element);
	}

	@Override
	public boolean contains(Edge element) {
		return list.containsKey(Integer.toString(element.hashCode()));
	}

	@Override
	public boolean remove(IElement element) {
		if (element instanceof Node)
			return this.remove((Node) element);
		if (element instanceof Edge)
			return this.remove((Edge) element);
		throw new RuntimeException("Can't handle element of type "
				+ element.getClass() + " here");
	}

	@Override
	public boolean remove(Node element) {
		if (this.list.remove(Integer.toString(element.getIndex())) == null) {
			return false;
		}
		if (element.getIndex() == this.maxNodeIndex) {
			int max = this.maxNodeIndex - 1;
			while (!this.list.containsKey(Integer.toString(max)) && max >= 0) {
				max--;
			}
			this.maxNodeIndex = max;
		}
		return true;
	}

	@Override
	public boolean remove(Edge element) {
		if (this.list.remove(Integer.toString(element.hashCode())) == null) {
			return false;
		}
		return true;
	}

	@Override
	public int size() {
		return list.size();
	}

	@Override
	public IElement getRandom() {
		int index = Rand.rand.nextInt(this.list.size());
		int counter = 0;
		for (IElement element : this.list.values()) {
			if (counter == index) {
				return element;
			}
			counter++;
		}
		return null;
	}

	@Override
	public Collection<IElement> getElements() {
		return this.list.values();
	}

	@Override
	protected Iterator<IElement> iterator_() {
		return this.list.values().iterator();
	}

	@Override
	public Node get(int index) {
		return (Node) this.list.get(Integer.toString(index));
	}

	@Override
	public Edge get(Edge element) {
		return (Edge) this.list.get(Integer.toString(element.hashCode()));
	}

	@Override
	public int getMaxNodeIndex() {
		return this.maxNodeIndex;
	}

	public static Complexity getComplexity(Class<? extends IElement> dt,
			AccessType access, Base base) {
		throw new RuntimeException("Not supported yet");
	}

}
