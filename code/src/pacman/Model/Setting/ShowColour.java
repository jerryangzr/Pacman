package pacman.Model.Setting;

import javafx.scene.paint.Color;

public class ShowColour {
		
		private static Color wallcolour;
		private static Color backgroundcolour;

		public static Color getBackgroundColour() {
			return backgroundcolour;
		}
		
		public static Color getWallColour() {
			return wallcolour;
		}
		
		/**
		 * show the corresponding Wall colour for player to preview 
		 * @param s
		 */
		public void Wall(String s) {
			switch(s) {
				case "Black":
					wallcolour = Color.BLACK;
					break;
				case "Grey":
					wallcolour = Color.DIMGREY;
					break;
				case "Green":
					wallcolour = Color.DARKGREEN;
					break;
				case "Indigo":
					wallcolour = Color.INDIGO;
					break;
				case "Brown":
					wallcolour = Color.SADDLEBROWN;
					break;
				case "Red":
					wallcolour = Color.RED;
					break;
				case "Blue":
					wallcolour = Color.MIDNIGHTBLUE;
					break;
				case "Yellow":
					wallcolour = Color.YELLOW;
					break;
				case "Turquoise":
					wallcolour = Color.DARKTURQUOISE;
					break;
			}
		}
		
		/**
		 * show the corresponding BackGround colour for player to preview 
		 * @param s
		 */
		public void BackGround(String s) {
			switch(s) {
				case "Lavender":
					backgroundcolour = Color.LAVENDERBLUSH;
					break;
				case "Grey":
					backgroundcolour = Color.LIGHTGREY;
					break;
				case "Green":
					backgroundcolour = Color.DARKSEAGREEN;
					break;
				case "Mint":
					backgroundcolour = Color.MINTCREAM;
					break;
				case "Wood":
					backgroundcolour = Color.BURLYWOOD;
					break;
				case "Azure":
					backgroundcolour = Color.AZURE;
					break;
				case "Thistle":
					backgroundcolour = Color.THISTLE;
					break;
				case "Beige":
					backgroundcolour = Color.BEIGE;
					break;
				case "Dew":
					backgroundcolour = Color.HONEYDEW;
					break;
			}
		}	
}
