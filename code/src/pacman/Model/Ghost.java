package pacman.Model;



import javafx.animation.AnimationTimer;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import pacman.Model.Manager.GameManager;

import java.util.Random;




public class Ghost extends Rectangle implements Runnable {

    private String direction;
    private GameManager gameManager;
    private Maze maze;
    private AnimationTimer animation;
    private int walkAtLeast = 4;
    private int timesWalked;
    private static double step = 5;

    
    public Ghost(double x, double y, String name, Maze maze, GameManager gameManager) {
        this.setX(x);
        this.setY(y);
        this.maze = maze;
        this.gameManager = gameManager;
        this.setHeight(50);
        this.setWidth(50);
        Image image = new Image(name);
        this.setFill(new ImagePattern(image));
        this.timesWalked = 0;
        this.direction = "down";
        this.createAnimation();
    }

 
    public static void setStep(double Step) {
    	step = Step;
    }
    
    /**
     * get the random direction for the ghost to go
     * @param exclude1
     * @param exclude2
     * @return
     */
    private String getRandomDirection(String exclude1, String exclude2) {
        String[] directions = {"left", "right", "up", "down"};
        int rnd = new Random().nextInt(directions.length);
        while (directions[rnd].equals(exclude1) || directions[rnd].equals(exclude2)) {
            rnd = new Random().nextInt(directions.length);
        }
        return directions[rnd];
    }


    /**
     * Gets the animation for the ghost
     * @return
     */
    public AnimationTimer getAnimation() {
        return animation;
    }

    /**
     *
     * @param direction
     */
    private void checkIftheresPathToGo(String direction) {
        double rightEdge, leftEdge, topEdge, bottomEdge;
        switch (direction) {
            case "down":
                leftEdge = getX() - 10;
                bottomEdge = getY() + getHeight() + 15;
                rightEdge = getX() + getWidth() + 10;
                if (!maze.hasObstacle(leftEdge, rightEdge, bottomEdge - 1, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "up":
                leftEdge = getX() - 10;
                rightEdge = getX() + getWidth() + 10;
                topEdge = getY() - 15;
                if (!maze.hasObstacle(leftEdge, rightEdge, topEdge - 1, topEdge)) {
                    this.direction = direction;
                }
                break;
            case "left":
                leftEdge = getX() - 15;
                bottomEdge = getY() + getHeight() + 10;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(leftEdge - 1, leftEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
            case "right":
                bottomEdge = getY() + getHeight() + 10;
                rightEdge = getX() + getWidth() + 15;
                topEdge = getY() - 10;
                if (!maze.hasObstacle(rightEdge - 1, rightEdge, topEdge, bottomEdge)) {
                    this.direction = direction;
                }
                break;
        }
    }

    /**
     *
     * @param whereToGo
     * @param whereToChangeTo
     * @param leftEdge
     * @param topEdge
     * @param rightEdge
     * @param bottomEdge
     * @param padding
     */
    private void moveUntilYouCant(String whereToGo, String whereToChangeTo, double leftEdge, double topEdge, double rightEdge, double bottomEdge, double padding) {
        switch (whereToGo) {
            case "left":
                if (!maze.isTouching(leftEdge, topEdge, padding)) {
                    setX(leftEdge - step);
                } else {
                    while (maze.isTouching(getX(), getY(), padding)) {
                        setX(getX() + 1);
                    }
                    direction = whereToChangeTo;
                }
                break;
            case "right":
                if (!maze.isTouching(rightEdge, topEdge, padding)) {
                    setX(leftEdge + step);
                } else {
                    while (maze.isTouching(getX() + getWidth(), getY(), padding)) {
                        setX(getX() - 1);
                    }
                    direction = whereToChangeTo;
                }
                break;
            case "up":
                if (!maze.isTouching(leftEdge, topEdge, padding)) {
                    setY(topEdge - step);
                } else {
                    while (maze.isTouching(getX(), getY(), padding)) {
                        setY(getY() + 1);
                    }
                    direction = "left";
                }
                break;
            case "down":
                if (!maze.isTouching(leftEdge, bottomEdge, padding)) {
                    setY(topEdge + step);
                } else {
                    while (maze.isTouching(getX(), getY() + getHeight(), padding)) {
                        setY(getY() - 1);
                    }
                    direction = "right";
                }
                break;
        }

    }

    /**
     * Creates an animation of the ghost
     */
    public void createAnimation() {

        this.animation = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                gameManager.checkGhostCoalition();
                double leftEdge = getX();
                double topEdge = getY();
                double rightEdge = getX() + getWidth();
                double bottomEdge = getY() + getHeight();
                double padding = 12;
                timesWalked++;
                switch (direction) {
                    case "left":
                    	setScaleX(-1);
                        moveUntilYouCant("left", "down", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        LeftRighCheck();
                        if(leftEdge < 0) {
                        	setX(1225);
                        	setY(312.5);
                        }
                        break;
                    case "right":
                    	setScaleX(1);
                        moveUntilYouCant("right", "up", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        LeftRighCheck();
                        if(rightEdge > 1225) {
                        	setX(0);
                        	setY(312.5);
                        }
                        break;
                    case "up":
                        moveUntilYouCant("up", "left", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        UpDownCheck();
                        break;
                    case "down":
                        moveUntilYouCant("down", "right", leftEdge, topEdge, rightEdge, bottomEdge, padding);
                        UpDownCheck();
                        break;
                }
            }
        };
    }
    
    /**
     * Check for the left and right case to see if there is way to go
     */
    private void LeftRighCheck() {
        if (timesWalked > walkAtLeast) {
            checkIftheresPathToGo(getRandomDirection("left", "right"));
             timesWalked = 0;
        }
    }
    /**
     * Check for the up and down case to see if there is way to go
     */
    private void UpDownCheck() {
        if (timesWalked > walkAtLeast) {
            checkIftheresPathToGo(getRandomDirection("up", "down"));
            timesWalked = 0;
        }
    }
    
    @Override
    public void run() {
        this.animation.start();
    }
}
