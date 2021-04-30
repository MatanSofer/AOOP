//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Country;
import java.awt.Color;



public enum RamzorColor {
	Green  (0.4,Color.GREEN,1),  //calls constructor with value 3
	Yellow(0.6,Color.YELLOW,0.8),  //calls constructor with value 2
	Orange   (0.7,Color.ORANGE,0.6),
	Red (0.8,Color.RED,0.4)//calls constructor with value 1
    ; // semicolon needed when fields / methods follow
	
	private Color color ;
    private final double val;
    private final double inSettleProbability;

    private RamzorColor(double d,Color color , double inSettleProbability) {
        this.inSettleProbability=inSettleProbability;
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
    
    public Color getColor() {
    	return this.color;
    }
    
    public double getVal() {
    	return this.val;
    }
    
    public double getInSettleProbability() {
    	return this.inSettleProbability;
    }
   
}