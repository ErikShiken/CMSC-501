package knights;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Knights {
	private static boolean TEST = false;
	private static Board myBoard;

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		initFlags(args);
		initBoard();

		myBoard.findMaxMatching();
	}

	private static void getInvalids(List<Position> invalids, BufferedReader br,
			int numInv) throws IOException {
		String strLine = "";
		for (int i = 0; i < numInv; i++) {
			strLine = br.readLine();
			Position temp = getPos(strLine);
			if (TEST) {
				System.out
						.println("TEST: getInvalids: position read from input --> "
								+ temp.toString());
			}
			invalids.add(temp);
		}
	}

	private static void initBoard() throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(reader);

		// get the size of the board
		String strLine = br.readLine();
		int dim = Integer.parseInt(strLine);
		Position final_pos = new Position(dim, dim);

		// get number of invalid points
		strLine = br.readLine();
		int numInv = Integer.parseInt(strLine);

		List<Position> invalids = new ArrayList<Position>();
		getInvalids(invalids, br, numInv);

		myBoard = new Board(invalids, final_pos, TEST);

		if (TEST) {
			System.out.println("TEST: Knights.initBoard: available fields --> "
					+ myBoard.dispAvailableFields());
		}
	}

	private static void initFlags(String[] args) {
		for (String arg : args) {
			if (arg.compareTo("-test") == 0) {
				TEST = true;
			}
		}
	}

	private static Position getPos(String strLine) {
		int commaInd = strLine.indexOf(',');
		int x = Integer.parseInt(strLine.substring(0, commaInd));
		int y = Integer.parseInt(strLine.substring(commaInd + 1,
				strLine.length()));

		return new Position(x, y);
	}
}
