package Country;

import java.util.List;

import Location.Location;
import Population.Person;

public class Moshav extends Settlement
{
	public Moshav(String name , Location location, List<Person> people , RamzorColor ramzorcolor)
	{
		super(name ,location,people ,ramzorcolor);
		
	}
	
	public RamzorColor calculateRamzorGrade()
	{
		int c;
		 //////// 0 / 1
	    if(this.getColor() == RamzorColor.Green) 
	    {
            c=0;
	    }
        else if (this.getColor() == RamzorColor.Green) 
        {
            c=1;
        }
        else if (this.getColor() == RamzorColor.Yellow)
        {
            c=2;
        }
        else if (this.getColor() == RamzorColor.Orange)
        {   
            c=3;
        }
        else //red
        {
        	
            c=4;
		}

		double p = contagiousPercent();
		double result = 0.3+3*Math.pow(Math.pow(1.2,c)*(p-0.35),5);
		
		if (result <= 0.4)
			return RamzorColor.Green;
		else if (result <= 0.6)
			return RamzorColor.Yellow;
		else if (result <= 0.8)
			return RamzorColor.Orange;
		else
			return RamzorColor.Red;
	}
	
	
	
	
	
	
}
