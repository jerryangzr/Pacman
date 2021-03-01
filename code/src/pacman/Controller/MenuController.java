package pacman.Controller;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import pacman.Model.Game.StartGame;
import pacman.Model.HighScore.HighScore;
import pacman.Model.HighScore.ViewRankPage;
import pacman.Model.Setting.Setup;


public class MenuController {
    @FXML private ImageView MenuImage1;
    @FXML private ImageView MenuImage2;
    @FXML public TextField Username;
    
    private static String name = "PlayerName";
    
    /**
     * set up the images used in Menu Page
     */
    @FXML
    public void initialize() {
        Image menu1 = new Image("menu1.png");
        MenuImage1.setImage(menu1);
        Image menu2 = new Image("menu2.png");
        MenuImage2.setImage(menu2);
    }
    
    /**
     * It's called when player clicks the START button
     * @param E
     */
    @FXML
    public void ControlStartGame(ActionEvent E){
    	setName();
        StartGame start = new StartGame();
        start.Starting(E);
    }
    /**
     * It's called when player clicks the SETTING button
     * @param E
     * @throws Exception
     */
	@FXML
    public void ControlSetup(ActionEvent E) throws Exception{
    	Setup set = new Setup();
    	set.Setting(E);
    }
	/**
	 * It's called when player clicks the HightScore button to view the current HighScore
	 * @param E
	 * @throws IOException
	 */
	public void ControlViewHighScore(ActionEvent E) throws IOException {
		HighScore hs = new HighScore();
		hs.Read();
	    ViewRankPage view = new ViewRankPage();
		view.Ranking();
	}
	/**
	 * Set the Name for player as "PlayerName" if player doesn't enter their name in the menu page
	 */
	public void setName() {
    	name = Username.getText();
        if(name.isEmpty()) {
        	name = "PlayerName";
        }
	}
	
	/**
	 * get the player's name he just entered in the menu page
	 * @return
	 */
	public static String getName() {
		return name;
	}
}

