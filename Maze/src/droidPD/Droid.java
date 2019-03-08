package droidPD;

import java.util.ArrayList;
import java.util.Stack;

import mazePD.Coordinates;
import mazePD.DroidInterface;
import mazePD.Maze;
import mazePD.Maze.Content;
import mazePD.Maze.Direction;

public class Droid implements DroidInterface {

	private String name;
	private Maze maze;

	public enum STATUS {IDLE, EXPLORING, LASTSEARCHED};
	
	public Droid(String name, Maze maze) {
		this.name = name;
		this.maze = maze;
	}
	
	public void solveMaze() {
		Stack<Direction> moves = new Stack<Direction>();
		ArrayList<Coordinates> visitedCells = new ArrayList<Coordinates>();
		Boolean endFound = false;
		
		System.out.println(maze.toString());
		System.out.println();
		System.out.println("Maze Level:");
		printMaze(maze);
		
		maze.enterMaze(this);
		System.out.println();
		System.out.println("Current location: " + maze.getCurrentCoordinates(this));
		visitedCells.add(maze.getMazeStartCoord());
		
		// loop while not at the end of the maze
		while (maze.scanCurLoc(this) != Content.END)  {
			
			// get a list of surrounding cell's contents
			Content[] contentList = maze.scanAdjLoc(this);
			
			// check if any surrounding cells have an end
			if (contentList[0] == Content.END) {
				System.out.println("End found! Moved to: " + maze.move(this, Direction.D00));
				endFound = true;
			}
			else if (contentList[1] == Content.END) {
				System.out.println("End found! Moved to: " + maze.move(this, Direction.D90));
				endFound = true;
			}
			else if (contentList[2] == Content.END) {
				System.out.println("End found! Moved to: " + maze.move(this, Direction.D180));
				endFound = true;
			}
			else if (contentList[3] == Content.END) {
				System.out.println("End found! Moved to: " + maze.move(this, Direction.D270));
				endFound = true;
			}
			
			// check if any surrounding cells have a portal down
			if (contentList[0] == Content.PORTAL_DN) {
				maze.move(this, Direction.D00);
				maze.usePortal(this);
				visitedCells.clear();
				moves.clear();
				System.out.println("Portal down");
			}
			else if (contentList[1] == Content.PORTAL_DN) {
				maze.move(this, Direction.D90);
				maze.usePortal(this);
				visitedCells.clear();
				moves.clear();
				System.out.println("Portal down");
			}
			else if (contentList[2] == Content.PORTAL_DN) {
				maze.move(this, Direction.D180);
				maze.usePortal(this);
				visitedCells.clear();
				moves.clear();
				System.out.println("Portal down");
			}
			else if (contentList[3] == Content.PORTAL_DN) {
				maze.move(this, Direction.D270);
				maze.usePortal(this);
				visitedCells.clear();
				moves.clear();
				System.out.println("Portal down");
			}
			
			if (!endFound) {
				
				Coordinates[] adjCoord = maze.getAdjCoords(this);
				
				// determine which empty space to move to
				if (contentList[0] == Content.EMPTY && !visitedCells.contains(adjCoord[0])) {
					System.out.println("Moved to: " + maze.move(this, Direction.D00));
					visitedCells.add(maze.getCurrentCoordinates(this));
					moves.push(Direction.D180);
				}
				else if (contentList[1] == Content.EMPTY && !visitedCells.contains(adjCoord[1])) {
					System.out.println("Moved to: " + maze.move(this, Direction.D90));
					visitedCells.add(maze.getCurrentCoordinates(this));
					moves.push(Direction.D270);
				}
				else if (contentList[2] == Content.EMPTY && !visitedCells.contains(adjCoord[2])) {
					System.out.println("Moved to: " + maze.move(this, Direction.D180));
					visitedCells.add(maze.getCurrentCoordinates(this));
					moves.push(Direction.D00);
				}
				else if (contentList[3] == Content.EMPTY && !visitedCells.contains(adjCoord[3])) {
					System.out.println("Moved to: " + maze.move(this, Direction.D270));
					visitedCells.add(maze.getCurrentCoordinates(this));
					moves.push(Direction.D90);
				}
				else {
					System.out.println("Moved back to: " + maze.move(this, moves.pop()));
				}
			}
		}
		System.out.println("Maze completed!");
		
	}
	
	public void printMaze(Maze maze)
	{
		String[] layout;
		
		for (int z=0; z<maze.getMazeDepth(); z++) {
			layout = maze.toStringLevel(z);
			System.out.println("Level: " + z);
			for (int y=0; y < layout.length; y++)
				System.out.println(layout[y]);

		}
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
