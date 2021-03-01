package pacman.Model.Game;



import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import pacman.Model.BarObstacle;

public class Score {

    public Text score;
    public Text lifes;
    public Text level;

    /**
     * detail setup for the text and print the Score, Remaining life and Level to the Game screen
     * @param root
     */
    public Score(Group root) {
        this.score = new Text(BarObstacle.THICKNESS * 4, BarObstacle.THICKNESS * 27, "Score: 0");
        this.lifes = new Text(BarObstacle.THICKNESS * 22, BarObstacle.THICKNESS * 27,"Lifes: 3");
        this.level = new Text(BarObstacle.THICKNESS * 40, BarObstacle.THICKNESS * 27,"Level: 1");       
        score.setFill(Color.MAGENTA);
        score.setFont(Font.font("Arial", 30));
        lifes.setFill(Color.MAROON);
        lifes.setFont(Font.font("Arial", 30));
        level.setFill(Color.BLACK);
        level.setFont(Font.font("Arial", 30));
        root.getChildren().add(score);
        root.getChildren().add(lifes);
        root.getChildren().add(level);
    }
}
