package pacman.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import pacman.Model.Game.Main;
import pacman.Model.Game.StartGame;
import pacman.Model.HighScore.HighScore;
import pacman.Model.Manager.GameManager;
import pacman.Model.Setting.SetPlayerInfo;


public class RankingController {
    private String name,score;
    private ObservableList<SetPlayerInfo> playerData = FXCollections.observableArrayList();
    @FXML private TableView<SetPlayerInfo> playerTable;
    @FXML private TableColumn<SetPlayerInfo, String> NameColumn;
    @FXML private TableColumn<SetPlayerInfo, String> ScoreColumn;

    /**
     * print the latest HighScore ranking to the table in HighScore page
     */
	@FXML
    public void initialize() {
        NameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        ScoreColumn.setCellValueFactory(cellData -> cellData.getValue().scoreProperty());
        playerTable.setItems(playerData);
        showHighScore();
    }

	/**
	 * add the info of the Record file to the table in HighScore page
	 */
	public void showHighScore() {
		for(int i = 0; i < 10; i++) {
			name = HighScore.Name[i];
			score = String.valueOf(HighScore.Score[i]);
			playerData.add(new SetPlayerInfo(name,score));
		}
	}

	/**
	 * It's called when player clicks the MENU button and return back to Menu page
	 * @throws Exception
	 */
	@FXML
    public void ControlMenu() throws Exception{
		GameManager.setScore(0);
    	Main menu = new Main();
    	menu.start(Main.PrimaryStage);
    }
	
	/**
	 * It's called when player clicks the RESTART button
	 * @param E
	 */
	@FXML
    public void ControlRestart(ActionEvent E) {
		StartGame start = new StartGame();
		start.Starting(E);
	}
    
	/**
	 * It's called when player clicks the EXIT button
	 */
	@FXML
    public void ControlExit(){
	  	Main menu = new Main();
    	menu.close(Main.PrimaryStage);
    }
	
	/**
	 * It's called when player clicks the BACK button in the ViewHighScore page and return back to Menu 
	 * @throws Exception
	 */
	@FXML
    public void ControlBack() throws Exception{
    	Main back = new Main();
    	back.start(Main.PrimaryStage);
    }
}
