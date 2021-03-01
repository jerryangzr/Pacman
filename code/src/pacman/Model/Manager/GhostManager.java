package pacman.Model.Manager;

import java.util.Set;

import pacman.Model.BarObstacle;
import pacman.Model.Ghost;
import pacman.Model.Setting.SetLevel;

public class GhostManager {
	
    public Set<Ghost> ghosts;
    private GameManager Gamemanager;
    private String path = SetLevel.getMazeLevel();
	
    /**
     * Constructor
     * @param gamemanager
     */
    GhostManager(GameManager gamemanager) {
        this.Gamemanager = gamemanager;
    }
    	
     /**
     * Generates the ghosts for the pacman!
     */
    public void generateGhosts() {
	    this.ghosts.add(new Ghost(18.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, "ghost1.png", Gamemanager.getMaze(), Gamemanager));
	    this.ghosts.add(new Ghost(22.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, "ghost2.png", Gamemanager.getMaze(), Gamemanager));
	    this.ghosts.add(new Ghost(28.5 * BarObstacle.THICKNESS, 12.5 * BarObstacle.THICKNESS, "ghost3.png", Gamemanager.getMaze(), Gamemanager));
	    this.ghosts.add(new Ghost(28.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, "ghost4.png", Gamemanager.getMaze(), Gamemanager));
		if (path == "resources/Maze-Level2.txt" || path == "resources/Maze-Level3.txt") {
		    this.ghosts.add(new Ghost(22.5 * BarObstacle.THICKNESS, 9.5 * BarObstacle.THICKNESS, "ghost5.png", Gamemanager.getMaze(), Gamemanager));
		}
    }
}