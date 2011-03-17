package knights;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	ArrayList<Position> source_edges;
	ArrayList<Position> sink_edges;
	ArrayList<Position> L;
	ArrayList<Position> R;

	public Graph(List<Position> L, List<Position> R) {
		this.L = new ArrayList<Position>();
		this.R = new ArrayList<Position>();
		this.L.addAll(L);
		this.R.addAll(R);

		this.source_edges.addAll(this.L);
		this.sink_edges.addAll(this.R);
	}
}
