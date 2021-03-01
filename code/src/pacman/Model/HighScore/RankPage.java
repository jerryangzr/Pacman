package pacman.Model.HighScore;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import pacman.Model.Game.Main;


public class RankPage {
		
	/**
	 * setup the scene for HighScore Page
	 * @throws IOException
	 */
	public void Ranking() throws IOException {
    	Parent root = FXMLLoader.load(getClass().getResource("/pacman/View/Ranking.fxml"));
       	Scene RankingScene = new Scene(root,600,800);
        Main.PrimaryStage.setTitle( "HighScore !!!" );
        RankingScene.setFill(Color.BLACK);
        RankingScene.getStylesheets().add("mystyles.css");
		Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
		Main.PrimaryStage.setScene(RankingScene);
	}
}
