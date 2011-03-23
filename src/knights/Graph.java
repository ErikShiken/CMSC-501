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
		if (this.TEST) {
			for (int i = 0; i < this.R.size(); i++) {
				System.out.println("TEST: R(" + i + ") -> " + this.R.get(i));
			}
		}
		initLR_edges();
	}

	private void initLR_edges() {
		// also finds orphans in L
		this.edges = new ArrayList<Edge>();
		List<Boolean> r_orphans = new ArrayList<Boolean>();

		for (int i = 0; i < this.R.size(); i++) {
			r_orphans.add(true);
		}

		int i = 0;
		while (this.L.size() > 0 && i < this.L.size()) {
			Knight temp = new Knight(this.L.get(i));
			ArrayList<Position> R_connection = temp.getThreats();
			if (this.TEST) {
				for (int j = 0; j < R_connection.size(); j++) {
					System.out.println("TEST: R_connection(" + j + ") -> "
							+ R_connection.get(j));
				}
			}

			boolean orphan = true;
			for (Position p : R_connection) {
				if (this.TEST) {
					System.out.println("Position -> " + p);
				}
				if (this.R.contains(p)) {
					if (this.TEST) {
						System.out.println("TEST: Adding edge -> "
								+ new Edge(i, this.R.indexOf(p), 0));
					}
					this.edges.add(new Edge(i, this.R.indexOf(p), 0));
					orphan = false;

					int r_index = this.R.indexOf(p);
					r_orphans.set(r_index, false);
				}
			}

			if (orphan) {
				this.IS.add(this.L.get(i));
				this.L.remove(i);
			} else {
				i++;
			}
		}

		i = 0;
		while (r_orphans.size() > 0 && i < r_orphans.size()) {
			if (this.TEST) {
				System.out.println("TEST: entered while loop for r_orphans.");
			}
			if (r_orphans.get(i)) {
				this.IS.add(this.R.get(i));
				this.R.remove(i);
				r_orphans.remove(i);
				if (this.TEST) {
					System.out.println("TEST: removing for r_orphans.");
				}
			} else {
				i++;
				if (this.TEST) {
					System.out.println("TEST: incremented for r_orphans.");
				}
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

			int i = 1;
			while (this.edges.size() > 1 && i < this.edges.size()) {
				if (this.edges.get(i).getStart() == u) {
					if (!take_nodes_from_L) {
						this.IS.add(this.R.get(this.edges.get(i).getEnd()));
					}
					this.edges.remove(i);
				} else if (this.edges.get(i).getEnd() == v) {
					if (take_nodes_from_L) {
						this.IS.add(this.L.get(this.edges.get(i).getStart()));
					}
					this.edges.remove(i);
				} else {
					i++;
				}
			}
			this.edges.remove(0);
			if (this.TEST) {
				System.out.println("TEST: removed edge " + e);
			}
		}

		for (int k = 0; k < this.IS.size(); k++) {
			for (int l = k + 1; l < this.IS.size();) {
				if (this.IS.get(k).equals(this.IS.get(l))) {
					this.IS.remove(l);
				} else {
					l++;
				}
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
