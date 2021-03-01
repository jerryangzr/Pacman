package pacman.Model.HighScore;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import pacman.Controller.MenuController;
import pacman.Model.Manager.GameManager;

public class HighScore {
	
	private int i,j;
	private int count = 0;
	private int playerscore = 0; 
	private String playername;
	public final static int size = 10;
	public static int Score[] = new int[size];
	public static String Name[] = new String [size];
	
	public void setPlayerScore(int Score) {
		playerscore = Score;
	}
    
	public void setPlayerName(String Name) {
		playername = Name;
	}
	
	public int getPlayerScore() {
		return playerscore;
	}
	
	public String getPlayerName() {
		return playername;
	}
	
	/**
	 * Read the Record file and sort the current player score and name into array to keep it as a ranked HighScore
	 */
	public void Read(){
		try { 
			  playername = MenuController.getName();
			  playerscore = GameManager.getScore();
			  File file = new File("resources/Record.txt");
			  FileReader fr = new FileReader(file);
			  BufferedReader reader = new BufferedReader(fr);
			  String line = reader.readLine();
			  while (line!= null) { 
					String name = line;
					Name[count] = name;
					line = reader.readLine();
					int score = Integer.parseInt(line);
		            Score[count] = score;
				  	count++;
					line = reader.readLine();
			  }
			  
			  for(i = 0 ; i < size ; i++) {	  
                  if(playerscore > Score[i]) {  
                   	  for(j = size - 1 ; j > i; j--) {
                    	  Score[j] = Score [j-1];
                    	  Name[j] = Name [j-1];
                	  }
	            	  Score[i] = playerscore;
	            	  Name [i] = playername;
	            	  break;
                  }
			  }
			  System.out.println("Your Name: " + playername);
			  System.out.println("Your Score: " + playerscore);
		      reader.close();
		}catch(Exception e){
			  e.printStackTrace();
		}
	}
	
	/**
	 * write the sorted array into Record file
	 */
	public void Write() {
		  try {			
			  File file = new File("resources/Record.txt");
			  FileWriter fw = new FileWriter(file);
			  BufferedWriter writer = new BufferedWriter(fw);
              for (i = 0; i < size; i++) {
            	  	  writer.write(Name[i] + "\n");
                      writer.write(Score[i] + "\n");
              }
	          writer.close();
		  } catch (Exception e) {
			  e.printStackTrace();
		  }	
	}
}
