package pacman.Model.Setting;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class SetPlayerInfo {
	
	    private StringProperty PlayerName;
        private StringProperty PlayerScore;
	
        /**
         * Constructor
         * @param PlayerName
         * @param PlayerScore
         */
        public SetPlayerInfo(String PlayerName, String PlayerScore){
            this.setName(PlayerName);
            this.setScore(PlayerScore);
        }
        
        /**
         * name property for adding to table view 
         * @return
         */
	    public StringProperty nameProperty(){
	        if(PlayerName == null) 
	        PlayerName = new SimpleStringProperty(this, "PlayerName");
	        return PlayerName;
	    }	    
	    
	    public void setName(String Name){
	    	nameProperty().set(Name);
	    }    
	    
        /**
         * score property for adding to table view 
         * @return
         */
	    public StringProperty scoreProperty(){
	        if(PlayerScore == null) 
	        PlayerScore = new SimpleStringProperty(this, "PlayerScore");
	        return PlayerScore;
	    }
	    
	    public void setScore(String Score) {
	        scoreProperty().set(Score);
	    }
	    
	    public String getScore() {
	        return scoreProperty().get();
	    }
}
