package Virus;
import Population.*;
public interface IVirus {
	//to check about random
	
	public double contagionProbability(Person other);
    public boolean tryToContagion(Person one, Person two);
    public boolean tryToKill(Person other);
}
