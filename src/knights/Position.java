package knights;

import java.util.Comparator;

public class Position implements Comparator<Position> {
	private int x;
	private int y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	@Override
	public int compare(Position o1, Position o2) {
		int returnVal = -1;

		if (this.x == o1.getX() && this.y == o2.getY()) {
			returnVal = 0;
		} else if ((this.x != o1.getX() && this.y == o2.getY())
				|| (this.x == o1.getX() && this.y != o2.getY())) {
			returnVal = 1;
		}

		return returnVal;
	}

	public boolean equals(Position p) {
		if (p.getX() == this.x && p.getY() == y)
			return true;
		return false;
	}
}
