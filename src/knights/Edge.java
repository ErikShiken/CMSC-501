package knights;

public class Edge {
	private int start;
	private int end;
	private int preflow;

	public Edge(int start, int end, int pf) {
		this.start = start;
		this.end = end;
		this.preflow = pf;
	}

	public String toString() {
		return "(" + this.start + ", " + this.end + ")";
	}

	public int getStart() {
		return this.start;
	}

	public int getEnd() {
		return this.end;
	}

	public int getPreflow() {
		return this.preflow;
	}

	public void setPreflow(int pf) {
		this.preflow = pf;
	}

	public boolean equals(Object e) {
		if (((Edge) e).getStart() == this.start
				&& ((Edge) e).getEnd() == this.end)
			return true;
		return false;
	}
}
