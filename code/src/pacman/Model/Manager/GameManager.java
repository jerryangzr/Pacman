package pacman.Model.Manager;



import javafx.scene.Group;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import pacman.Model.BarObstacle;
import pacman.Model.Cookie;
import pacman.Model.Ghost;
import pacman.Model.Maze;
import pacman.Model.Pacman;
import pacman.Model.HighScore.HighScore;
import pacman.Model.HighScore.RankPage;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class GameManager {

    private PacmanManager pacmanmanager;
    private GhostManager ghostmanager;
    private Maze maze;
    private Group root;
    private Set<Cookie> cookieSet;
    private int lifes;
    private int level;
    private static int score;
    private int cookiesEaten;
     
    /**
     * Constructor
     */
    public GameManager(Group root) {
        this.root = root;
        this.pacmanmanager = new PacmanManager(this);
        this.ghostmanager = new GhostManager(this);
        this.maze = new Maze(this);
        this.cookieSet = new HashSet<>();
        this.ghostmanager.ghosts = new HashSet<>();
        this.lifes = 3;
        score = 0;
        this.level = 1;
        this.cookiesEaten = 0;
    }

	public PacmanManager getPacmanmanager() {
		return pacmanmanager;
	}

	public GhostManager getGhostmanager() {
		return ghostmanager;
	}

	public Maze getMaze() {
 		return maze;
 	}
	
    public Group getRoot() {
		return root;
	}

	public Set<Cookie> getCookieSet() {
		return cookieSet;
	}
	
    public static void setScore(int Score) {
		 score = Score;
    }
	
    public static int getScore() {
		return score;
    }
    
    /**
     * Call HighScore class to sort the current player score and name into Record file
     */
    public void CallHighScore(){
    	 HighScore hs = new HighScore();
         hs.Read();
         hs.Write();
    }
    
    /**
     * Call the RankPage class to set up the scene for HighScore Page
     */
    public void CallRankPage() {
    	RankPage rp = new RankPage();
    	try {
			rp.Ranking();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
	
	/**
     * Set one life less and show a pop up screen for current playing status
     */
    private void lifeLost(){
        this.pacmanmanager.getLeftPacmanAnimation().stop();
        this.pacmanmanager.getRightPacmanAnimation().stop();
        this.pacmanmanager.getUpPacmanAnimation().stop();
        this.pacmanmanager.getDownPacmanAnimation().stop();
        for (Ghost ghost : ghostmanager.ghosts) {
            ghost.getAnimation().stop();
        }
        this.pacmanmanager.getPacman().setCenterX(2.5 * BarObstacle.THICKNESS);
        this.pacmanmanager.getPacman().setCenterY(2.5 * BarObstacle.THICKNESS);
        lifes--;
        score -= 10;
        maze.getScoreBoard().lifes.setText("Lifes: " + this.lifes);
        maze.getScoreBoard().score.setText("Score: " + score);
        if(lifes != 0) {
        	Alert alert = new Alert(AlertType.WARNING); 
        	alert.setTitle(" Be careful !"); 
        	alert.setHeaderText(" You Lost your life!");
        	alert.setContentText("Your Current Score: " + score + "\n" + "Remaining life: " + this.lifes);
        	alert.show();
        }
        if (lifes == 0) {
            this.endGame();
        }
    }
    
	/**
     * Sort the current name and score into Record file and call the HighScore Page
     */
    private void endGame(){
        CallHighScore();
        CallRankPage();
    }
            
    /**
     * Checks if the Pacman touches cookies. Once pacman has eaten all cookies, 
     * show a pop up screen to remind the player to go to next to next level, which the ghost will
     * speed up 
     * @param pacman
     * @param axis
     */
    public void checkCookieCoalition(Pacman pacman, String axis) {
    	double k = 5;
    	double pacmanCenterY = pacman.getCenterY();
        double pacmanCenterX = pacman.getCenterX();
        double pacmanLeftEdge = pacmanCenterX - pacman.getRadius();
        double pacmanRightEdge = pacmanCenterX + pacman.getRadius();
        double pacmanTopEdge = pacmanCenterY - pacman.getRadius();
        double pacmanBottomEdge = pacmanCenterY + pacman.getRadius();
        for (Cookie cookie:cookieSet) {
            double cookieCenterX = cookie.getCenterX();
            double cookieCenterY = cookie.getCenterY();
            double cookieLeftEdge = cookieCenterX - cookie.getRadius();
            double cookieRightEdge = cookieCenterX + cookie.getRadius();
            double cookieTopEdge = cookieCenterY - cookie.getRadius();
            double cookieBottomEdge = cookieCenterY + cookie.getRadius();
            if (axis.equals("x")) {
                // pacman goes right
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge) && (pacmanRightEdge >= cookieLeftEdge && pacmanRightEdge <= cookieRightEdge)) {
                    if (cookie.isVisible()) {
                        score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
                // pacman goes left
                if ((cookieCenterY >= pacmanTopEdge && cookieCenterY <= pacmanBottomEdge) && (pacmanLeftEdge >= cookieLeftEdge && pacmanLeftEdge <= cookieRightEdge)) {
                    if (cookie.isVisible()) {
                        score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
            } else {
                // pacman goes up
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge) && (pacmanBottomEdge >= cookieTopEdge && pacmanBottomEdge <= cookieBottomEdge)) {
                    if (cookie.isVisible()) {
                        score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
                // pacman goes down
                if ((cookieCenterX >= pacmanLeftEdge && cookieCenterX <= pacmanRightEdge) && (pacmanTopEdge <= cookieBottomEdge && pacmanTopEdge >= cookieTopEdge)) {
                    if (cookie.isVisible()) {
                        score += cookie.getValue();
                        this.cookiesEaten++;
                    }
                    cookie.hide();
                }
            }
            maze.getScoreBoard().score.setText("Score: " + score);
            if (this.cookiesEaten == this.cookieSet.size()) {
                this.pacmanmanager.getLeftPacmanAnimation().stop();
                this.pacmanmanager.getRightPacmanAnimation().stop();
                this.pacmanmanager.getUpPacmanAnimation().stop();
                this.pacmanmanager.getDownPacmanAnimation().stop();
                for (Ghost ghost : ghostmanager.ghosts) {
                    ghost.getAnimation().stop();
                }
            	Alert alert = new Alert(AlertType.INFORMATION); 
            	alert.setTitle(" Notice!!! "); 
            	alert.setHeaderText(" You can continue to next level !");
            	alert.setContentText("Your Current Score: " + score + "\n" + "Remaining life: " + this.lifes);
            	alert.show();
            	k += 1;
            	level++;
                maze.getScoreBoard().level.setText("Level: " + level);
            	this.cookiesEaten = 0;
            	Ghost.setStep(k);
            	for(Cookie showCookie : cookieSet) {
            		showCookie.show();
            	}
            }
        }
    }
      
    /**
     * Checks if pacman is touching a ghost
     */
    public void checkGhostCoalition() {
        double pacmanCenterY = pacmanmanager.getPacman().getCenterY();
        double pacmanCenterX = pacmanmanager.getPacman().getCenterX();
        double pacmanLeftEdge = pacmanCenterX - pacmanmanager.getPacman().getRadius();
        double pacmanRightEdge = pacmanCenterX + pacmanmanager.getPacman().getRadius();
        double pacmanTopEdge = pacmanCenterY - pacmanmanager.getPacman().getRadius();
        double pacmanBottomEdge = pacmanCenterY + pacmanmanager.getPacman().getRadius();
        for (Ghost ghost : ghostmanager.ghosts) {
            double ghostLeftEdge = ghost.getX();
            double ghostRightEdge = ghost.getX() + ghost.getWidth();
            double ghostTopEdge = ghost.getY();
            double ghostBottomEdge = ghost.getY() + ghost.getHeight();
            if ((pacmanLeftEdge <= ghostRightEdge && pacmanLeftEdge >= ghostLeftEdge) || (pacmanRightEdge >= ghostLeftEdge && pacmanRightEdge <= ghostRightEdge)) {
                if ((pacmanTopEdge <= ghostBottomEdge && pacmanTopEdge >= ghostTopEdge) || (pacmanBottomEdge >= ghostTopEdge && pacmanBottomEdge <= ghostBottomEdge)) {
                    lifeLost();
                }
            }
        }
    }
}
