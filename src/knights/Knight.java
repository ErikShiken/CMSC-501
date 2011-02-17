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
		// Maximum number of possible threats for a Knight is 8
		this.threatens = new ArrayList<Position>();
		// 'top left' positions
		this.threatens.add(new Position(myPosition.getX() - 2, myPosition
				.getY() + 1));
		this.threatens.add(new Position(myPosition.getX() - 1, myPosition
				.getY() + 2));
		// 'top right' positions
		this.threatens.add(new Position(myPosition.getX() + 2, myPosition
				.getY() + 1));
		this.threatens.add(new Position(myPosition.getX() + 1, myPosition
				.getY() + 2));
		// 'bottom left' positions
		this.threatens.add(new Position(myPosition.getX() - 2, myPosition
				.getY() - 1));
		this.threatens.add(new Position(myPosition.getX() - 1, myPosition
				.getY() - 2));
		// 'bottom right' positions
		this.threatens.add(new Position(myPosition.getX() + 2, myPosition
				.getY() - 1));
		this.threatens.add(new Position(myPosition.getX() + 1, myPosition
				.getY() - 2));
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Position> getThreats() {
		return (ArrayList<Position>) this.threatens.clone();
	}
}
