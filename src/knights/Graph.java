package knights;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private final String LINE_SEPARATOR = System.getProperty("line.separator");
	private final boolean TEST;
	private final String TEST_PREFIX = "TEST: Graph.";

	List<Edge> edges;
	List<Position> L;
	List<Position> R;
	List<Position> IS;

	public Graph(List<Position> L, List<Position> R, boolean testing) {
		this.IS = new ArrayList<Position>();
		this.L = new ArrayList<Position>();
		this.R = new ArrayList<Position>();
		this.L.addAll(L);
		this.R.addAll(R);
		this.TEST = testing;
		initLR_edges();
	}

	private void initLR_edges() {
		// also finds orphans in L
		this.edges = new ArrayList<Edge>();
		int L_size = this.L.size();

		List<Boolean> r_orphans = new ArrayList<Boolean>(this.R.size());
		for (int i = 0; i < r_orphans.size(); i++) {
			r_orphans.set(i, true);
		}

		for (int i = 0; i < L_size; i++) {
			Knight temp = new Knight(this.L.get(i));
			ArrayList<Position> R_connection = temp.getThreats();

			boolean orphan = true;
			for (Position p : R_connection) {
				if (this.R.contains(p)) {
					this.edges.add(new Edge(i, this.R.indexOf(p), 0));
					orphan = false;

					int r_index = this.R.indexOf(p);
					r_orphans.set(r_index, false);
				}
			}

			if (orphan) {
				this.IS.add(this.L.get(i));
				this.L.remove(i);
				L_size--;
			}
		}

		for (int i = 0; i < r_orphans.size(); i++) {
			if (r_orphans.get(i)) {
				this.IS.add(this.R.get(i));
				this.R.remove(i);
				r_orphans.remove(i);
			}
		}

	}

	public List<Position> findIS() {
		boolean take_nodes_from_L = (this.L.size() >= this.R.size());

		while (this.edges.size() > 0) {
			Edge e = this.edges.get(0);
			int u = e.getStart();
			int v = e.getEnd();

			if (take_nodes_from_L) {
				this.IS.add(this.L.get(u));
				if (this.TEST) {
					System.out.println("TEST: added node " + this.L.get(u)
							+ " to IS");
				}
			} else {
				this.IS.add(this.R.get(v));
				if (this.TEST) {
					System.out.println("TEST: added node " + this.R.get(v)
							+ " to IS");
				}
			}

			this.edges.remove(0);
			if (this.TEST) {
				System.out.println("TEST: removed edge " + e);
			}
		}

		return this.IS;
	}

	public String dispConnections() {
		String returnStr = this.LINE_SEPARATOR;
		returnStr += this.LINE_SEPARATOR;

		for (Edge e : this.edges) {
			returnStr += e.getStart() + " -> " + e.getEnd();
			returnStr += this.LINE_SEPARATOR;
		}

		return returnStr;
	}
}
