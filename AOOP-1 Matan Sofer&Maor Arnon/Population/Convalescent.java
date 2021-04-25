//* Authors: Maor Arnon (ID: 205974553) and Matan Sofer (ID:208491811)
package Population;
import Country.Settlement;
import Location.Point;
import Virus.*;
import Simulation.*;

public class Convalescent extends Person
{
	private IVirus virus;	
    private static  double infectionCoefficient=0.2; //static as requested
  
    public Convalescent(int age , Point location , Settlement settlement,IVirus virus) //constructor for all fields
    {
  	  super(age,location,settlement);
  	  this.virus=virus;
    }
    public double contagionProbability() // return the contagionProbability static double
    {
	    return infectionCoefficient;
    }

	public Person contagion(IVirus virus)  // returns new similar person with different health condition , now he sick
	{
		Point locationCopy = new Point(getLocation());
		Settlement settlementCopy = new Settlement(getSettlement());
		Sick sickperson = new Sick(getAge(),locationCopy,settlementCopy,Clock.now(),virus);
		return sickperson;
	}
	
	public  IVirus getVirus(){  //return virus type
		
		return virus;
	}
	public String getType()
	{
		return "Convalescent";
	}
	public boolean isEqual(Convalescent other)  //is equal
	{
		return super.isEqual((Person)other) &&
		this.getVirus().getClass().equals(other.getVirus().getClass());
	}

}
