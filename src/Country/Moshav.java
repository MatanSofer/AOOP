//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Country;
import java.util.List;
import Location.Location;
import Population.Person;

public class Moshav extends Settlement
{
	public Moshav(String name , Location location, List<Person> people , RamzorColor ramzorcolor,int maxPopulation)  //constructor for all fields
	{
		super(name ,location,people ,ramzorcolor,maxPopulation);
		
	}
	public String getType()
	{
		return "Moshav";
	}
	public RamzorColor calculateRamzorGrade()  //calculate ramzor grade , set ramzor rating value
	{
        double c = getRamzorRating();
		double p = contagiousPercent();
		double result = 0.3+ 3*(Math.pow((Math.pow(1.2,c))*(p-0.35),5));
		this.setRamzorRating(result);
		
		return RamzorColor.gradeToColor(result);
	}

}
