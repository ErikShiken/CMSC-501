package knights;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private final String LINE_SEPARATOR = System.getProperty("line.separator");
	ArrayList<Position> source_edges;
	ArrayList<Position> sink_edges;
	ArrayList<Position> L;
	ArrayList<Edge> L_edges;
	ArrayList<Position> R;
	ArrayList<Edge> R_edges;

	public Graph(List<Position> L, List<Position> R) {
		this.L = new ArrayList<Position>();
		this.R = new ArrayList<Position>();
		this.L.addAll(L);
		this.R.addAll(R);

		this.source_edges = new ArrayList<Position>();
		this.source_edges.addAll(this.L);

		this.sink_edges = new ArrayList<Position>();
		this.sink_edges.addAll(this.R);

		initL_edges();
		initR_edges();
	}

	private void initL_edges() {
		this.L_edges = new ArrayList<Edge>();
		for (int i = 0; i < this.L.size(); i++) {
			Knight temp = new Knight(this.L.get(i));
			ArrayList<Position> R_connection = temp.getThreats();

			boolean added = false;
			for (Position p : R_connection) {
				if (this.R.contains(p)) {
					this.L_edges.add(new Edge(i, this.R.indexOf(p)));
					added = true;
				}
			}

			if (!added) {
				// this L needs to be connected to the sink
				this.sink_edges.add(this.L.get(i));
			}
		}
	}

	private void initR_edges() {
		this.R_edges = new ArrayList<Edge>();
		for (int i = 0; i < this.R.size(); i++) {
			Knight temp = new Knight(this.R.get(i));
			ArrayList<Position> L_connection = temp.getThreats();

			boolean added = false;
			for (Position p : L_connection) {
				if (this.L.contains(p)) {
					this.R_edges.add(new Edge(i, this.L.indexOf(p)));
					added = true;
				}
			}

			if (!added) {
				// this R needs to be connected to the source
				this.source_edges.add(this.R.get(i));
			}
		}
	}

	public String dispConnections() {
		String returnStr = this.LINE_SEPARATOR;
		returnStr += "From source to L: ";
		returnStr += this.LINE_SEPARATOR;

		for (Position p : this.source_edges) {
			returnStr += "s -> " + p;
			returnStr += this.LINE_SEPARATOR;
		}

		returnStr += "From L to R: ";
		returnStr += this.LINE_SEPARATOR;
		for (Edge e : this.L_edges) {
			returnStr += "L" + this.L.get(e.getStart()) + " -> ";
			returnStr += "R" + this.R.get(e.getEnd());
			returnStr += this.LINE_SEPARATOR;
		}

		returnStr += "From R to L: ";
		returnStr += this.LINE_SEPARATOR;
		for (Edge e : this.R_edges) {
			returnStr += "R" + this.R.get(e.getStart()) + " -> ";
			returnStr += "L" + this.L.get(e.getEnd());
			returnStr += this.LINE_SEPARATOR;
		}

		returnStr += "From R to t: ";
		returnStr += this.LINE_SEPARATOR;
		for (Position p : this.sink_edges) {
			returnStr += p + " -> t";
			returnStr += this.LINE_SEPARATOR;
		}

		return returnStr;
	}
}
