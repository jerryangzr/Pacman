package pacman.Model.Game;

import javafx.event.ActionEvent;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pacman.Model.Manager.GameManager;
import pacman.Model.Setting.SetColour;

public class StartGame{
	
	/**
	 * Start setup the game scene at the beginning of the game when player click the Start Button
	 * @param E
	 */
	public void Starting (ActionEvent E) {
        Group root = new Group();
        Scene theScene = new Scene(root);
        Node info = (Node) E.getSource();
        Stage stage = (Stage) info.getScene().getWindow();
        
		Canvas canvas = new Canvas( 1225, 600 );
		root.getChildren().add( canvas );
		GameManager gameManager = new GameManager(root);
		
		gameManager.getMaze().drawBoard();
		
		theScene.addEventHandler(KeyEvent.KEY_PRESSED, event -> gameManager.getPacmanmanager().movePacman(event));
		theScene.addEventHandler(KeyEvent.KEY_RELEASED, event -> gameManager.getPacmanmanager().stopPacman(event));
		
		if (SetColour.getBackgroundColour() == null) {
			theScene.setFill(Color.WHITE);
		}
		else {
			theScene.setFill(SetColour.getBackgroundColour());
		}
		stage.setScene(theScene);
	}
}
