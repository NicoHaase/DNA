package dna.metrics.apsp.allPairShortestPath;

import dna.updates.batch.Batch;
import dna.updates.update.Update;

public class AllPairShortestPathR extends
		AllPairShortestPath {

	public AllPairShortestPathR() {
		super("AllPairShortestPathR", ApplicationType.Recomputation);

	}

	@Override
	public boolean applyBeforeBatch(Batch b) {
		return false;
	}

	@Override
	public boolean applyAfterBatch(Batch b) {
		return false;
	}

	@Override
	public boolean applyBeforeUpdate(Update u) {
		return false;
	}

	@Override
	public boolean applyAfterUpdate(Update u) {
		return false;
	}
}
