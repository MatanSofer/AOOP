package Country;
import java.util.List;
import Location.Location;
import Population.Person;


public class Kibbutz extends Settlement
{
	public Kibbutz(String name , Location location, List<Person> people , RamzorColor ramzorcolor)
	{
		super(name,location,people,ramzorcolor);
	}
	
	public RamzorColor calculateRamzorGrade()
	{
		int c;
		
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
        else
        {
            c=4;
		}
		
		double p = contagiousPercent();
		
		double result = 0.45+(Math.pow(1.5,c)*Math.pow((p-0.4),3));
		
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