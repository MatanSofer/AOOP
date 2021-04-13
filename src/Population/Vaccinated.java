package Population;
import Location.*;
import Country.*;
import Virus.*;
import Simulation.*;

public class Vaccinated extends Person
{
	
	private long vaccinationTime;

	public Vaccinated(int age , Point location , Settlement settlement,long vaccinationTime)  // constructor for all fields
	{
		super(age , location , settlement);
		this.vaccinationTime=vaccinationTime;
	}

	public  double contagionProbability()  // return the contagionProbability static double
	{
		long t = getTimeFromVaccination();  //calculate by those conditions
		if (t<21)
		{
			return Math.min(1,0.56+0.15*Math.sqrt(21-t));
		}
		else if (t >= 21)
		{
			return Math.max(0.05,1.05/(t-14));
		}
		else 
			throw new IllegalArgumentException( "Error negative passed time.");
	}
	
	public long getVaccinationTime() // return vaccination time
	{
		return vaccinationTime;
	}
	
	public long getTimeFromVaccination() // return the time difference
	{
		return getVaccinationTime()-Clock.now();
	}
	
	public Person contagion(IVirus virus)  // returns new similar person with different health condition , now he sick
	{
		Point locationCopy = new Point(getLocation());
		Settlement settlementCopy = new Settlement(getSettlement());
		Sick sickperson = new Sick(getAge(),locationCopy,settlementCopy,Clock.now(),virus);
		return sickperson;
	}
	public String getType()
	{
		return "Vaccinated";
	}
	public String toString()
	{
		return super.toString() +" his vaccinationTime is : " +getVaccinationTime() +"\n";
	}
	public boolean isEqual(Vaccinated other) // is equal
	{
		return super.isEqual((Person)other) &&
		this.getVaccinationTime() == other.getVaccinationTime() ;
	}

}

