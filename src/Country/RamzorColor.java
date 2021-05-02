//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Country;
import java.awt.Color;



public enum RamzorColor {
	Green  (Color.GREEN,1),  //calls constructor with value 0.4 grade
	Yellow(Color.YELLOW,0.8),  //calls constructor with value 0.6 grade
	Orange (Color.ORANGE,0.6),//calls constructor with value 0.7
	Red (Color.RED,0.4)//calls constructor with value 0.4
    ; // semicolon needed when fields / methods follow
	
	private Color color ;
    //private final double val;
    private final double inSettleProbability;

    private RamzorColor(Color color , double inSettleProbability) {
        this.inSettleProbability=inSettleProbability;
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
    
    public Color getColor() {
    	return this.color;
    }
    

    
    public double getInSettleProbability() {
    	return this.inSettleProbability;
    }
   
}