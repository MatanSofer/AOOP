package Virus;
import Population.*;
public interface IVirus {  // this interface is for all the viruses classes 3 method as requested
	
	
	public double contagionProbability(Person other);
    public boolean tryToContagion(Person one, Person two);
    public boolean tryToKill(Sick other);
}
