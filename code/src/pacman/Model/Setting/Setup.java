package pacman.Model.Setting;

import java.io.IOException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Setup {
	
	/**
	 * set up the scene for the Setting Page 
	 * @param E
	 * @throws IOException
	 */
	public void Setting(ActionEvent E) throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/pacman/View/SetupPage.fxml"));
       	Scene theScene = new Scene(root);
        Node info = (Node) E.getSource();
        Stage stage = (Stage) info.getScene().getWindow(); 
        stage.setTitle( "Setting" );
        theScene.setFill(Color.BLACK);
		Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
		stage.setScene(theScene);
	}
}
