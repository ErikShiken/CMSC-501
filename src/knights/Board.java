package knights;

import java.util.ArrayList;
import java.util.List;

public class Board {
	ArrayList<Position> invalid_fields;
	ArrayList<Position> black_fields;
	ArrayList<Position> white_fields;
	ArrayList<Position> knight_fields;
	Graph bipartite_graph;
	Position size;

	final private boolean TEST;
	final private String LINE_SEPARATOR = System.getProperty("line.separator");

	public Board(List<Position> invalid_fields, Position size, boolean test) {
		this.size = size;
		this.TEST = test;
		this.invalid_fields = (ArrayList<Position>) invalid_fields;

		initAvailableFields();
		initGraph();
		List<Position> IS = this.bipartite_graph.findIS();

		if (this.TEST) {
			for (int i = 0; i < IS.size(); i++) {
				System.out.println("IS -> " + IS.get(i));
			}
		}
	}

	public void findMaxMatching() {
		initGraph();
		if (this.TEST) {
			System.out.println("TEST: Board.findMaxMatching: Completed");
			System.out
					.println("TEST: Board.findMaxMatching: Start relabel_to_front");
		}

		// int maxflow = this.bipartite_graph.relabel_to_front();
	}

	private void initAvailableFields() {
		boolean invalid = false;
		if (this.TEST) {
			System.out.println("TEST: Board.initAvailableFields: size --> "
					+ this.size.toString());
		}

		this.white_fields = new ArrayList<Position>();
		this.black_fields = new ArrayList<Position>();

		boolean black = true;

		for (int i = 1; i <= this.size.getX(); i++) {
			for (int j = 1; j <= this.size.getY(); j++) {
				Position temp = new Position(i, j);
				if (this.invalid_fields != null
						&& this.invalid_fields.contains(temp)) {
					invalid = true;
				}

				if (!invalid) {
					if (black) {
						// init black fields
						this.black_fields.add(temp);
						black = false;
					} else {
						// init white fields
						this.white_fields.add(temp);
						black = true;
					}
				} else {
					invalid = false;
				}
			}
		}
	}

	private void initGraph() {
		if (this.black_fields.size() > this.white_fields.size()) {
			this.bipartite_graph = new Graph(this.black_fields,
					this.white_fields, this.TEST);
		} else {
			this.bipartite_graph = new Graph(this.white_fields,
					this.black_fields, this.TEST);
		}

		if (this.TEST) {
			System.out.println("TEST: Board.initGraph: dispConnections: "
					+ this.bipartite_graph.dispConnections());
		}
	}

	public String dispAvailableFields() {
		String returnStr = this.LINE_SEPARATOR;
		returnStr += "White Fields: ";
		returnStr += this.LINE_SEPARATOR;

		for (Position p : this.white_fields) {
			returnStr += p.toString();
			returnStr += this.LINE_SEPARATOR;
		}

		returnStr += "Black Fields: ";
		returnStr += this.LINE_SEPARATOR;
		for (Position p : this.black_fields) {
			returnStr += p.toString();
			returnStr += this.LINE_SEPARATOR;
		}

		return returnStr;
	}
}
