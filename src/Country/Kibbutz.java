//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Country;
import java.util.List;
import Location.Location;
import Population.Person;


public class Kibbutz extends Settlement
{
	public Kibbutz(String name , Location location, List<Person> people , RamzorColor ramzorcolor,int maxPopulation) { //constructor for all fields
		super(name,location,people,ramzorcolor,maxPopulation);
	}
	public String getType() {
		return "Kibbutz";
	}
	public RamzorColor calculateRamzorGrade() { //calculate ramzor grade , set ramzor rating value
		double c = getRamzorRating();
		double p = contagiousPercent();
		double result = 0.45+(Math.pow(1.5,c)*Math.pow((p-0.4),3));
		
		this.setRamzorRating(result);

		return RamzorColor.gradeToColor(result);
	}
}