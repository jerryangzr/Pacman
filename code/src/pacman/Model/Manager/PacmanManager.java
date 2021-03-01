package pacman.Model.Manager;

import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyEvent;
import pacman.Model.BarObstacle;
import pacman.Model.Ghost;
import pacman.Model.Pacman;

public class PacmanManager {
	
    private Pacman pacman;
    private AnimationTimer leftPacmanAnimation;
    private AnimationTimer rightPacmanAnimation;
    private AnimationTimer upPacmanAnimation;
    private AnimationTimer downPacmanAnimation;
	private GameManager Gamemanager;
    
    /**
     * Constructor
     */
    PacmanManager(GameManager gamemanager) {
        this.Gamemanager = gamemanager;
        this.pacman = new Pacman(2.5 * BarObstacle.THICKNESS, 2.5 * BarObstacle.THICKNESS);
        this.leftPacmanAnimation = this.createAnimation("left");
        this.rightPacmanAnimation = this.createAnimation("right");
        this.upPacmanAnimation = this.createAnimation("up");
        this.downPacmanAnimation = this.createAnimation("down");
    }
    
    
		
	public Pacman getPacman() {
		return pacman;
	}

	public AnimationTimer getLeftPacmanAnimation() {
		return leftPacmanAnimation;
	}

    public AnimationTimer getRightPacmanAnimation() {
		return rightPacmanAnimation;
	}

	public AnimationTimer getUpPacmanAnimation() {
		return upPacmanAnimation;
	}

	public AnimationTimer getDownPacmanAnimation() {
		return downPacmanAnimation;
	}
	
	/**
	 * Moves the pacman
	 * @param event
	 */
	@SuppressWarnings("incomplete-switch")
	public void movePacman(KeyEvent event) {
	    for (Ghost ghost : this.Gamemanager.getGhostmanager().ghosts) {
	        ghost.run();
	    }
	    switch(event.getCode()) {
	        case RIGHT:
	            this.rightPacmanAnimation.start();
	            break;
	        case LEFT:
	            this.leftPacmanAnimation.start();
	            break;
	        case UP:
	            this.upPacmanAnimation.start();
	            break;
	        case DOWN:
	            this.downPacmanAnimation.start();
	            break;
	    }
	}
	
	// ------ Pacman --------    
	
	/**
	 * Stops the pacman
	 * @param event
	 */
	@SuppressWarnings("incomplete-switch")
	public void stopPacman(KeyEvent event) {
	    switch(event.getCode()) {
	        case RIGHT:
	            this.rightPacmanAnimation.stop();
	            break;
	        case LEFT:
	            this.leftPacmanAnimation.stop();
	            break;
	        case UP:
	            this.upPacmanAnimation.stop();
	            break;
	        case DOWN:
	            this.downPacmanAnimation.stop();
	            break;
	    }
	}
	
	/**
	 * check the Left and Right to see if the Pacman touches cookies.
	 */
	private void LeftRightCheck() {
        Gamemanager.checkCookieCoalition(pacman, "x");
        Gamemanager.checkGhostCoalition();
	}
	/**
	 * check the Up and Down to see if the Pacman touches cookies.
	 */
	private void UpDownCheck() {
        Gamemanager.checkCookieCoalition(pacman, "y");
        Gamemanager.checkGhostCoalition();
	}
	// ------ Pacman --------  
	
	/**
	 * Creates an animation of the movement.
	 * @param direction
	 * @return
	 */
	private AnimationTimer createAnimation(String direction) {
	    double step = 5;
	    return new AnimationTimer()
	    {
	        public void handle(long currentNanoTime)
	        {
		        switch (direction) {
		            case "left":
		            	pacman.setRotate(180);
		                if (!Gamemanager.getMaze().isTouching(pacman.getCenterX() - pacman.getRadius(), pacman.getCenterY(), 15)) {
		                    pacman.setCenterX(pacman.getCenterX() - step);
		                    LeftRightCheck();
		                }
		                if(pacman.getCenterX()<0) {
		                	pacman.setCenterX(1225);
		                	pacman.setCenterY(312.5);
		                }
		                break;
		            case "right":
		            	pacman.setRotate(0);
		                if (!Gamemanager.getMaze().isTouching(pacman.getCenterX() + pacman.getRadius(), pacman.getCenterY(), 15)) {
		                    pacman.setCenterX(pacman.getCenterX() + step);
		                    LeftRightCheck();
		                }
		                if(pacman.getCenterX()>1225) {
		                	pacman.setCenterX(0);
		                	pacman.setCenterY(312.5);
		                }
		                break;
		            case "up":
		            	pacman.setRotate(270);
		                if (!Gamemanager.getMaze().isTouching(pacman.getCenterX(), pacman.getCenterY() - pacman.getRadius(), 15)) {
		                    pacman.setCenterY(pacman.getCenterY() - step);
		                    UpDownCheck();
		                }
		                break;
		            case "down":
		            	pacman.setRotate(90);
		               if (!Gamemanager.getMaze().isTouching(pacman.getCenterX(), pacman.getCenterY() + pacman.getRadius(), 15)) {
		                   pacman.setCenterY(pacman.getCenterY() + step);
		                   UpDownCheck();
		               }
		               break;
		        }
	        }
	    };
	}
}