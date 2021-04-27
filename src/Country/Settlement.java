//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Country;
import java.util.*;
import Population.*;
import Virus.ChineseVariant;
import Virus.SouthAfricanVariant;
import Location.*;

public class Settlement 
{
		
	 private String name;  //all fields the included in settlement
	 private Location location;
	 private List<Person> people;
	 private RamzorColor ramzorcolor;
	 private double ramzorRating; //this is additional field for saving ramzor VALUE
     private int maxPopulation;
	 private int vaccineDose=0; 
	 private int deadpopulation=0;
     List<Settlement> connectedSettlements = new ArrayList<Settlement>();
     List<Person> sickPeople = new ArrayList<Person>();
     List<Person> healthyPeople = new ArrayList<Person>();

    
	 
	 public Settlement() //default constructor (there isn't use now , maybe in future )
	 {
		 this.name = "NULL";
		 this.location = null;
		 this.people = null;
		 this.ramzorcolor = null;
		 this.ramzorRating = 0.4;
		 this.vaccineDose=0; 
	 }
	 public Settlement(String name , Location location, List<Person> people , RamzorColor ramzorcolor,int maxPopulation) // constructor for all fields
	 {
		 this.name = new String(name);
		 this.location = new Location(location);
		 this.people = new ArrayList<Person>(people);
		 this.ramzorcolor = ramzorcolor;
		 this.maxPopulation = maxPopulation;
		 //this.ramzorRating = 0.4;
	    //this.vaccineDose=0; 
	 }
	 public Settlement(Settlement other) //copy constructor
	 {
		 this.name= new String(other.getName());
		 this.location= new Location(other.getLocation());
		 this.people = other.people;
		 this.ramzorcolor = other.getColor();
	 
	 }
	 public RamzorColor calculateRamzorGrade() //this method implemented in "sons"
	 {
		return ramzorcolor;
	 }
 
	 public double contagiousPercent() //calculate the percent value between 0 to 1 of sick people
	 {
		 	
			double sickCounter=0;
			for (int i=0 ; i< people.size(); i++)
			{
				if(people.get(i) instanceof Sick)
					sickCounter++;
			}
			return  sickCounter/this.people.size();   //return the percent
	 }


	 public static Point randomLocation(Point point ,Size size) //return random location in the settle
	 {
		 int x=((int)(Math.random()*size.getWidth()))+point.getX() ;
		 int y=((int)(Math.random()*size.getHeight()))+point.getY() ;
		 return new Point(x,y);
	 }
	 
	 public boolean addPerson(Person other) //add another person to settle people array
	 {
			this.people.add(other);
	
		 	return true;
	 }
	 
	 public void jtableMakeSick()
	 {
		 System.out.println("ss");
		 Person temp;
		 
		 for(int i = 0 ; i < 0.01*healthyPeople.size(); i++)
		 {
			 
			 temp = healthyPeople.get(i).contagion(new ChineseVariant());  ///מאורררררררר
			 sickPeople.add(temp);
			 healthyPeople.remove(i);
			 
		 }
	 }

	 
	 public void updateSickHealthy()
	 {
		 healthyPeople.clear();
		 sickPeople.clear();
		 for(int i = 0 ; i < people.size() ; i++)
		 {
			   if(people.get(i) instanceof Sick )
				{
					this.sickPeople.add(people.get(i));
				}
				else if(people.get(i) instanceof Healthy )
				{
					this.healthyPeople.add(people.get(i));
				}
		 }
	 }
	 public boolean transferPerson(Person other , Settlement one) //will be used in future
	 {
		if(this.getPeople().size() >= this.maxPopulation)
		{
			return false;
		}
		double randomProbability = Math.random();
		if(one.getColor().getInSettleProbability() * other.getSettlement().getColor().getInSettleProbability() <= randomProbability)
		{
			return false;
		}
		
		
		 return true;
	 }
	public List<Settlement> getconnectedSettlements()
	{
		return this.connectedSettlements;
	}
	public void appendconnectedSettlements(Settlement other)
	{
		this.connectedSettlements.add(other);
	}
	public void printconnectedSettlements()
	{
		System.out.println("For settlement " + getName()+" connected settlements are :");
		for(int i = 0 ; i < this.connectedSettlements.size(); i++)
		{	
			System.out.println(this.connectedSettlements.get(i).getName());
		}
	}
	 public void setColor(RamzorColor newColor) //set the ramzor color
	 {
		 this.ramzorcolor=newColor;
		 
	 }
	 public double getRamzorRating()  //get ramzor rating
	 {
		 return this.ramzorRating;
	 }
	 public void setRamzorRating(double newRating)  //set ramzor rating
	 {
		this.ramzorRating = newRating;
	 }
	 
	public RamzorColor getColor()  //get ramzor color
	{
		return this.ramzorcolor;
	}
	
	public List<Person> getPeople()  //get population list
	{
		return this.people;
	}
	public List<Person> getsickPeople()  //get population list
	{
		return this.sickPeople;
	}
	public List<Person> gethealthygetPeople()  //get population list
	{
		return this.healthyPeople;
	}
	public int getPeopleSize()
	{
		int tot = getPeople().size() ;
		return tot;
	}
	public int getSickPeopleSize()
	{
		return sickPeople.size();
	}
	public int getHealthyPeopleSize()
	{
		return healthyPeople.size();
	}
	public int getVaccineDose()
	{
		return this.vaccineDose;
	}
	public void setVaccineDose(int newDoses)
	{
		this.vaccineDose+=newDoses;
	}
	public int getdeadPopulation()
	{
		return this.deadpopulation;
	}
	public String getName()  //return name
	{
		return this.name;	
	}
	
	public Location getLocation()  //return location
	{
		return this.location;
	}
	public String getType()
	{
		return "Settlement";
	}
	
	
	public String toString() //to string
	{
        
		return "The name is: "+ name+
		"\nThe location on the map is:"+location+
		"\nThe ramzor color is: "+ramzorcolor+"\n";

	}

	public boolean isEqual(Settlement other)  //is equal
	{
		 return (this.getName() == other.getName() && this.getLocation() == other.getLocation() &&
		  this.getPeople() == other.getPeople() && this.getColor() == other.getColor() &&
		   this.getRamzorRating() == other.getRamzorRating());
	}
}
