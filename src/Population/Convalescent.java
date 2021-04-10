package Population;
import Country.Settlement;
import Location.Point;
import Virus.*;
import Simulation.*;

public class Convalescent extends Person
{
	private IVirus virus;	
    private static final double infectionCoefficient=0.2;
  
    public Convalescent(int age , Point location , Settlement settlement,IVirus virus)
    {
  	  super(age,location,settlement);
  	  this.virus=virus;
    }
    public double contagionProbability()
    {
	    return infectionCoefficient;
    }

	public Person contagion(IVirus virus)
	{
		Point locationCopy = new Point(getLocation());
		Settlement settlementCopy = new Settlement(getSettlement());
		Sick sickperson = new Sick(getAge(),locationCopy,settlementCopy,Clock.now(),virus);
		return sickperson;
	}

}
