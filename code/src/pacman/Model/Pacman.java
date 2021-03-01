package pacman.Model;



import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;

public class Pacman extends Circle {

	/**
	 * set the location of the pacman and load proper image for it 
	 * @param x
	 * @param y
	 */
    public Pacman(double x, double y) {
        this.setCenterX(x);
        this.setCenterY(y);
        this.setRadius(25);
        Image image = new Image("pacman.png");
        this.setFill(new ImagePattern(image));
    }
}
