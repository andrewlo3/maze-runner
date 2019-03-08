package droidHI;

import droidPD.Droid;
import mazePD.Maze;
import mazePD.Maze.MazeMode;

public class Test {

	public static void main(String[] args) {
		
		Maze maze = new Maze(5, 3, MazeMode.NORMAL);
		Droid droid = new Droid("Escapee", maze);
		
		droid.solveMaze();
	}
	
}