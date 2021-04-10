package Population;
import Location.*;
import Country.*;
import Virus.*;
import Simulation.*;

public class Vaccinated extends Person
{
	
	private long vaccinationTime;

	public Vaccinated(int age , Point location , Settlement settlement,long  vaccinationTime)
	{
		super(age , location , settlement);
		this.vaccinationTime=vaccinationTime;
	}
	
	
	
	public  double contagionProbability()
	{
		long t = getTimeFromVaccination();
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
	
	public long getVaccinationTime()
	{
		return vaccinationTime;
	}
	
	public long getTimeFromVaccination()
	{
		return getVaccinationTime()-Clock.now();
	}
	
	public Person contagion(IVirus virus)
	{
		
		Point locationCopy = new Point(getLocation());
		Settlement settlementCopy = new Settlement(getSettlement());
		Sick sickperson = new Sick(getAge(),locationCopy,settlementCopy,Clock.now(),virus);
		return sickperson;
	}

}

