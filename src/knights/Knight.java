package knights;

import java.util.ArrayList;

public class Knight {
	Position myPosition;
	ArrayList<Position> threatens;

	public Knight(Position pos) {
		this.myPosition = new Position(pos.getX(), pos.getY());
		this.generateThreats();
	}

	private void generateThreats() {

	}
}
