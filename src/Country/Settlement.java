package Country;
import java.util.*;
import Population.*;
import Location.*;

public class Settlement 
{
		
	 private String name;
	 private Location location;
	 private List<Person>  people;
	 private RamzorColor ramzorcolor;
	 
	 public Settlement(String name , Location location, List<Person> people , RamzorColor ramzorcolor)
	 {
		 this.name = new String(name);
		 this.location = new Location(location);
		 this.people = new ArrayList<Person>(people);
		 this.ramzorcolor = ramzorcolor;
	 }
	 public Settlement(Settlement other)
	 {
		 this.name= new String(other.getName());
		 this.location= new Location(other.getLocation());
		 this.people = other.people;/////by refrence (not by value)
		 this.ramzorcolor = other.getColor();
	 
	 }
	 public RamzorColor calculateRamzorGrade()
	 {
		return ramzorcolor;
	 }
 
	 public double contagiousPercent()
	 {
			int sickCounter=0;
			for (int i=0 ; i< people.size(); i++)
			{
				if(people.get(i) instanceof Sick)
					sickCounter++;
			}
			return  people.size()/sickCounter; 
	 }
	 
	 
	 
	 public Point randomLocation()
	 {
		 return new Point((int)Math.random()*200-100,(int)Math.random()*200-100);
	 }
	 
	 public boolean addPerson(Person other)
	 {
			this.people.add(other);
		 	return true;
	 }

	 
	 
	 public boolean transferPerson(Person other , Settlement one)
	 {
		 return true;
	 }
	
	
	 public void setColor(RamzorColor newColor)
	 {
		 this.ramzorcolor=newColor;
		 
	 }
	 public RamzorColor getColor()
	 {
		 return this.ramzorcolor;
	 }
	
	 
	
	
	
	
	public String getName()
	{
		return this.name;	
	}
	
	public Location getLocation()
	{
		return this.location;
	}
	
	public String toString()
	{
		String peoplestring = "";
		for (int i=0 ;i<people.size();i++){
			peoplestring += people.get(i).toString();
		}
		return "The name is: "+ name+
		"\nThe location on the map is:"+location+
		"\nThe ramzor color is: "+ramzorcolor+
		"\nList of people: "+peoplestring;
		
	}
}
