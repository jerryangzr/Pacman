package pacman.Model.Setting;


public class SetLevel {

	private static int mazesize;
	private static String mazelevel;
	
	public static String getMazeLevel() {
		return mazelevel;
	}

	public static int getMazeSize() {
		return mazesize;
	}
	
	/**
	 * get the level player just chose and set according maze file for different layout
	 * @param s
	 */
	public void Level(String s) {
		switch(s) {
			case "Easy":
				mazelevel = "resources/Maze-Level1.txt";
				mazesize = 31;
				break;
			case "Medium":
				mazelevel = "resources/Maze-Level2.txt";
				mazesize = 32;
				break;
			case "Hard":
				mazelevel = "resources/Maze-Level3.txt";
				mazesize = 34;
				break;
		}
	}



}
