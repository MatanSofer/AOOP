//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Country;
import java.util.List;
import Location.Location;
import Population.Person;
import java.awt.Color;


public class City extends Settlement
{
	public City(String name , Location location, List<Person> people , RamzorColor ramzorcolor,int maxPopulation)  //constructor for all fields
	{
		super(name,location,people,ramzorcolor,maxPopulation);
	}
	
	public String getType()
	{
		return "City";
	}
	public RamzorColor calculateRamzorGrade()  //calculate ramzor grade , set ramzor rating value
	{
		double p = contagiousPercent();
		double result = 0.2*(Math.pow(4,1.25*p));
		this.setRamzorRating(result);
		
		return RamzorColor.gradeToColor(result);

		
	}
}