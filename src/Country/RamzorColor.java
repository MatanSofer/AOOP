//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Country;
import java.awt.Color;

//public enum RamzorColor  // enum colors 
//{
//	
//	Green,Yellow,Orange,Red;
//
//}


public enum RamzorColor {
	Green  (0.4,Color.GREEN),  //calls constructor with value 3
	Yellow(0.6,Color.YELLOW),  //calls constructor with value 2
	Orange   (0.7,Color.ORANGE),
	Red (0.8,Color.RED)//calls constructor with value 1
    ; // semicolon needed when fields / methods follow
	
	private Color color ;
    private final double val;

    private RamzorColor(double d,Color color) {
        this.val = d;
        this.color = color;
    }
    
    public static RamzorColor gradeToColor(double d) {
    	
		if (d <= 0.4)
			return RamzorColor.Green;
		else if (d <= 0.6)
			return RamzorColor.Yellow;
		else if (d <= 0.8)
			return RamzorColor.Orange;
		else
			return RamzorColor.Red;
    }
   
}