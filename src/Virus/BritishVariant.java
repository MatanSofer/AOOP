package Virus;
import Population.*;
public class BritishVariant implements IVirus
{
	public double contagionProbability(Person other)
	{
		
			return 0.7 * other.contagionProbability();
		
		
	}
    public boolean tryToContagion(Person one, Person two)
    {
    	double p = contagionProbability(two)*Math.min(1,0.14*Math.exp(2-(0.25*d)));
    	return p >= Math.random();
    }
    public boolean tryToKill(Person other)
    {
    	double p ;
    	if(other.getAge() >= 0 && other.getAge() <=18)
		{
			p = Math.max(0 , (0.01-(0.01*0.01)) * Math.pow(t-15,2)  );
		} 
		else if(other.getAge() > 18 )
		{
			p = Math.max(0 , (0.1-(0.01*0.1)) * Math.pow(t-15,2)  );
		}
    	return (p>= Math.random());
    }
}
