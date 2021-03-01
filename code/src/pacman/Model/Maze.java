package pacman.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import pacman.Model.Game.Score;
import pacman.Model.Manager.GameManager;
import pacman.Model.Setting.SetLevel;

public class Maze {
	
	private GameManager Gamemanager;    
	private int i = 0;
	private int size = 31; 			//Maze 1:31 , Maze 2: 32 , Maze 3: 34
	private String path = "resources/Maze-Level1.txt";
    private Set<BarObstacle> obstacles;
	private Score scoreBoard;

	
    /**
     * Constructor
     */
    public Maze(GameManager gamemanager) {
        obstacles = new HashSet<>();
        this.Gamemanager = gamemanager;
    }

	public Score getScoreBoard() {
		return scoreBoard;
	}
    
    /**
     * Checks if point is touching obstacles
     * @param x
     * @param y
     * @return
     */
    public Boolean isTouching(double x, double y, double padding) {
        for (BarObstacle barObstacle:obstacles) {
            if (
                x >= barObstacle.getX() - padding &&
                x <= barObstacle.getX() + padding + barObstacle.getWidth() &&
                y >= barObstacle.getY() - padding &&
                y <= barObstacle.getY() + padding + barObstacle.getHeight())
            {
                return true;
            }
        }
        return false;
    }

    /**
     * lets you know if there's an obstacle in the current coordinate
     * @param fromX
     * @param toX
     * @param fromY
     * @param toY
     * @return
     */
    public Boolean hasObstacle(double fromX,  double toX, double fromY, double toY) {
        boolean isTouching = false;
        for (double i = fromX; i < toX; i++) {
            for (double j = fromY; j < toY; j++) {
                if (this.isTouching(i, j, 0)) isTouching = true;
            }
        }
        return isTouching;
    }

    /**
     * Draws the maze
     * @param root
     */
    public void CreateMaze() {
		if (SetLevel.getMazeLevel() != null) {
			path = SetLevel.getMazeLevel();
			size = SetLevel.getMazeSize();
		}
    	String []Direction = new String [size];
        double []X = new double [size];
    	double []Y = new double [size];			 
        double []Length = new double [size];
		try {
			File file = new File(path);
        	FileReader fr = new FileReader(file);
        	BufferedReader reader = new BufferedReader(fr);
        	String line = reader.readLine();
			while (line!= null) { 
			    String []Maze = line.split(",");
	    		X[i] = Double.parseDouble(Maze[0]);
	    		Y[i] = Double.parseDouble(Maze[1]);
	    		Direction[i] = Maze[2];
	    		Length[i] = Double.parseDouble(Maze[3]);			    		
		    	i++;
				line = reader.readLine();
			}			
			for(int j = 0; j < size ; j++) {
		        this.obstacles.add(new BarObstacle(X[j],Y[j],Direction[j],Length[j]));
			}		
			reader.close();					
        } catch(Exception e) {
        	e.printStackTrace();
        }
        Gamemanager.getRoot().getChildren().addAll(obstacles);
    }
    
     
    /**
     * Draws the board of the game with the cookies and the Pacman
     */
    public void drawBoard() {
        CreateMaze(); 
        try{
        	File file = new File("resources/Cookie.txt");
        	FileReader fr = new FileReader(file);
        	BufferedReader reader = new BufferedReader(fr);
        	String line = reader.readLine();
        	double k = 2.5;
			while (line!= null) { 
			    String []Skip = line.split(",");
			    Integer skip[] = new Integer[Skip.length];
			    for(int s = 0; s < Skip.length; s++) {
			    	if(Skip!=null) {
			    		skip[s] = Integer.valueOf(Skip[s]);
			    	}
			    }
		        for (int j = 0; j < 23; j++) {
		            if (!Arrays.asList(skip).contains(j)) {
		                Cookie cookie = new Cookie(((2*j) + 2.5) * BarObstacle.THICKNESS, k * BarObstacle.THICKNESS);
		                this.Gamemanager.getCookieSet().add(cookie);
		                Gamemanager.getRoot().getChildren().add(cookie);
		            }
		        }
		        k += 2; 
				line = reader.readLine();
			}
			reader.close();
			
        } catch(Exception e) {
        	e.printStackTrace();
        }
        Gamemanager.getRoot().getChildren().add(this.Gamemanager.getPacmanmanager().getPacman());
        this.Gamemanager.getGhostmanager().generateGhosts();
        Gamemanager.getRoot().getChildren().addAll(this.Gamemanager.getGhostmanager().ghosts);
        scoreBoard = new Score(Gamemanager.getRoot());
    }
}
