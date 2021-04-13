package Country;

import java.util.List;

import Location.Location;
import Population.Person;

public class Moshav extends Settlement
{
	public Moshav(String name , Location location, List<Person> people , RamzorColor ramzorcolor)  //constructor for all fields
	{
		super(name ,location,people ,ramzorcolor);
		
	}
	
	public RamzorColor calculateRamzorGrade()  //calculate ramzor grade , set ramzor rating value
	{
        double c = getRamzorRating();
		double p = contagiousPercent();
		double result = 0.3+ 3*(Math.pow((Math.pow(1.2,c))*(p-0.35),5));
		this.setRamzorRating(result);
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
