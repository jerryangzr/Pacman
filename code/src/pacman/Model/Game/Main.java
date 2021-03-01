package pacman.Model.Game;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	
	public static Stage PrimaryStage;

    @Override
    public void start(Stage theStage) throws Exception{
    	PrimaryStage = theStage;
    	PrimaryStage.setTitle( "Welcome to Pacman !!!" );
    	PrimaryStage.getIcons().add(new Image("pacman.png"));
    	Parent root = FXMLLoader.load(getClass().getResource("/pacman/View/Menu.fxml"));
    	Scene scene = new Scene(root);
        scene.setFill(Color.BLACK);
		Application.setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
		PrimaryStage.setScene(scene);
		PrimaryStage.show();
		PrimaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			public void handle(WindowEvent event) {
	          System.out.print("User close the Game....");          
	          System.exit(0);
	        }
		});
    }
    
    /**
     * Close the stage when player want to exit
     * @param theStage
     */
    public void close(Stage theStage) {
    	PrimaryStage = theStage;
    	PrimaryStage.close();
    	System.out.println("User exit the Game....");
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}