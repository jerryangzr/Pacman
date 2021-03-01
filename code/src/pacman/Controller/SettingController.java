package pacman.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import pacman.Model.Game.Main;
import pacman.Model.Setting.SetColour;
import pacman.Model.Setting.SetLevel;
import pacman.Model.Setting.ShowColour;

public class SettingController {
    @FXML private ImageView SetupPage;
    @FXML private Text Title;
    @FXML private ComboBox<String> GameLevel;
    @FXML private ComboBox<String> WallColour;
    @FXML private ComboBox<String> BackGroundColour;
    @FXML public  Rectangle ShowWall;
    @FXML public  Rectangle ShowBackGround;

    /**
     * Set the image for the Setting page and set the options for the combo boxes
     */
	@FXML
    public void initialize() {
    	ObservableList<String> level = FXCollections.observableArrayList("Easy","Medium","Hard");
    	GameLevel.setItems(level);
    	ObservableList<String> wall = FXCollections.observableArrayList("Black","Grey","Green","Indigo","Brown","Red","Blue","Yellow","Turquoise");
    	WallColour.setItems(wall);
		ObservableList<String> background = FXCollections.observableArrayList("Lavender","Grey","Green","Mint","Wood","Azure","Thistle","Beige","Dew");
		BackGroundColour.setItems(background);
		
    	Image setup = new Image("SetupPage.png");
        SetupPage.setImage(setup);
    }
	
	/**
	 * It's called when player clicks the BACK button in the Setting Page
	 * @throws Exception
	 */
	@FXML
    private void ControlBack() throws Exception{
    	Main back = new Main();
    	back.start(Main.PrimaryStage);
    }
    
	/**
	 * Set the Wall colour of the game and show the colour they just chose
	 */
    @FXML
    private void ControlWall() {
        String sw = WallColour.getSelectionModel().getSelectedItem();	//sw: String Wall
        SetColour colour = new SetColour();
        colour.Wall(sw);
        ShowColour show = new ShowColour();
        show.Wall(sw);
        ShowWall.setFill(ShowColour.getWallColour());
    }
    
    /**
     * Set the BackGround colour of the game and show the colour they just chose
     */
    @FXML
    private void ControlBackGround() {
        String sb = BackGroundColour.getSelectionModel().getSelectedItem(); //sb: String BackGround
        SetColour colour = new SetColour();
        colour.BackGround(sb);
        ShowColour show = new ShowColour();
        show.BackGround(sb);
        ShowBackGround.setFill(ShowColour.getBackgroundColour());
    }
    
    /**
     * Set the Level of the game with different maze layout
     */
	@FXML
    private void ControlLevel(){
        String gl = GameLevel.getSelectionModel().getSelectedItem();	//gl: Game Level
        SetLevel level = new SetLevel();
        level.Level(gl);
    }
    
}
