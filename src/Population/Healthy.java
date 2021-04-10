package Population;
import Location.*;
import Country.*;
import Virus.*;
import Simulation.*;


public class Healthy extends Person
{
	
	private static double infectionCoefficient=1;
	
	public Healthy(int age , Point location , Settlement settlement)
	{
		super(age , location , settlement);
	}
	public Person vaccinate(){
		Point locationCopy = new Point(getLocation());
		Settlement settlementCopy = new Settlement(getSettlement());
		Vaccinated vaccinated = new Vaccinated(getAge(),locationCopy,settlementCopy,Clock.now());
		return vaccinated;
	}
	
	public Person contagion(IVirus virus){
		Point locationCopy = new Point(getLocation());
		Settlement settlementCopy = new Settlement(getSettlement());
		Sick sickperson = new Sick(getAge(),locationCopy,settlementCopy,Clock.now(),virus);
		return sickperson;
	}

	public double contagionProbability()
	{
		return infectionCoefficient;
	}
}
