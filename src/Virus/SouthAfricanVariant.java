package Virus;
import Population.*;
public class SouthAfricanVariant implements IVirus 
{
	public double contagionProbability(Person other)
	{
		if(other.getAge() >= 0 && other.getAge() <=18)
		{
			return 0.6 * other.contagionProbability();
		}
		
		return 0.5 * other.contagionProbability();
		
		
		
	}
    public boolean tryToContagion(Person one, Person two)
    {
		if(two instanceof Healthy)
		{
			double p = contagionProbability(two)*Math.min(1,0.14*Math.exp(2-(0.25*d)));
	    	return p >= Math.random();
		}
    	
		return false;
    	
    }
    public boolean tryToKill(Person other)
    {
    	double p;
    	if(other.getAge() >= 0 && other.getAge() <=18)
		{
    		p = Math.max(0 , (0.05-(0.01*0.05)) * Math.pow(t-15,2)  );
		}
		else if(other.getAge() > 18 )
		{
			p = Math.max(0 , (0.08-(0.01*0.08)) * Math.pow(t-15,2)  );
		}
    	return p >= Math.random();
    }
}
