package Population;
import Country.*;
import Location.*;
import Virus.*;
import Simulation.*;

public class Sick extends Person
{
	private long contagiousTime;
	private IVirus virus;
	
	public Sick(int age , Point location , Settlement settlement,long contagiousTime,IVirus virus)
	{
		super(age,location,settlement);
		this.contagiousTime=contagiousTime;
		this.virus=virus;
	}

	public double contagionProbability()
	{
		
		return 1; 
	}
	
   public Person contagion(IVirus other)
	{
		throw new IllegalArgumentException("Already sick!");
	}
    
    public Person recover()
    {
    	Point locationCopy = new Point(getLocation());
    	Settlement settlementCopy = new Settlement(getSettlement());
    	Convalescent covalescent = new Convalescent(getAge(),locationCopy,settlementCopy,virus);
    	return covalescent;
    	
    }
    public boolean tryToDie()   
    {
    	return virus.tryToKill(this);
    }
}
